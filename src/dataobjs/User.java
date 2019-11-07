package dataobjs;

public abstract class User {

	private String id, password;
	
	public boolean verifyPassword(String password) {
		if (null == password || !password.equals(this.password)) return false;
		return true;
	}
	
	public boolean setPassword(String oldPass, String newPass) {
		if (oldPass == null || newPass == null || !oldPass.equals(password)) return false;
		password = newPass;
		return true;
	}
	
	public String getId() {
		return id;
	}
	
	protected void setId(String id) {
		this.id = id;
	}

	protected void setPassword(String password) {
		this.password = password;
	}
	
	public String getDefaultPassword() {
		if (password == null) {
			setPassword(generatePassword());
		}
		return password;
	}
	
	public boolean verifyId(String id) {
		if (id == null || !id.equals(this.id)) return false;
		return true;
	}
	
	protected abstract String generatePassword();
}
