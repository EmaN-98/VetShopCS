package bll;

import java.util.List;

import model.User;
import operations.UserOp;

public class UserBLL {

	public static List<Object> viewAllUsers() {

		return UserOp.findAll();
	}

	public static User viewUser(int id) {

		return UserOp.findById(id);
	}

	public static int insertUser(User user) {

		return UserOp.insertUser(user);
	}

	public static User editUser(User user) {

		return UserOp.editUser(user);
	}

	public static void deleteUser(int id) {

		UserOp.deleteUser(id);
	}

}
