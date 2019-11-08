package logics;

import dataobjs.Customer;
import dataobjs.Store;
import util.IOUtil;
import util.Printer;

public class UserLogin {

	public void run() {
		printMenu();
		
		int option = IOUtil.getInt();
		IOUtil.ignoreLine();
		
		switch (option) {
			case 1: login(); break;
			case 2: register(); break;
			case 3: System.exit(0);
			default: break;
		}
	}

	private void printMenu() {
		System.out.println("\nYou have following option:\n"
				+ "1. Login\n"
				+ "2. Register as New User\n"
				+ "3. Close App");
	}
	
	private void login() {
		System.out.println("\nYou chosen to login in.");
		
		System.out.println("Enter your userid:");
		String id = IOUtil.getStringLine();
		
		Customer customer = Store.custMap.get(id);
		
		if (customer == null) {
			System.err.println("No such user");
			return;
		}
		
		System.out.println("Enter your password:");
		String passwordString = IOUtil.getStringLine();
		
		if (customer.verifyPassword(passwordString)) {
			Printer.printWithColor("You have successfully loggedin", Printer.Color.GREEN);
			new LoggedInUser().run(customer);
		} else {
			System.err.println("Wrong password");
		}
	}
	
	private void register() {
		System.out.println("You have chosen to register new user:");
		
		System.out.println("Enter name:");
		String name = IOUtil.getStringLine();
		
		System.out.println("Enter address:");
		String address = IOUtil.getStringLine();
		
		Customer customer = new Customer(name, address);
		Store.custMap.put(customer.getId(), customer);
		
		System.out.println("Your user id is " + customer.getId() +
				" and your default password is " + customer.getDefaultPassword());
	}
}
