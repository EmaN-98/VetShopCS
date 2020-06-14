package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Logger;

import connection.ConnectionMake;


public class Login  {

	private static Connection conn;
	private final static String findStatementString = "SELECT * FROM user where username = ? and password = ?";
	private static Logger logger;
	
	public static boolean login(String username, String password) {

		boolean toReturn = false;
		conn = ConnectionMake.getConnection();
		//Connection dbConnection = ConnectionMake.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = conn.prepareStatement(findStatementString);
			findStatement.setString(1, username);
			findStatement.setString(2, password);
			rs = findStatement.executeQuery();
			rs.next();
			if(rs!=null)
				{toReturn =true;}
		} catch (SQLException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		} finally {
			ConnectionMake.close(rs);
			ConnectionMake.close(findStatement);
			ConnectionMake.close(conn);
		}
		return toReturn;

	}
}
