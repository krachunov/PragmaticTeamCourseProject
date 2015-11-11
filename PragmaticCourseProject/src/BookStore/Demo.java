package BookStore;

import java.util.List;

public class Demo {
		static UserManagement um = new UserManagement();
		
		public static void main(String[] args) {
			int uid = um.createUser("admin", "admin");
			um.selectUserById(uid).permissions.makeAdmin();

			uid = um.createUser("sales", "sales");
			um.selectUserById(uid).permissions.makeSalesEmployee();
			
			BookStore bs = new BookStore("Store");
			
			Book a = new Book("Java 1", "nqkoi", 10, 0);
			Book d = new Book("Intro to java", "peti", 10, 5);
			Book b = new Book("Building with Java", "drug", 10, 5);
			Book c = new Book("Second intro to Java", "treti", 10, 5);
			Music m = new Music("First album", "author", 10, 5);
			
			List<Product> lis = bs.getListOfProducts();
			lis.add(a);
			lis.add(b);
			lis.add(c);
			lis.add(d);
			lis.add(m);
			
			ConsoleUI ui = new ConsoleUI(bs, um);
			ui.displayMenu();
	}

}
