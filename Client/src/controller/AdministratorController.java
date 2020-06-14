package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import client.AppClient;
import model.Animal;
import model.Stock;
import model.User;
import presentation.AdministratorView;
import presentation.DeleteAnimalView;
import presentation.DeleteUserView;
import presentation.EditAnimalView;
import presentation.EditUserView;
import presentation.InsertAnimalView;
import presentation.InsertUserView;
import presentation.UpdateStockView;

public class AdministratorController {

	private AdministratorView administratorView;
	private InsertUserView insertUserView;
	private DeleteUserView deleteUserView;
	private EditUserView editUserView;
	private InsertAnimalView insertAnimalView;
	private DeleteAnimalView deleteAnimalView;
	private EditAnimalView editAnimalView;
	private UpdateStockView updateStockView;
	private AppClient client;
	private static Logger logger;

	public AdministratorController(AppClient c,AdministratorView administratorView) {

		client=c;
		client.start();
		this.administratorView = administratorView;

	}

	public AdministratorController(AppClient c,InsertUserView insertUserView) {
		client=c;
		client.start();
		this.insertUserView = insertUserView;
	}

	public AdministratorController(AppClient c,DeleteUserView deleteUserView) {
		client=c;
		client.start();
		this.deleteUserView = deleteUserView;
	}

	public AdministratorController(AppClient c,EditUserView editUserView) {
		client=c;
		client.start();
		this.editUserView = editUserView;
	}

	public AdministratorController(AppClient c,UpdateStockView updateStockView) {
		client=c;
		client.start();
		this.updateStockView = updateStockView;
	}

	public void insertUser() {


		String name=insertUserView.getName();
		String email=insertUserView.getEmail();
		String username=insertUserView.getUsername();
		String password=insertUserView.getPassword();

		User u=new User(name, email, username, password);
		List<Object> list=new ArrayList<Object>();
		list.add("insertUser");
		list.add(u);

		try {
			client.writeObject(list);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
		insertUserView.showMessage("User successfully added.");}



	public void editUser() {


		int id=Integer.parseInt(editUserView.getId());
		String name=editUserView.getName();
		String email=editUserView.getEmail();
		String username=editUserView.getUsername();
		String password=editUserView.getPassword();

		User u=new User(id,name, email, username, password);
		List<Object> list=new ArrayList<Object>();
		list.add("editUser");
		list.add(u);

		try {
			client.writeObject(list);
		} catch (IOException exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
		}
		editUserView.showMessage("User successfully edited.");}


	public void deleteUser() {

		int id=Integer.parseInt(deleteUserView.getID());
		List<Object> list=new ArrayList<Object>();
		list.add("deleteUser");
		list.add(id);
		try {
			client.writeObject(list);
		} catch (IOException ex) {
			logger.severe(Arrays.toString(ex.getStackTrace()));
			
		}
	}

	public void viewAllUsers() {

		try {
			List<Object> l=new ArrayList<Object>();
			l.add("getUsersList");
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}

	public void viewAllItems() {

		try {
			List<Object> l=new ArrayList<Object>();
			l.add("getItemsList");
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}

	public void view1User() {
		try {
			ArrayList<Object> l=new ArrayList<Object>();
			l.add("getUser");
			l.add(administratorView.getID());
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}

	public void updateStock(int id,String name, int quantity) {

		Stock s=new Stock(id,name,quantity);

		List<Object> list=new ArrayList<Object>();
		list.add("updateStock");
		list.add(s);

		try {
			client.writeObject(list);
		} catch (IOException exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
		}
		updateStockView.showMessage("Stock successfully updated.");}

	public void insertAnimal() {


		String name=insertAnimalView.getName();
		String species=insertAnimalView.getSpecies();
		String owner=insertAnimalView.getOowner();
		String age=insertAnimalView.getAge();
		int agei=Integer.parseInt(age);
		String diseases=insertAnimalView.getDiseases();
		String treatments=insertAnimalView.getTreatments();
		Animal a=new Animal(name,species,owner,agei,diseases,treatments);

		List<Object> list=new ArrayList<Object>();
		list.add("insertAnimal");
		list.add(a);
		System.out.println(list.toString());

		try {
			client.writeObject(list);
			////		getAnimalsList();
		} catch (IOException ex) {
			logger.severe(Arrays.toString(ex.getStackTrace()));
			
		}
	}

	public void editAnimal() {


		String id=editAnimalView.getID();
		int idi=Integer.parseInt(id);
		String name=editAnimalView.getName();
		String species=editAnimalView.getSpecies();
		String owner=editAnimalView.getOowner();
		String age=editAnimalView.getAge();
		int agei=Integer.parseInt(age);
		String diseases=editAnimalView.getDiseases();
		String treatments=editAnimalView.getTreatments();

		Animal a=new Animal(idi,name,species,owner,agei,diseases,treatments);

		List<Object> list=new ArrayList<Object>();
		list.add("editAnimal");
		list.add(a);

		try {
			client.writeObject(list);
		} catch (IOException exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
		}
	}



	public void deleteAnimal() {
		ArrayList<Object> list=new ArrayList<Object>();
		int id=Integer.parseInt(deleteAnimalView.getID());
		list.add("deleteAnimal");
		list.add(id);
		try {
			client.writeObject(list);
		} catch (IOException ex) {
			logger.severe(Arrays.toString(ex.getStackTrace()));
			
		}
	}

	public void viewAllAnimals() {

		try {
			List<Object> l=new ArrayList<Object>();
			l.add("getAnimalsList");
			l.add("admin");
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}

	public void view1Animal() {
		try {
			List<Object> l=new ArrayList<Object>();
			l.add("getAnimal");
			l.add(administratorView.getID2());
			l.add("admin");
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}




	public void getUsersList1(List<Object> l){

		administratorView.showMessage("Users List: "+l.toString());

	}



	public void getUser1(List<Object> l){
		administratorView.showMessage("User: "+l.toString());

	}


	public void getAnimalsList1(List<Object> l){

		administratorView.showMessage("Animals List: "+l.toString());

	}



	public void getAnimal1(List<Object> l){

		administratorView.showMessage("Animal: "+l.toString());

	}

	public void generateReport() {
		try {
			List<Object> l=new ArrayList<Object>();
			l.add("generateReport");
			String type=administratorView.getTypeReport();
			int id=Integer.parseInt(administratorView.getIDReport());
			String location=administratorView.getReportLocation();
			l.add(type);
			l.add(id);
			l.add(location);
			client.writeObject(l);
		}catch(IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}

	}

	public void getItemsList1(List<Object> l){

		administratorView.showMessage("Items List: "+l.toString());

	}
}
