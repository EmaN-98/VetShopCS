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
import model.Animal;

public class AnimalsOp {

	private static Connection conn;
	private final static String insertAnimal = "INSERT INTO animals (name,species,owner,age,diseases,treatments)" + " VALUES (?,?,?,?,?,?)";
	private final static String deleteAnimal = "DELETE FROM animals WHERE animalId = ? ";
	private final static String editAnimal= "UPDATE animals SET name=?, species=?, owner=?, age=?, diseases=?, treatments=? WHERE animalId= ? ";
	private final static String findStatementString = "SELECT * FROM animals where animalId = ?";
	private static Logger logger;
	
	public static int insertAnimal(Animal a) {

		//Animal a=new Animal("name1","species1@yahoo.com","owner1",1,"diseases1","treatments1");
		PreparedStatement stmt = null;
		ResultSet res=null;

		int animalId = 0 ;
		try {
			//userId=u.getUserId();
			String name=a.getName();
			String species=a.getSpecies();
			String owner=a.getOwner();
			int age=a.getAge();
			String diseases=a.getDiseases();
			String treatments=a.getTreatments();

			boolean ok1=false;
			boolean ok2=false;
			boolean ok3=false;
			boolean ok4=false;
			boolean ok5=false;
			boolean ok6=false;

			if(Pattern.matches("[a-z A-Z]+" ,name)&&!name.isEmpty()) {
				ok1=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again name!");
			}

			if(Pattern.matches("[a-z A-Z]+" ,species)&&!species.isEmpty()) {
				ok2=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again species!");
			}

			if(Pattern.matches("[a-z A-Z]+" ,owner)&&!owner.isEmpty()) {
				ok3=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again owner!");
			}

			if (Pattern.matches("[0-9]+",String.valueOf(age)) && !String.valueOf(age).isEmpty() ) {

				ok4=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Check again age!");
			}

			if(Pattern.matches("[a-zA-Z]+" ,diseases)&&!diseases.isEmpty()) {
				ok5=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again diseases!");
			}
			if(Pattern.matches("[a-z A-Z]+" ,treatments)&&!treatments.isEmpty()) {
				ok6=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again treatments!");
			}

			if(ok3&&ok1&&ok2&&ok4&&ok5&&ok6) {
				conn = ConnectionMake.getConnection();

				stmt = conn.prepareStatement(insertAnimal, Statement.RETURN_GENERATED_KEYS);
				//stmt.setInt(1, animalId);
				stmt.setString(1, name);
				stmt.setString(2, species);
				stmt.setString(3, owner);
				stmt.setInt(4,age);
				stmt.setString(5, diseases);
				stmt.setString(6, treatments);

				stmt.executeUpdate();

				 res = stmt.getGeneratedKeys();
				JOptionPane.showMessageDialog(null,"Animal successfully added.");
			}
		} catch (Exception exc) {

			logger.severe(Arrays.toString(exc.getStackTrace()));
			
			JOptionPane.showMessageDialog(null, "Sorry, cannot insert animal. Try another ID");

		} finally {
			ConnectionMake.close(stmt);
			ConnectionMake.close(res);
			ConnectionMake.close(conn);
		}
		System.out.println("animalId::::"+animalId);
		return animalId;

	}

	public static void deleteAnimal(int id) {
		PreparedStatement stmt = null;
		ResultSet res=null;
		try {
			if (Pattern.matches("[0-9]+",String.valueOf(id)) && !String.valueOf(id).isEmpty() ) {

				conn = ConnectionMake.getConnection();
				stmt = conn.prepareStatement(deleteAnimal, Statement.RETURN_GENERATED_KEYS);
				stmt.setLong(1, id);
				stmt.executeUpdate();
				res = stmt.getGeneratedKeys();

				JOptionPane.showMessageDialog(null, "Animal successfully deleted.");
			}

		} catch (SQLException exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
			JOptionPane.showMessageDialog(null, "Sorry, cannot delete animal.");

		} finally {
			ConnectionMake.close(stmt);
			ConnectionMake.close(res);
			ConnectionMake.close(conn);
		}
	}

	public static Animal editAnimal(Animal a) {

		PreparedStatement stmt = null;
		ResultSet res=null;
		try {
			String name=a.getName();
			String species=a.getSpecies();
			String owner=a.getOwner();
			int age=a.getAge();
			String diseases=a.getDiseases();
			String treatments=a.getTreatments();
			int id=a.getAnimalId();

			boolean ok1=false;
			boolean ok2=false;
			boolean ok3=false;
			boolean ok4=false;
			boolean ok5=false;
			boolean ok6=false;
			boolean ok7=false;

			if(Pattern.matches("[a-z A-Z]+" ,name)&&!name.isEmpty()) {
				ok1=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again name!");
			}

			if(Pattern.matches("[a-z A-Z]+" ,species)&&!species.isEmpty()) {
				ok2=true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Check again species!");
			}

			if(Pattern.matches("[a-z A-Z]+" ,owner)&&!owner.isEmpty()) {
				ok3=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again owner!");
			}

			if (Pattern.matches("[0-9]+",String.valueOf(age)) && !String.valueOf(age).isEmpty() ) {

				ok4=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Check again age!");
			}

			if(Pattern.matches("[a-z A-Z]+" ,diseases)&&!diseases.isEmpty()) {
				ok5=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again diseases!");
			}
			if(Pattern.matches("[a-z A-Z]+" ,treatments)&&!treatments.isEmpty()) {
				ok6=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Check again treatments!");
			}
			if (Pattern.matches("[0-9]+",String.valueOf(id)) && !String.valueOf(id).isEmpty() ) {

				ok7=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Check again ID!");
			}

			if(ok3&&ok1&&ok2&&ok4&&ok5&&ok6&&ok7) {


				conn = ConnectionMake.getConnection();
				stmt = conn.prepareStatement(editAnimal, Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, a.getName());
				stmt.setString(2, a.getSpecies());
				stmt.setString(3, a.getOwner());
				stmt.setInt(4, a.getAge());
				stmt.setString(5, a.getDiseases());
				stmt.setString(6, a.getTreatments());
				stmt.setInt(7, a.getAnimalId());

				int changes =stmt.executeUpdate();
				res = stmt.getGeneratedKeys();

				if(changes > 0) { 
					System.out.println(changes);
					JOptionPane.showMessageDialog(null,"Animal successfully edited.");
					return a;
				} else {
					return null;
				}


			}
		} catch (Exception exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
			JOptionPane.showMessageDialog(null,
					"Sorry, cannot edit animal. Check again the data desired");

		} finally {
			ConnectionMake.close(stmt);
			ConnectionMake.close(res);
			ConnectionMake.close(conn);
		}
		return null;
	}


	public static List<Object> findAll() {

		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		List<Object> animals = new ArrayList<>();
		try {
			conn = ConnectionMake.getConnection();
			preparedStatement = conn.prepareStatement("SELECT * FROM animals");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Animal a = new Animal(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7));

				animals.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(preparedStatement);
			ConnectionMake.close(conn);
		}
		return animals;
	}

	public static Animal findById(int animalId) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		try {
			if (Pattern.matches("[0-9]+",String.valueOf(animalId)) && !String.valueOf(animalId).isEmpty() ) {
				conn = ConnectionMake.getConnection();
				 preparedStatement = conn.prepareStatement(findStatementString);
				preparedStatement.setInt(1, animalId);

				 resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					return new Animal(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7));
					
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

}
