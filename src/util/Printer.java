package util;


public class Printer {
	
	private static final String ANSI_RESET  = "\u001B[0m";

	private static final String ANSI_BLACK  = "\u001B[30m";
	private static final String ANSI_RED    = "\u001B[31m";
	private static final String ANSI_GREEN  = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_BLUE   = "\u001B[34m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private static final String ANSI_CYAN   = "\u001B[36m";
	private static final String ANSI_WHITE  = "\u001B[37m";

	private static final String ANSI_BRIGHT_BLACK  = "\u001B[90m";
	private static final String ANSI_BRIGHT_RED    = "\u001B[91m";
	private static final String ANSI_BRIGHT_GREEN  = "\u001B[92m";
	private static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
	private static final String ANSI_BRIGHT_BLUE   = "\u001B[94m";
	private static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
	private static final String ANSI_BRIGHT_CYAN   = "\u001B[96m";
	private static final String ANSI_BRIGHT_WHITE  = "\u001B[97m";
	
	public static enum Color {
		BLACK, RED,
		GREEN, YELLOW, BLUE,
		PURPLE, CYAN, WHITE,
		BRIGHT_BLACK, BRIGHT_RED, BRIGHT_GREEN,
		BRIGHT_YELLOW, BRIGHT_BLUE, BRIGHT_PURPLE,
		BRIGHT_CYAN, BRIGHT_WHITE
	}
	
	public static void printWithColor(String str, Color color) {
		switch (color) {
			case BLACK: System.out.print(ANSI_BLACK); break;
			case RED: System.out.print(ANSI_RED); break;
			
			case GREEN: System.out.print(ANSI_GREEN); break;
			case YELLOW: System.out.println(ANSI_YELLOW); break;
			case BLUE: System.out.println(ANSI_BLUE); break;
			
			case PURPLE: System.out.println(ANSI_PURPLE); break;
			case CYAN: System.out.println(ANSI_CYAN); break;
			case WHITE: System.out.println(ANSI_WHITE); break;
			
			case BRIGHT_BLACK: System.out.println(ANSI_BRIGHT_BLACK); break;
			case BRIGHT_RED: System.out.println(ANSI_BRIGHT_RED); break;
			case BRIGHT_GREEN: System.out.println(ANSI_BRIGHT_GREEN); break;
			
			case BRIGHT_YELLOW: System.out.println(ANSI_BRIGHT_YELLOW); break;
			case BRIGHT_BLUE: System.out.println(ANSI_BRIGHT_BLUE); break;
			case BRIGHT_PURPLE: System.out.println(ANSI_BRIGHT_PURPLE); break;
			
			case BRIGHT_CYAN: System.out.println(ANSI_BRIGHT_CYAN); break;
			case BRIGHT_WHITE: System.out.println(ANSI_BRIGHT_WHITE); break;
			default: break;
		}
		
		System.out.print(str);
		System.out.println(ANSI_RESET);
	}
}
