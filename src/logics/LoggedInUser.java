package logics;

import java.util.LinkedList;
import java.util.List;

import dataobjs.Customer;
import dataobjs.Order;
import dataobjs.Store;
import util.IOUtil;
import util.Printer;

public class LoggedInUser {
	private Customer customer;
	
	private LoggedInUser() {}
	
	public LoggedInUser(Customer  customer) {
		this();
		this.customer = customer;
	}

	public void run() {
		loggedin:
			while(true) {
				printMenu();
				int key = IOUtil.getInt();
				IOUtil.ignoreLine();
				
				switch (key) {
					case 1: viewCataloge(); break;
					case 2: placeOrder(); break;
					case 3: viewOrders(); break;
					case 4: break loggedin;
					case 5: System.exit(0);
					default: System.out.println("Wrong option"); break;
				}
			}
	}

	private void printMenu() {
		System.out.println("Please choose the option to perform\n"
				+ "1. View Catalogue\n"
				+ "2. Place Order\n"
				+ "3. View your Orders\n"
				+ "4. Logout\n"
				+ "5. CloseApp");
	}
	
	private void viewOrders() {
		try {
			Store.userOrdersMap.get(customer).forEach((O) -> {System.out.println(O);});
		} catch (NullPointerException e) {
			Printer.printWithColor("You haven't Placed any orders yet.", Printer.Color.YELLOW);
		}
	}
	
	private void viewCataloge() {
		try {
			Store.itemsMap.forEach((K,V) -> {System.out.println(V);});
		} catch (NullPointerException e) {
			Printer.printWithColor("", Printer.Color.YELLOW);
		}
	}
	
	private void placeOrder() {
		Order order = new OrderPlacementmanager().run();
		
		if (order != null) {
			List<Order> orders = Store.userOrdersMap.get(customer);
			if (orders == null) {
				orders = new LinkedList<Order>();
				Store.userOrdersMap.put(customer, orders);
			}
			orders.add(order);
		}
	}
}
