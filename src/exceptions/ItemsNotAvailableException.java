package exceptions;


public class ItemsNotAvailableException extends Exception {

	private static final long serialVersionUID = -3519726409775552391L;

	public ItemsNotAvailableException() {
		super();
	}
	
	public ItemsNotAvailableException(String message) {
		super(message);
	}
}
