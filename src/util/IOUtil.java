package util;

import java.io.Console;
import java.util.Scanner;

public class IOUtil {
	
	private static Scanner scanner;
	
	public static void setScanner() {
		if (scanner == null) scanner = new Scanner(System.in);
	}
	
	public static void closeScanner() {
		if (scanner != null) {
			scanner.close();
			scanner = null;
		}
	}
	
	public static void ignoreLine() {
		scanner.nextLine();
	}
	
	public static String getStringLine() {
		if (scanner != null) return scanner.nextLine();
		return null;
	}
	
	public static Integer getInt() {
		if (scanner != null) {
			while (!scanner.hasNextInt()) {
				scanner.nextLine();
				System.err.println("A number is required.");
			}
			return scanner.nextInt();
		}
		return null;
	}
	
	public static Integer getJustInt() {
		setScanner();
		Integer integer;
		
		while (!scanner.hasNextInt()) {
			scanner.nextLine();
			System.err.println("A number is required.");
		}
		
		integer = scanner.nextInt();
		closeScanner();
		return integer;
	}
	
	public static Float getFloat() {
		if (scanner != null) return scanner.nextFloat();
		return 0f;
	}
	
	public static Double getDouble() {
		if (scanner != null) return scanner.nextDouble();
		return 0d;
	}
	
	public static String readPassword() {
		Console console = System.console();
		
		if (console == null) {
			return getStringLine();
		} else {
			return new String(console.readPassword());
		}
	}
	
	public static Character getChar() {
		String line = getStringLine();
		return line.charAt(0);
	}
}
