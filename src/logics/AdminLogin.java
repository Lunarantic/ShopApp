package logics;

import dataobjs.Order;
import dataobjs.Store;
import util.IOUtil;
import util.Printer;

public class AdminLogin {
	
	private void printMenu() {
		System.out.println("\nPlease enter the option to perform\n"
				+ "1. View Orders\n"
				+ "2. Approve Orders\n"
				+ "3. Reject Orders\n"
				+ "4. Update Inventry\n"
				+ "5. Logout\n"
				+ "6. Close App");
	}

	public void run() {
		Printer.printWithColor("You have successfully logged in", Printer.Color.GREEN);
		
		login:
			while(true) {
				printMenu();
				int key = IOUtil.getInt();
				IOUtil.ignoreLine();
				
				switch (key) {
					case 1: viewOrders(); break;
					case 2: approveOrders(); break;
					case 3: rejectOrders(); break;
					case 4: new ItemManagement().run(); break;
					case 5: break login;
					case 6: System.exit(0);
					default: System.out.println("No such Option"); break;
				}
			}
	}
	
	private void viewOrders() {
		System.out.println("Following orders have been received till date:");
		
		for (Order order: Store.allOrders) {
			System.out.println(order);
		}
		
		System.out.println();
	}
	
	private boolean query(Order order, String query) {
		System.out.println("Should we " + query + " order #" + order.getId() + " (Y):");
		
		Character option = IOUtil.getChar();
		
		switch (option) {
			case 'Y':
			case 'y': return true;
			default: return false;
		}
	}
	
	private void approveOrders() {
		System.out.println("Following orders awaiting approval:");
		
		while (!Store.pendingOrders.isEmpty()) {
			Order order = Store.pendingOrders.peek();
			if (query(order, "approve")) {
				order.setStatus(Order.Status.APPROVED);
				Store.pendingOrders.poll();
			}
		}
		
		System.out.println();
	}
	
	private void rejectOrders() {
		System.out.println("Following orders rejection approval:");
		
		while (!Store.pendingOrders.isEmpty()) {
			Order order = Store.pendingOrders.peek();
			if (query(order, "reject")) {
				order.setStatus(Order.Status.REJECTED);
				Store.pendingOrders.poll();
			}
		}
		
		System.out.println();
	}
}
