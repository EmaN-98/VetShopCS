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

import model.Stock;

public class StockOp { 

	private static Connection conn;
	private final static String updateStock= "UPDATE stock SET item=?, itemQuantity=? WHERE itemId= ? ";
	private final static String findStatementString = "SELECT * FROM stock";
	private static Logger logger;


	public static Stock updateStock(Stock s) {

		PreparedStatement stmt = null;
		ResultSet res=null;
		try {
			String item=s.getItem();
			int itemQuantity=s.getItemQuantity();

			int id=s.getItemId();

			boolean ok1=false;
			boolean ok2=false;
			boolean ok3=false;

			if(Pattern.matches("[a-z A-Z]+" ,item)&&!item.isEmpty()) {
				ok1=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again name!");
			}


			if (Pattern.matches("[0-9]+",String.valueOf(itemQuantity)) && !String.valueOf(itemQuantity).isEmpty() ) {

				ok2=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Check again quantity!");
			}


			if (Pattern.matches("[0-9]+",String.valueOf(id)) && !String.valueOf(id).isEmpty() ) {

				ok3=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Check again ID!");
			}

			if(ok3&&ok1&&ok2) {


				conn = ConnectionMake.getConnection();
				stmt = conn.prepareStatement(updateStock, Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, item);
				stmt.setInt(2, itemQuantity);
				stmt.setInt(3, id);

				int changes =stmt.executeUpdate();
				res = stmt.getGeneratedKeys();

				if(changes > 0) { 
					JOptionPane.showMessageDialog(null,"Stock successfully updated.");
					return s;
				} else {
					return null;
				}


			}
		} catch (Exception exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
			JOptionPane.showMessageDialog(null,
					"Sorry, cannot update stock. Check again the data desired");

		} finally {
			ConnectionMake.close(res);
			ConnectionMake.close(stmt);
			ConnectionMake.close(conn);
		}
		return null;
	}

	public static boolean checkStock() {
		boolean ok=true;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		List<Object> stock = new ArrayList<>();
		try {
			conn = ConnectionMake.getConnection();
			 preparedStatement = conn.prepareStatement("SELECT * from stock WHERE itemQuantity=0");
			 resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ok=false;
				stock.add(ok);
			}
		} catch (SQLException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}finally {
			ConnectionMake.close(resultSet);
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(conn);
		}
		return ok;
	}

	public static void decreaseQuantity() {
		PreparedStatement preparedStatement=null;
		
		try {
			conn = ConnectionMake.getConnection();
			 preparedStatement = conn.prepareStatement("UPDATE stock SET itemQuantity = itemQuantity - 1");

			int changes =preparedStatement.executeUpdate();

			System.out.println("changes:"+changes);
		} catch (SQLException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}finally {
		
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(conn);
		}
	}

	public static List<Object> viewAll() {

		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		List<Object> stock = new ArrayList<>();
		try {
			conn = ConnectionMake.getConnection();
			 preparedStatement = conn.prepareStatement(findStatementString);
			 resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Stock s=new Stock(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3));
				stock.add(s);
			}
		} catch (SQLException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}finally {
			ConnectionMake.close(resultSet);
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(conn);
		}
		return stock;
	}



}
