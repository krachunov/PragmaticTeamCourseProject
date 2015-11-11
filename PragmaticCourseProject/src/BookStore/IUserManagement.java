package BookStore;

public interface IUserManagement {

		public int createUser(String username, String password);
		public User logIn(String username, String password);
		
}
