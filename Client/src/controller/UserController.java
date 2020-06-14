package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import client.AppClient;
import model.Animal;
import model.Consultation;
import presentation.DeleteAnimalView;
import presentation.DeleteConsultationView;
import presentation.EditAnimalView;
import presentation.EditConsultationView;
import presentation.InsertAnimalView;
import presentation.InsertConsultationView;
import presentation.UserView;


public class UserController {
	private UserView userView;
	private InsertAnimalView insertAnimalView;
	private DeleteAnimalView deleteAnimalView;
	private EditAnimalView editAnimalView;
	private InsertConsultationView insertConsultationView;
	private DeleteConsultationView deleteConsultationView;
	private EditConsultationView editConsultationView;
	private AppClient client;
	private Logger logger;

	public UserController(AppClient c,UserView userView) {
		client=c;
		client.start();
		this.userView = userView;

	}

	public UserController(AppClient c,InsertAnimalView insertAnimalView) {
		client=c;
		client.start();
		this.insertAnimalView = insertAnimalView;
	}

	public UserController(AppClient c,DeleteAnimalView deleteAnimalView) {
		client=c;
		client.start();
		this.deleteAnimalView = deleteAnimalView;
	}

	public UserController(AppClient c,EditConsultationView editConsultationView) {
		client=c;
		client.start();
		this.editConsultationView = editConsultationView;
	}

	public UserController(AppClient c,InsertConsultationView insertConsultationView) {
		client=c;
		client.start();
		this.insertConsultationView = insertConsultationView;
	}

	public UserController(AppClient c,DeleteConsultationView deleteConsultationView) {
		client=c;
		client.start();
		this.deleteConsultationView = deleteConsultationView;
	}

	public UserController(AppClient c,EditAnimalView editAnimalView) {
		client=c;
		client.start();
		this.editAnimalView = editAnimalView;
	}

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
		//System.out.println(list.toString());

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
		List<Object> list=new ArrayList<Object>();
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
			l.add("user");
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}

	public void view1Animal() {
		try {
			List<Object> l=new ArrayList<Object>();
			l.add("getAnimal");
			l.add(userView.getIDanimal());
			l.add("user");
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}


	public void insertConsultation() {

		Consultation c=new Consultation(insertConsultationView.getAnimalName(),insertConsultationView.getDoctorName(),insertConsultationView.getDate(),insertConsultationView.getDiagnosis(),insertConsultationView.getTreatment(),"scheduled");

		List<Object> list=new ArrayList<Object>();
		list.add("insertConsultation");
		list.add(c);

		try {
			client.writeObject(list);
			//notifyDoctor(getDoctorName());
		} catch (IOException ex) {
			logger.severe(Arrays.toString(ex.getStackTrace()));
			
		}
	}


	public void editConsultation() {


		Consultation c=new Consultation(Integer.parseInt(editConsultationView.getID()),editConsultationView.getAnimalName(),editConsultationView.getDoctorName(),editConsultationView.getDate(),editConsultationView.getDiagnosis(),editConsultationView.getTreatment(),editConsultationView.getSstate());

		List<Object> list=new ArrayList<Object>();
		list.add("editConsultation");
		list.add(c);

		try {
			client.writeObject(list);
		} catch (IOException exc) {
			logger.severe(Arrays.toString(exc.getStackTrace()));
			
		}
	}

	public void deleteConsultation() {
		List<Object> list=new ArrayList<Object>();
		int id=Integer.parseInt(deleteConsultationView.getID());
		list.add("deleteConsultation");
		list.add(id);
		try {
			client.writeObject(list);
		} catch (IOException ex) {
			logger.severe(Arrays.toString(ex.getStackTrace()));
			
		}
	}

	public void viewAllConsultations() {

		try {
			List<Object> l=new ArrayList<Object>();
			l.add("getConsultationsList");
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}

	public void view1Consultation() {
		try {
			List<Object> l=new ArrayList<Object>();
			l.add("getConsultation");
			l.add(userView.getIDconsultation());
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}

	public void getAnimalsList1(List<Object> l){

		userView.showMessage("Animals List: "+l.toString());

	}



	public void getAnimal1(List<Object> l){

		userView.showMessage("Animal: "+l.toString());

	}
	public void getConsultation1(List<Object> l){

		userView.showMessage("Consultation: "+l.toString());

	}


	public void getConsultationsList1(List<Object> l){

		userView.showMessage("Consultations List: "+l.toString());

	}

	public void getScheduledConsultations(){
		try {
			List<Object> l=new ArrayList<Object>();
			l.add("getScheduledConsultations");
			l.add(userView.getDoctorName());
			client.writeObject(l);
		} catch (IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}

	public void getScheduledConsultations1(List<Object> l){

		userView.showMessage("Scheduled Consultations: "+l.toString());

	}

	public void outOfItems() {
		userView.showMessage("Consultation can't be moved to in progress. Some items are missing from stock" );
	}
}
