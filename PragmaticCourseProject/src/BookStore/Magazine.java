/**
 * @author Krachunov
 */
package BookStore;

public class Magazine extends Book {

	/**
	 * @nextNumber - after each addition of the element is increased by 1
	 */
	private static int nextNumber = 1;
	private static final int PRODUCTCODE = 200000;

	public Magazine(String name, String author, String genere,
			boolean isBestSaller, double price, int count, String type) {
		super(name, author, price, count);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method setProductID - generate unique ID for every single item as book
	 * number starts with 300 000. For other products will start with another
	 * number
	 */
	@Override
	protected void setProductID() {
		this.productID = PRODUCTCODE + nextNumber;
		nextNumber++;
	}

}
