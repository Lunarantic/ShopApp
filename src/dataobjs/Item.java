package dataobjs;

public final class Item {

	private static int count = 0;
	private Integer id;
	private String name;
	private Double price, quantity;

	private Item() {
		id = ++count;
	}
	
	public Item(String name, Double price, Double quantity) {
		this();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return id.toString() + '\t' + name + '\t' + price + '\t' + quantity;
	}
}
