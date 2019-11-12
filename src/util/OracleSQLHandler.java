package util;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class ResultSetParser {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Class<?>[] parse(ResultSet resultSet, Class<?> obj) throws SQLException,
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	NoSuchMethodException, SecurityException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		Class cls[] = new Class[metaData.getColumnCount()];
		
		
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
		Constructor<?> const = obj.getConstructor(cls);
		
		for (int i = 1; resultSet.next(); ++i) {
			initargs = new Object[cls.length];
			for (int j = 0; j < cls.length; ++j) initargs[j] = resultSet.getObject(i, cls[j]);
			res[i] = (Class<?>) const.newInstance(initargs);
		}
		
		return res;
	}
}
