package bll;

import operations.Login;

public class LoginBLL {

	public static boolean login(String username, String password) {

	 	return Login.login(username,password);
	}

}
