/**
 * @author Krachunov
 */
package BookStore;

import java.util.*;

public interface IBookStore {

		public int createNewProduct(String title, String author, double price, int count, String typeOfProduct);
		
		public List<Product> searchByTitle(String title);

		public List<Product> printAllProducts();
		
		public boolean sellProduct(Product product) throws NoSuchElementException;

		public boolean removeProduct(Product product) throws NoSuchElementException;
		
		public boolean modifyProduct(Product product) throws NoSuchElementException;
	}

