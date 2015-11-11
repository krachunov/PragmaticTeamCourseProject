package BookStore;

public class UserManagement implements IUserManagement{
	private int currentNumOfUsers = 0;
	private User[] users = new User[10];
	
	public User selectUserById(int userId){
		return users[userId];
	}
	
	public int createUser(String username, String password){
		this.users[currentNumOfUsers] = new User();
		this.users[currentNumOfUsers].setUsername(username);
		this.users[currentNumOfUsers].setPassword(password);
		this.users[currentNumOfUsers].permissions = new Permissions();
		return currentNumOfUsers++;
	}
		
	public User logIn(String username, String password) {
		for(int i = 0; i< currentNumOfUsers; i++) {
			if (username.equals(this.users[i].getUsername())) {
				if (this.users[i].getPassword().equals(password))
					return this.users[i];
				else
					return null;
			}
		}
		return null;
	}
}


