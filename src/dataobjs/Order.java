package dataobjs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Order {
	
	public enum Status {
		APPROVED, PENDING, REJECTED;
	}
	
	private static int count = 0;

	private List<Product> products;
	private User user;
	private Status status;
	private int id;

	private Order() {
		products = new ArrayList<>();
		id = ++count;
		setStatus(Status.PENDING);
		Store.pendingOrders.add(this);
	}
	
	public Order(User user) {
		this();
		this.user = user;
		List<Order> orders = Store.userOrdersMap.get(user);
		if (orders == null) {
			Store.userOrdersMap.put(user, new LinkedList<>());
		}
		orders.add(this);
	}
	
	public User getUser() {
		return user;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("User <");
		stringBuilder.append(user.toString());
		stringBuilder.append(">\n");
		
		stringBuilder.append("Status <");
		stringBuilder.append(status.toString());
		stringBuilder.append(">\n");
		
		stringBuilder.append("Items:");
		for (Product product: products) {
			stringBuilder.append("\n");
			stringBuilder.append(product.toString());
		}
		return stringBuilder.toString();
	}

	public int getId() {
		return id;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public boolean addProduct(Product product) {
		return products.add(product);
	}
	
	public Product getProduct(int index) {
		if (index < 0 || index >= products.size()) return null;
		return products.get(index);
	}
	
	public boolean removeProduct(int index) {
		if (index < 0 || index >= products.size()) return false;
		return products.remove(index) != null;
	}
}
