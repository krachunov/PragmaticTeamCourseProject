package BookStore;

import java.util.List;

public class DemoBookStroe {
	public static void main(String[] args) throws NoSuchElementException {
		BookStore b1 = new BookStore("Store");

		Book a = new Book("A", "nqkoi", 10, 5);
		Book d = new Book("A", "peti", 10, 5);
		Book b = new Book("B", "drug", 10, 5);
		Book c = new Book("B", "treti", 10, 5);

		List<Product> lis = b1.getListOfProducts();
		lis.add(a);
		lis.add(b);
		lis.add(c);
		lis.add(d);

		b1.searchByTitle("Java");

		b1.printAllProducts();
		b1.sortProduct();
		System.out.println();
		b1.printAllProducts();

	}

}
