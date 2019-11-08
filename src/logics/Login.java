package logics;

import dataobjs.Admin;
import util.IOUtil;
import util.Printer;

public class Login {
	
	private void printMenu() {
		System.out.println("\nPlease enter the usertype\n" 
				+ "1. Admin\n"
				+ "2. Customer\n"
				+ "3. Exit");
	}

	public void run() {
		login:
			while (true) {
				printMenu();
				int key = IOUtil.getInt();
				IOUtil.ignoreLine();
				
				switch (key) {
					case 1: adminLogin(); break;
					case 2: new UserLogin().run(); break;
					case 3: break login;
					default: System.out.println("No such Option"); break;
				}
			}
	}
	
	private void adminLogin() {
		System.out.println("\nYou have chosen to login as Admin");
		System.out.println("Please enter username:");

		if (Admin.getInstance().verifyId(IOUtil.getStringLine())) {
			System.out.println("Please enter password:");
			
			if (Admin.getInstance().verifyPassword(IOUtil.readPassword())) {
				new AdminLogin().run();
				Printer.printWithColor("Successfully logged out", Printer.Color.GREEN);
			} else {
				Printer.printWithColor("Wrong Password", Printer.Color.RED);
			}
		} else {
			Printer.printWithColor("Wrong UserName", Printer.Color.RED);
		}
	}
}
