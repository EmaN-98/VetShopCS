package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import connection.ConnectionMake;

import model.User;

public class UserOp {

	private static Connection conn;
	private final static String insertUser = "INSERT INTO user (name,email,username,password)" + " VALUES (?,?,?,?)";
	private final static String deleteUser = "DELETE FROM user WHERE userId = ? ";
	private final static String editUser= "UPDATE user SET name=?, email=?, username=?, password=? WHERE userId= ? ";
	private final static String findStatementString = "SELECT * FROM user where userId = ?";
	private static Logger logger;
	
	public static int insertUser(User u) {

		PreparedStatement stmt = null;
		ResultSet res=null;

		int userId = 0 ;
		try {

			String name=u.getName();
			String email=u.getEmail();
			String username=u.getUsername();
			String password=u.getPassword();

			boolean ok1=false;
			boolean ok2=false;
			boolean ok3=false;

			if(Pattern.matches("[a-zA-Z]+" ,name)&&!name.isEmpty()) {
				ok1=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again name!");
			}

			if (Pattern.matches("[a-zA-Z0-9]+",username) && !username.isEmpty() ) {

				ok2=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Check again username!");
				ok2=false;
			}
			if(Pattern.matches("([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)" ,email)&&!email.isEmpty()) {
				ok3=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again email!");
			}
			if(ok3&&ok1&&ok2) {
				conn =ConnectionMake.getConnection();
				stmt = conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, name);
				stmt.setString(2, email);
				stmt.setString(3, username);
				stmt.setString(4, password);

				stmt.executeUpdate();

				res = stmt.getGeneratedKeys();
				JOptionPane.showMessageDialog(null,"User successfully added.");
			}
		} catch (Exception exc) {

			logger.severe(Arrays.toString(exc.getStackTrace()));
			
			JOptionPane.showMessageDialog(null, "Sorry, cannot insert user. Try another ID");

		} finally {
			ConnectionMake.close(res);
			ConnectionMake.close(stmt);
			ConnectionMake.close(conn);
		}
		
		return userId;

	}

	public static void deleteUser(int id) {
		PreparedStatement stmt = null;
		ResultSet res=null;
		try {
	 		if (Pattern.matches("[0-9]+",String.valueOf(id)) && !(String.valueOf(id).isEmpty()) ) {

				conn = ConnectionMake.getConnection();
				stmt = conn.prepareStatement(deleteUser, Statement.RETURN_GENERATED_KEYS);
				stmt.setLong(1, id);
				stmt.executeUpdate();
				res = stmt.getGeneratedKeys();
				JOptionPane.showMessageDialog(null,"User successfully deleted.");}
		} catch (SQLException exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
			JOptionPane.showMessageDialog(null, "Sorry, cannot delete user.");

		} finally {
			ConnectionMake.close(res);
			ConnectionMake.close(stmt);
			ConnectionMake.close(conn);
		}
	}

	public static User editUser(User u) {

		PreparedStatement stmt = null;
		ResultSet res=null;
		try {

			String name=u.getName();
			String email=u.getEmail();
			String username=u.getUsername();
			String password=u.getPassword();

			boolean ok1=false;
			boolean ok2=false;
			boolean ok3=false;

			if(Pattern.matches("[a-zA-Z]+" ,name)&&!name.isEmpty()) {
				ok1=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again name!");
			}

			if (Pattern.matches("[a-zA-Z0-9]+",username) && !username.isEmpty() ) {

				ok2=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Check again username!");
				ok2=false;
			}
			if(Pattern.matches("([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)" ,email)&&!email.isEmpty()) {
				ok3=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again email!");
			}
			if(ok3&&ok1&&ok2) {
				conn = ConnectionMake.getConnection();
				stmt = conn.prepareStatement(editUser, Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, name);
				stmt.setString(2, email);
				stmt.setString(3, username);
				stmt.setString(4, password);
				stmt.setInt(5, u.getUserId());

				int changes =stmt.executeUpdate();
				res = stmt.getGeneratedKeys();

				if(changes > 0) { 
					JOptionPane.showMessageDialog(null,"User successfully edited.");
					return u;
				} else {
					return null;
				}
			}
		} catch (Exception exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
			JOptionPane.showMessageDialog(null,
					"Sorry, cannot edit user. Check again the data desired");

		} finally {
			ConnectionMake.close(res);
			ConnectionMake.close(stmt);
			ConnectionMake.close(conn);
		}
		return null;
	}


	public static List<Object> findAll() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		List<Object> users = new ArrayList<Object>();
		try {
			conn = ConnectionMake.getConnection();
			 preparedStatement = conn.prepareStatement("SELECT * FROM user");
			 resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User u = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
				users.add(u);
			}
		} catch (SQLException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}finally {
			ConnectionMake.close(resultSet);
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(conn);
		}
		return (ArrayList<Object>) users;
	}

	public static User findById(int userId) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		try {
			if (Pattern.matches("[0-9]+",String.valueOf(userId)) && !String.valueOf(userId).isEmpty() ) {
				conn = ConnectionMake.getConnection();
				 preparedStatement = conn.prepareStatement(findStatementString);
				preparedStatement.setInt(1, userId);

				 resultSet = preparedStatement.executeQuery();

		 		if (resultSet.next()) {
					return new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
					
				}
			}
		} catch (Exception e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
			JOptionPane.showMessageDialog(null,"Sorry, cannot show user information. Check ID");
		}finally {
			ConnectionMake.close(resultSet);
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(conn);
		}

		return null;
	}

	public static User findByName(String name) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		try {
			if (Pattern.matches("[a-zA-Z]+",name) && !name.isEmpty() ) {
				conn = ConnectionMake.getConnection();
				 preparedStatement = conn.prepareStatement("SELECT * from user WHERE name=?");
				preparedStatement.setString(1, name);

				 resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					return new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
					
				}
			}
		} catch (Exception e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
			JOptionPane.showMessageDialog(null,"Sorry, cannot show user information. Check name");
		}finally {
			ConnectionMake.close(resultSet);
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(conn);
		}

		return null;
	}

	public static String findDoctorName(String doctorUsername) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		try {
			if (Pattern.matches("[a-zA-Z]+",doctorUsername) && !doctorUsername.isEmpty() ) {
				conn = ConnectionMake.getConnection();
				 preparedStatement = conn.prepareStatement("SELECT * FROM user where username = ?");
				preparedStatement.setString(1, doctorUsername);

				 resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					User u = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
					return u.getName();
				}
			}
		} catch (Exception e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
			JOptionPane.showMessageDialog(null,"Sorry, cannot find this doctor. Check doctorName");
		}finally {
			ConnectionMake.close(resultSet);
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(conn);
		}

		return null;
	}



}
