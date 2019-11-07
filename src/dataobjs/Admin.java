package dataobjs;


public final class Admin extends User {
	
	private Admin() {
		super();
		setId("admin");
		setPassword(generatePassword());
	}
	
	private static Admin admin = null;
	
	public static Admin getInstance() {
		if (admin == null) {
			Admin.admin = new Admin();
		}
		
		return admin;
	}

	@Override
	protected String generatePassword() {
		return "admin";
	}
}
