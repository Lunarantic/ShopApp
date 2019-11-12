package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class OracleSQLHandler {
	
	public enum Check {
		TABLE, SEQUENCE;
	}
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Kindly provide Oracle JDBC jar to build path.");
		}
	}
	
	public static Connection connection = null;
	
	public static void setConnection() {
		try {
			connection = DriverManager.getConnection(Config.DBURL, Config.DBUSER, Config.PASSWORD);
		} catch (SQLException e) {
			System.err.println("Connection not formed.");
		}
	}
	
	public static void closeConnection() {
		close(null, null, connection);
	}

	public static Class<?>[] parseResultSet(ResultSet resultSet, Class<?> obj) throws SQLException,
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	NoSuchMethodException, SecurityException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		Class<?> cls[] = new Class[metaData.getColumnCount()];
		
		
		for (int i = 0; i < cls.length;) {
			switch (metaData.getColumnType(i)) {
				case Types.BIGINT:
				case Types.INTEGER:
				case Types.TINYINT:
				case Types.NUMERIC:
					cls[++i] = Integer.class;
					break;
					
				case Types.VARCHAR:
				case Types.NVARCHAR:
				case Types.LONGNVARCHAR:
					cls[i] = String.class;
					break;
					
				case Types.FLOAT:
				case Types.DOUBLE:
				case Types.DECIMAL:
					cls[i] = Double.class;
					break;
					
				case Types.DATE:
				case Types.TIME:
				case Types.TIMESTAMP:
					cls[i] = String.class;
					break;
	
				default:
					break;
			}
			
		}
		
		Object[] initargs;
		Class<?>[] res = new Class<?>[resultSet.getFetchSize()];
		Constructor<?> constructor = obj.getConstructor(cls);
		
		for (int i = 1; resultSet.next(); ++i) {
			initargs = new Object[cls.length];
			for (int j = 0; j < cls.length; ++j) initargs[j] = resultSet.getObject(i, cls[j]);
			res[i] = (Class<?>) constructor.newInstance(initargs);
		}
		
		return res;
	}
	
	public static boolean checkFor(Check checkFor, String name) {
		if (Checks.isEmpty(checkFor) || Checks.isEmpty(name)) return false;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			switch (checkFor) {
				case TABLE:
					preparedStatement = connection.prepareStatement("select count(1) from user_tables"
														+ " where tablespace_name=? and table_name=?");
					break;
					
				case SEQUENCE:
					preparedStatement = connection.prepareStatement("select count(1) from dba_sequences"
														+ " where sequence_owner=? and sequence_name=?");
					break;

				default:
					break;
			}

			preparedStatement.setString(1, Config.DBUSER);
			preparedStatement.setString(2, name);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1) == 1;
		} catch (SQLException e) {
			System.err.println("Issue with preparedStatement.");
		} finally {
			OracleSQLHandler.close(resultSet, preparedStatement, null);
		}
		
		return false;
	}
	
	public static void close(ResultSet resultSet, PreparedStatement preparedStatement,
							Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			if (preparedStatement != null) {
				preparedStatement.close();
				preparedStatement = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {}
	}
	
	public static void createSequence(String sequenceName, Integer start, Integer incrementBy) {
		if (Checks.isEmpty(sequenceName) || checkFor(Check.SEQUENCE, sequenceName)) return;
		if (start == null) start = 1;
		if (incrementBy == null) start = 1;
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("CREATE SEQUENCE "
															+ sequenceName
															+ " START WITH "
															+ start
															+ " INCREMENT BY "
															+ incrementBy
															+ " NOCACHE NOCYCLE");
			preparedStatement.execute();
		} catch (SQLException e) {
			System.err.println("Issue with creating Sequence.");
			e.printStackTrace();
		} finally {
			OracleSQLHandler.close(null, preparedStatement, null);
		}
	}
	
	public static void createTable(String tableName, String tableParameters) {
		if (Checks.isEmpty(tableName) || checkFor(Check.TABLE, tableName) || Checks.isEmpty(tableParameters))
			return;
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(tableParameters);
		} catch (SQLException e) {
			System.err.println("Issue with creating Table.");
		} finally {
			OracleSQLHandler.close(null, preparedStatement, null);
		}
	}
}
