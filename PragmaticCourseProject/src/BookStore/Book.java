/**
 * @author Krachunov
 */
package BookStore;

public class Book extends Product {

	/**
	 * @nextNumber -serial number of the item
	 * @PRODUCTCODE - type number of the item
	 * @productID = PRODUCTCODE+nextNumber
	 */
	private static int nextNumber = 1;
	private static final int PRODUCTCODE = 100000;

	public Book(String title, String author, double price, int count) {
		super.setTitle(title);
		super.setAuthor(author);
		super.setPrice(price);
		super.setCount(count);

		setProductID();
	}

	public static int getNextNumber() {
		return nextNumber;
	}

	public static void setNextNumber(int nextNumber) {
		Book.nextNumber = nextNumber;
	}

	/**
	 * Method setProductID - generate unique ID for every single item as book
	 * number starts with 100 000. For other products will start with another
	 * number
	 */
	@Override
	protected void setProductID() {
		this.productID = PRODUCTCODE + nextNumber;
		nextNumber++;
	}

}
