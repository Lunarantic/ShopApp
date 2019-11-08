package logics;

import dataobjs.Item;
import dataobjs.Store;
import util.IOUtil;
import util.Printer;

public class ItemManagement {

	private void itemMenu() {
		System.out.println("What do you want to do:\n"
				+ "1. Add new Item\n"
				+ "2. Update an Item\n"
				+ "3. View all Items\n"
				+ "4. Exit\n"
				+ "5. Close App");
	}
	
	public void run() {
		System.out.println("You have selected to change your inventory:");
		
		runner:
			while(true) {
				System.out.println();
				itemMenu();
				int option = IOUtil.getInt();
				IOUtil.ignoreLine();
				System.out.println();
				
				switch (option) {
					case 1: addItem(); break;
					case 2: updateAnItem(); break;
					case 3: viewAllItems(); break;
					case 4: break runner;
					case 5: System.exit(0);
					default: System.out.println("No such Option"); break;
				}
			}
	}
	
	private void addItem() {
		System.out.println("You have selected to add item");
		
		System.out.println("Name:");
		String name = IOUtil.getStringLine();
		
		System.out.println("Price:");
		Double price = IOUtil.getDouble();
		
		System.out.println("Quantity:");
		Double quantity = IOUtil.getDouble();
		
		Item item = new Item(name, price, quantity);
		Store.itemsMap.put(item.getId(), item);
	}
	
	private void updateAnItem() {
		System.out.println("Which item do you want to update: <Provide ID>");
		int id = IOUtil.getInt();
		IOUtil.ignoreLine();
		
		Item item = Store.itemsMap.get(id);
		
		System.out.println("The item selected is:\n" + item);
		
		System.out.println("Do like to Name (Y/N)?");
		char c = Character.toLowerCase(IOUtil.getChar());
		if (c == 'y') {
			System.out.println("New name:");
			item.setName(IOUtil.getStringLine());
		}
		
		System.out.println("Do like to Price (Y/N)?");
		c = Character.toLowerCase(IOUtil.getChar());
		if (c == 'y') {
			System.out.println("New Price:");
			item.setPrice(IOUtil.getDouble());
		}
		
		System.out.println("Do like to Quanity (Y/N)?");
		c = Character.toLowerCase(IOUtil.getChar());
		if (c == 'y') {
			System.out.println("New Quanity:");
			item.setQuantity(IOUtil.getDouble());
		}

		System.out.println("You have successfully update the Item");
	}
	
	private void viewAllItems() {
		Printer.printWithColor("You chosen to view all items.", Printer.Color.YELLOW);
		
		System.out.println("\nID\tName\tPrice\tQuantity");
		
		for (Item item: Store.itemsMap.values()) {
			System.out.println(item);
		}
	}
}
