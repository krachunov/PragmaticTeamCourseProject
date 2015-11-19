/**
 * @author Krachunov
 */
package BookStore;

public abstract class Product implements Comparable<Product> {

	private String title;
	private String author;
	protected double price;
	protected int count;
	public int productID;
	private String typeOfProduct;

	public String getTypeOfProduct() {
		return this.typeOfProduct;
	}

	public void setTypeOfProduct(String typeOfProduct) {
		this.typeOfProduct = typeOfProduct;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getProductID() {
		return productID;
	}

	/**
	 * Method setProductID - generate unique ID for every single item as book
	 * number starts with 100 000. For other products will start with another
	 * number
	 */
	protected abstract void setProductID();

	/**
	 * sort product to title and then by ID
	 */
	@Override
	public int compareTo(Product product) {
		int compare = this.getTitle().compareToIgnoreCase(product.getTitle());
		if (compare == 0) {
			if (this.getProductID() == product.getProductID()) {
				return 0;
			} else if (this.getProductID() > product.getProductID()) {
				return 1;
			} else {
				return -1;
			}
		} else if (compare > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	public String toString() {
		return "Title: " + getTitle() + ", author: " + getAuthor()
				+ ", count: " + getCount() + ", ID: " + getProductID();
	}

}
