package BookStore;

public class Music extends Product {

	/**
	 * @nextNumber - after each addition of the element is increased by 1
	 */
	private static int nextNumber = 1;
	private static final int PRODUCTCODE = 300000;

	public Music(String title, String author, double price, int count) {
		super.setTitle(title);
		super.setAuthor(author);
		super.setPrice(price);
		super.setCount(count);

		setProductID();
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
