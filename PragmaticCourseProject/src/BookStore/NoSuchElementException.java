package BookStore;
public class NoSuchElementException extends Exception {

	public NoSuchElementException() {
		super();
	}

	public NoSuchElementException(String message) {
		super(message);
	}

	public NoSuchElementException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
