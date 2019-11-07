package dataobjs;

import util.Checks;

public final class Customer extends User {

	private String name, address;
	
	private static int count = 0;
	
	private Customer() {
		super();
		setId("cus" + (++count));
	}
	
	public Customer(String name, String address) {
		this();
		this.address = address;
		this.name = name;
	}

	public String getName() {
		if (name == null) return "";
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public boolean setAddress(String address) {
		if (!Checks.isEmpty(address)) {
			this.address = address;
			return true;
		}
		return false;
	}

	@Override	
	protected String generatePassword() {
		if (name.length() > 4) {
			return name.substring(0, 2) + name.substring(name.length() - 2, name.length());
		}
		return getName();
	}
	
	@Override
	public String toString() {
		return getId() + '\t' + name + '\t' + address;
	}
}
