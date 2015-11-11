package BookStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BookStore implements IBookStore {
	private String nameStore;
	private static int storeID;
	private List<Product> listOfProducts;

	public BookStore(String name) {
		setNameStore(name);
		setStoreID(storeID);
		listOfProducts = new ArrayList<Product>();
	}

	public String getNameStore() {
		return nameStore;
	}

	public void setNameStore(String nameStore) {
		this.nameStore = nameStore;
	}

	public static int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID++;
	}

	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	public void sortProduct() {
		Collections.sort(listOfProducts);
	}

	/**
	 * 
	 * @param title
	 *            - searching item
	 * @return - Find products or null
	 */
	public Product searchProduct(String title) {
		for (Product product : listOfProducts) {
			if (product.getTitle().contains(title.toLowerCase())) {
				return product;
			} else {
				return null;
			}
		}
		return null;
	}

	@SuppressWarnings("null")
	@Override
	public int createNewProduct(String title, String author, double price,
			int count, String typeOfProduct) {
		String type = typeOfProduct.toLowerCase();
		switch (type) {
		case "book":
			Book newBook = new Book(title, author, price, count);
			listOfProducts.add(newBook);
			return newBook.getProductID();
		case "music":
			Music newMusic = new Music(title, author, price, count);
			listOfProducts.add(newMusic);
			return newMusic.getProductID();
		default:
			throw new IllegalArgumentException("Choose a book or music");

		}
	}

	/**
	 * return List of Product or null
	 */
	@SuppressWarnings("null")
	@Override
	public List<Product> searchByTitle(String title) {
		List<Product> resultsFound = null;
		for (Product product : listOfProducts) {
			if (product.getTitle().matches(
					"(.*)" + title.toLowerCase() + "(.*)")) {
				resultsFound.add(product);
			}
		}
		return resultsFound;
	}
	
//	public List<Product> searchByTitle(String title) {
//		List<Product> wantedItems = new ArrayList<Product>(); 
//		for(Product product : listOfProducts){
//	        if(product.getTitle().toLowerCase().contains(title.toLowerCase())) {
//	        	wantedItems.add(product);
//	        }
//	    }
//		return wantedItems;
//	}

	@Override
	public List<Product> printAllProducts() {
		List<Product> listToPrint = getListOfProducts();
		return listToPrint;
	}

	@Override
	public boolean sellProduct(Product product) throws NoSuchElementException {
		Product productForSell = product;
		if (productForSell == null) {
			// return false;
			throw new NoSuchElementException("There is no product");
		} else if (productForSell.getCount() < 1) {
			// return false;
			throw new NoSuchElementException("No quantities");
		} else {
			int countProduct = productForSell.getCount();
			productForSell.setCount(countProduct - 1);
			return true;
		}
	}

	/**
	 * @return true if product remove successfully, or false
	 */
	@Override
	public boolean removeProduct(Product product) throws NoSuchElementException {
		Product productForRemove = product;
		if (listOfProducts.contains(product)) {
			listOfProducts.remove(product);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modifyProduct(Product product) throws NoSuchElementException {
		for (Product currentProduct : listOfProducts) {
			if (currentProduct.getProductID() == product.getProductID()) {
				currentProduct = product;
				return true;
			}

		}
		return false;

	}

}
