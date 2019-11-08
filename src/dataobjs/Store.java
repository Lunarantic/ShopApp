package dataobjs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Store {

	public static Queue<Order> pendingOrders = new LinkedList<>();
	public static Map<User, List<Order>> userOrdersMap = new HashMap<>();
	public static List<Order> allOrders = new LinkedList<>();
	public static Map<Integer, Item> itemsMap = new HashMap<>();
	public static Map<String, Customer> custMap = new HashMap<>();
}
