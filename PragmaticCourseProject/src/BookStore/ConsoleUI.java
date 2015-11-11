package BookStore;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {
	private User currentUser = new User();
	private IUserManagement currentUM;
	private IBookStore currentBS;
	
	ConsoleUI(IBookStore bs, IUserManagement um){
		this.currentBS = bs;
		this.currentUM = um; 
	}
	
	Scanner sc = new Scanner(System.in);
	
	private void loginDialog(){
		do {
			System.out.print("Please enter your username: ");
			String username = sc.nextLine();
			System.out.print("Please enter your password: ");
			String password = sc.nextLine();
			User usr = this.currentUM.logIn(username, password);
			if (usr != null) {
				currentUser = usr;
				break;
			} else {
				System.out.println("Please enter a valid username and password");
			}
		} while (true);
		System.out.println("You have successufully logged in as: " + currentUser.getUsername());
		System.out.println("------------------");
	}
	
	public Product searchDialog() {
		do {	
			System.out.println("Please enter the name of wanted product: ");
			String wantedItem = sc.nextLine();
			List<Product> listMatched = this.currentBS.searchByTitle(wantedItem);
			if((listMatched == null) || (listMatched.size() == 0)) {
				System.out.println("We are sorry, but we couldn`t find items containing your search criteria.");
				String answer;
				do {
					System.out.println("Would you like to look for another item? (Y/N)");
					answer = sc.nextLine().toLowerCase();
				} while (!(answer.equals("y") || answer.equals("n")));
				if(answer.equals("y")) {
					continue;
				}
				return null;
			}
			for (int i = 0; i < listMatched.size(); i++) {
				System.out.println(i + ". " + listMatched.get(i).getTitle() + " \\ " + listMatched.get(i).getAuthor()  + " \\ " + listMatched.get(i).getPrice() + " \\ " + listMatched.get(i).getCount() );		
			}
			System.out.println("This is the list with the items that match your search criteria.");
			int choice;
			do {
				System.out.println("Witch one are you looking for?");
				String result = sc.nextLine();
				try {
					choice = Integer.parseInt(result);
				} catch (Exception e) {
					System.out.println("Please enter a valid number.");
					continue;
				}
				if((choice >= 0) && (choice < listMatched.size())) {
					return listMatched.get(choice);
				}
			} while (true);
		} while (true);	
	}
	
	public void sellDialog() {
		Product forSellItem = searchDialog();
		if(forSellItem != null) {
			try {
				Boolean res = this.currentBS.sellProduct(forSellItem);
				if (res == true) {
					System.out.println("The product was successefully sold out!");
				}
			} catch (NoSuchElementException e) {
				System.out.println("Error: " + e.toString());
			}
		}
		System.out.println("------------------");
	}
	
	
	public void addNewItemDialog() {
		System.out.print("Please enter the name of the product: ");
		String title = sc.nextLine();
		System.out.print("Please enter an author: "); 
		String author = sc.nextLine();
		
		double price = 0; 
		do {
			System.out.print("Please enter the selling price of the product: ");
			String result = sc.nextLine();
			try {
				price = Double.parseDouble(result);
				break;
			} catch (Exception e) {
				System.out.println("Please, use numbers and a coma as decimal separator. ");
				continue;
			}
		} while (true); 
		
		int count = 0;
		do {
			System.out.print("Please enter the number of the product on stock: ");
			String result = sc.nextLine();
			try {
				count = Integer.parseInt(result);
				break;
			} catch (Exception e) {
				System.out.println("\nPlease enter a valid number. ");
				continue;
			}
		} while (true);
		int choice;
		String typeOP = "Book";
		do {
			System.out.println("Please choose one of the following types of the product: ");
			System.out.println("	1.Music");
			System.out.println("	2.Book");
			System.out.println(": ");
			String result = sc.nextLine();
			try {
				choice = Integer.parseInt(result);
			} catch (Exception e) {
				System.out.println("\nPlease enter a valid number. ");
				continue;
			}
			if ((choice >= 1) && (choice <= 2)) {
				break;
			}
		} while (true);
		switch (choice) {
		case 1:
			typeOP = "Music";
			break;
		case 2:
			typeOP = "Book";
			break;
		}
		System.out.println("Please review the following information: ");
		System.out.println("Type of the product: " + typeOP);
		System.out.println("Name of the product: " + title);
		System.out.println("Name of the author: " + author);
		System.out.println("Selling Price: " + price);
		System.out.println("Pieces on stock: " + count);
		
		String res;
		do {
			System.out.println("Please confirm to add the new Product. Y/N");
			res = sc.nextLine().toLowerCase();
		} while ( !((res.equals("y")) || (res.equals("n"))) );
		int newUUID;
		if (res.equals("y")) {
			newUUID = this.currentBS.createNewProduct(title, author, price, count, typeOP);
			System.out.println("You have successufully created a new Item with the following unique ID: " + newUUID);
		}
		System.out.println("------------------");
	}
	
	public void modifyItemDialog() {	
		Product prToModify = searchDialog();
		if(prToModify != null) {
			String prToModifyTypeOfProduct = prToModify.getTypeOfProduct();
			int prToModifyProductID = prToModify.getProductID();
			String prToModifyTitle = prToModify.getTitle();
			String prToModifyAuthor = prToModify.getAuthor();
			double prToModifyPrice = prToModify.getPrice();
			int prToModifyCount = prToModify.getCount();
			boolean loop = true;
			do {
				System.out.println("The current item information is the following:");
				System.out.println("Type of the product: " + prToModifyTypeOfProduct);
				System.out.println("Name of the product: " + prToModifyTitle);
				System.out.println("Name of the author: " + prToModifyAuthor);
				System.out.println("Selling Price: " + prToModifyPrice);
				System.out.println("Pieces on stock: " + prToModifyCount);
				System.out.println("Witch field would you wish to modify?");
				System.out.println("	1. Type of Product");
				System.out.println("	2. Title");
				System.out.println("	3. Author");
				System.out.println("	4. Selling price");
				System.out.println("	5. Pieces on stock");
				System.out.println("	6. Exit to confirm modifications");
				int choice = 0;
				do {
					
					String result = sc.nextLine();
					try {
						choice = Integer.parseInt(result);
					} catch (Exception e) {
						System.out.println("\nPlease enter a valid number. ");
						continue;
					}
					if ((choice >= 1) && (choice <= 6)) {
						break;
					}
				} while (true);
				switch (choice) {
				case 1:
					int prChoice;
					do {
						System.out.println("Please choose one of the following types of the product: ");
						System.out.println("	1.Music");
						System.out.println("	2.Book");
						System.out.println("	3.Exit");
						System.out.println(": ");
						String result = sc.nextLine();
						try {
							prChoice = Integer.parseInt(result);
						} catch (Exception e) {
							System.out.println("\nPlease enter a valid number. ");
							continue;
						}
						if ((prChoice >= 1) && (prChoice <= 3)) {
							break;
						}
					} while (true);
					switch (prChoice) {
					case 1:
						prToModifyTypeOfProduct = "Music";
						break;
					case 2:
						prToModifyTypeOfProduct = "Book";
						break;
					case 3:
						break;
					}
					break;
				case 2:
					System.out.println("Please enter the new title of Product: ");
					prToModifyTitle = sc.nextLine();
					break;
				case 3:
					System.out.println("Please enter the new author: ");
					prToModifyAuthor = sc.nextLine();
					break;
				case 4:
					System.out.println("Please enter a new selling price: ");
					prToModifyPrice = sc.nextDouble();
					break;
				case 5:
					System.out.println("Please enter the quantity on stock: ");
					prToModifyCount = sc.nextInt();
					break; 
				case 6:
					System.out.println("Please review the following information: ");
					System.out.println("Unique ID of the product: " + prToModifyProductID);
					System.out.println("Type of the product: " + prToModifyTypeOfProduct);
					System.out.println("Name of the product: " + prToModifyTitle);
					System.out.println("Name of the author: " + prToModifyAuthor);
					System.out.println("Selling Price: " + prToModifyPrice);
					System.out.println("Pieces on stock: " + prToModifyCount);					
					String res;
					do {
						System.out.println("Would you wish to confirm the changes? (Y/N)");
						res = sc.nextLine().toLowerCase();
					} while ( !((res.equals("y")) || (res.equals("n"))) );
					if (res.equals("y")) {
						prToModify.setTitle(prToModifyTitle);
						prToModify.setAuthor(prToModifyAuthor);
						prToModify.setPrice(prToModifyPrice);
						prToModify.setCount(prToModifyCount);
						prToModify.setTypeOfProduct(prToModifyTypeOfProduct);
						loop = false;
						try {
								this.currentBS.modifyProduct(prToModify);
							System.out.println("The product is successufully modified!");
						} catch (NoSuchElementException e) {
							System.out.println("Error: " + e.toString());
							e.printStackTrace();
						}
					} else {
						System.out.println("The product was not modified. ");
					}
					break;
				}
			} while (loop);
				
		} else {
			System.out.println("We couldn`t find such product!");
		}
		System.out.println("------------------");
	}

	public void removeItemDialog() {
		Product itemForRemove = searchDialog();
		if(itemForRemove != null) {
			try {
				Boolean res = this.currentBS.removeProduct(itemForRemove);
				if (res == true) {
					System.out.println("The product was successefully deleted");
				}
			} catch (NoSuchElementException e) {
				System.out.println("Error: " + e.toString());
			}
		}
		System.out.println("------------------");
	}	
	
	private void listAllItemsDialog() {
		List <Product> list = currentBS.printAllProducts();
		System.out.println("List of products:");
		for(Product pr : list) {
			System.out.println(pr.getProductID() + " | " + pr.getTitle() + " | " + pr.getAuthor()+ " | " + pr.getPrice()+ " | " + pr.getCount()+ " | " + pr.getTypeOfProduct());
		}	
	}
	
	
	public void displayMenu() {
	loginDialog();

	boolean exit = false;
	do {
		System.out.println("Please choose one of following options:");
		System.out.println("         1. Search for Item");
		if ( currentUser.permissions.isCanSellProduct())
			System.out.println("         2. Sell Item");
		if (currentUser.permissions.isCanCreateNewProduct())
			System.out.println("         3. Add a new Item");
		if (currentUser.permissions.isCanModifyProduct())
			System.out.println("         4. Modify an Item");
		if (currentUser.permissions.isCanRemoveProduct())
			System.out.println("         5. Remove an Item");
		System.out.println("         6. List all existing items");
		System.out.println("         7. Log in as another user");
		System.out.println("         8. Exit");
		
		System.out.print("Choose: ");
		
		int choice;
		String res = sc.nextLine();
		try {
			choice = Integer.parseInt(res);
		} catch (Exception e) {
			System.out.println("Please enter a valid number.");
			continue;
		}
		System.out.println("\n");
		switch (choice) {
			case 1:
				searchDialog();
				break;
			case 2:
				sellDialog();
				break;
			case 3:
				addNewItemDialog();
				break;
			case 4:
				modifyItemDialog();
				break;
			case 5:
				removeItemDialog();
				break;
			case 6:
				listAllItemsDialog();
				break;
			case 7:
				loginDialog();
				break;
			case 8:
				exit = true;
				break;
			default:
				System.out.println("Please enter a valid number.");
		}
		System.out.println("------------------");
	} while (!exit);
	System.out.println("Goodbye.");
	sc.close();
}


}