package BookStore;

public class UserManagement implements IUserManagement {
	private int currentNumOfUsers = 0;
	private int numberOfPosibleUsers = 10;
	private User[] users = new User[numberOfPosibleUsers];

	public User selectUserById(int userId) {
		return users[userId];
	}

	public int createUser(String username, String password) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.permissions = new Permissions();
		this.users[currentNumOfUsers] = newUser;
		return currentNumOfUsers++;
	}

	public User logIn(String username, String password) {
		for (int i = 0; i < currentNumOfUsers; i++) {
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
