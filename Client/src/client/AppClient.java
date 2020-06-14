package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import controller.AdministratorController;
import controller.UserController;
import presentation.AdministratorView;
import presentation.LoginView;
import presentation.UserView;

public class AppClient implements Runnable {

	private String hostName;
	private int port;
	public Socket socketClient;
	private Thread th=null;
	private List<Object> message;
	private ObjectOutputStream stdOut;
	private ObjectInputStream stdIn;
	private LoginView login=null;
	private AdministratorController adminCtrl=null;
	private UserController userCtrl=null;
	private Logger logger;
	private UserView user=null;

	public AppClient(LoginView log) throws Exception{
		this.hostName="localhost";
		this.port=8789;
		message=new ArrayList<Object>();
		login=log;
	}

	public void setAdmin(AdministratorView adminV, AdministratorController adminC){
		adminCtrl=adminC;
	}

	public void setUser(UserView userV,UserController userC){
		user=userV;
		userCtrl=userC;
	}



	public void connect() {
		try {
			System.out.println("Trying to connect to " + hostName + ":" + port);
			socketClient = new Socket(hostName, port);
			System.out.println("Connection Established");
		} catch ( IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}

	}




	public void readFromServer()throws IOException, ClassNotFoundException{
		System.out.println("readObject");
		stdOut=new ObjectOutputStream(socketClient.getOutputStream());
		stdOut.flush();
		stdIn = new ObjectInputStream(socketClient.getInputStream());
	}

	public void writeObject(Object o) throws IOException{
		stdOut.writeObject(o);
		System.out.println("Client writing: "+o);
		stdOut.flush();
	}

	public void whileConnected() throws IOException{
		while(true){
			try{
				message=(ArrayList<Object>) stdIn.readObject();
				if(message!=null)
					{handle((ArrayList<Object>) message);}
			}catch(Exception e){
				logger.severe(Arrays.toString(e.getStackTrace()));
				
				////////	socketClient.close();
			}
		}
	}

	public void run() {
		connect();
		try {
			readFromServer();
			whileConnected();
		} catch (Exception e) {	
			e.printStackTrace();
		} 
	}

	public void start(){
		if(th==null)
		{
			th=new Thread(this);
			th.start();
		}
	}

	@SuppressWarnings("deprecation")
	public void stop(){
		if(th==null)
		{
			th.stop();
			System.out.println("Client has been closed");
			th=null;
		}
	}

	public void handle(ArrayList<Object> list) throws IOException{
		String str= list.get(0).toString();
		switch(str){
		case "login": login.login1(list.get(1).toString());break;
		//case "notify":vacation.update();if(clientPg!=null) clientPg.update();break;
		case "getAnimalsList":  list.remove(0); 
		if(list.get(0).toString().equals("admin")) {adminCtrl.getAnimalsList1(list);}
		else {userCtrl.getAnimalsList1(list);}
		break;
		case "getUsersList":  list.remove(0);  adminCtrl.getUsersList1(list); break;
		case "getConsultationsList":  list.remove(0);  userCtrl.getConsultationsList1(list); break;
		case "getAnimal": list.remove(0); 
		if(list.get(0).toString().equals("admin")) {adminCtrl.getAnimal1(list);}
		else {userCtrl.getAnimal1(list);}
		break;
		case "getUser": list.remove(0); adminCtrl.getUser1(list); break;
		case "getConsultation": list.remove(0); userCtrl.getConsultation1(list); break;
		case "getScheduledConsultations": list.remove(0);  userCtrl.getScheduledConsultations1(list); break;
		case "notifyDoctor"://socketClient=new Socket(list.get(2).toString(),port);socketClient.getOutputStream().write(5);
			if(user!=null) {user.showMessage("New Consultation Scheduled!");} break;
		case "getItemsList":  list.remove(0); adminCtrl.getItemsList1(list); break;
		case "out of necessary items in stock": userCtrl.outOfItems(); break;
		default: break;
		}	
	}


}
