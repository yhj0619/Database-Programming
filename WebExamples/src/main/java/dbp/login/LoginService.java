package dbp.login;

import dbp.beans.User;

public class LoginService {
	public User login(String id, String pw) {
		System.out.println(id + ", " + pw);
		
		// process login...
		
		User user = new User("Jain", 22, "010-3333-4444"); // login user info
		return user; 
	}
}
