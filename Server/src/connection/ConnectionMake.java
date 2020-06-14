package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ConnectionMake {
	private static final Logger LOGGER = Logger.getLogger(ConnectionMake.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/vetshop";
	private static final String USER = "emanuelan";
	private static final String PASS = "passemanuela";
	//	private static Connection connection = null;
	private Statement stmt = null;

	private static ConnectionMake singleInstance = new ConnectionMake();

	private ConnectionMake() {

		try {
			Class.forName(DRIVER);
			Connection myConn = DriverManager.getConnection(DBURL, USER, PASS);

			stmt = myConn.createStatement();

		} catch (Exception exc) {
			LOGGER.severe(Arrays.toString(exc.getStackTrace()));
			
		}
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			LOGGER.severe(Arrays.toString(e.getStackTrace()));
			
		}
		return connection;
	}

	public static Connection getConnection() {
		return singleInstance.createConnection();

	}

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}

	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}

	public ResultSet executeStatement(final String query) {
		try {
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			LOGGER.severe(Arrays.toString(e.getStackTrace()));
			
		}
		return null;
	}



}
