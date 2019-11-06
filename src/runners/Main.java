package runners;

import logics.Login;
import util.IOUtil;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to Java Toy Store");
		
		IOUtil.setScanner();
		
		new Login().run();
		
		IOUtil.closeScanner();
	}
}
