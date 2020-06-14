package appServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import bll.AnimalsBLL;
import bll.ConsultationsBLL;
import bll.LoginBLL;
import bll.StockBLL;
import bll.UserBLL;
import model.Animal;
import model.Consultation;
import model.Stock;
import model.User;
import operations.UserOp;

public class ServerThread extends java.util.Observable implements Runnable{

	private Socket client;
	private int id;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public static Map<Socket, String> connectedClients = new HashMap<Socket, String>();
	private Logger logger;
	
	public ServerThread(Socket ss,int id){
		client=ss;
		this.setId(id);
	}

	public void connect() throws IOException{
		out= new ObjectOutputStream(client.getOutputStream());
		out.flush();
		in = new ObjectInputStream(client.getInputStream());
	}

	public void readFromClient() throws IOException,Exception{

		Object o=in.readObject();
		@SuppressWarnings("unchecked")
		List<Object> msg=(ArrayList<Object>) o;
		if(o!=null)
			{processMessage(msg);}

	}

	public void sendObject(List< Object> list)throws IOException {
		out.writeObject(list);
		out.flush();
	}

	public void processMessage(List<Object> msg) throws Exception{

		connection.ConnectionMake.getConnection();
		List<Object> list=new ArrayList<Object>();
		System.out.println("Server reading: "+msg);
		switch((String)msg.get(0)){
		case "login":{
			User u=(User) msg.get(2);
			System.out.println("type:"+msg.get(1)+"  user: "+msg.get(2));
			boolean ok=LoginBLL.login(u.getUsername(), u.getPassword()); 
			if(ok) {connectedClients.put(client,UserOp.findDoctorName( u.getUsername()));}
			System.out.println(connectedClients);
			list.add("login");

			if(u.getUsername().equals("admin")) 
				{list.add("admin");}
			else {list.add("user");}
			sendObject(list);
		}break;

		case "getUser":{
			int id=Integer.parseInt(msg.get(1).toString());
			User u=new User();
			u=UserBLL.viewUser(id);
			list.add("getUser");
			list.add(u);
			sendObject(list);

		}break;
		case "getConsultation":{
			int id=Integer.parseInt(msg.get(1).toString());
			Consultation c=new Consultation();
			c=ConsultationsBLL.viewConsultation(id);
			list.add("getConsultation");
			list.add(c);
			sendObject(list);

		}break;
		case "getAnimal":{
			int id=Integer.parseInt(msg.get(1).toString());
			Animal a=new Animal();
			a=AnimalsBLL.viewAnimal(id);
			list.add("getAnimal");
			if(msg.get(2).equals("admin"))
				{list.add("admin");}
			else {list.add("user");}
			list.add(a);
			sendObject(list);

		}break;
		case "insertUser":{
			User user=(User)msg.get(1);
			UserBLL.insertUser(user);

		}break;
		case "insertConsultation":{
			Consultation c=(Consultation)msg.get(1);
			ConsultationsBLL.insertConsultation(c);
			//setChanged();
			//notifyObservers();

		}break;
		case "insertAnimal":{
			Animal a=(Animal)msg.get(1);
			AnimalsBLL.insertAnimal(a);
		}break;
		case "editUser":{
			User user=(User)msg.get(1);
			UserBLL.editUser(user);
		}break;
		case "editConsultation":{
			Consultation c=(Consultation)msg.get(1);
			if(c.getState().equals("in progress")) {
				boolean ok=StockBLL.checkStock();
				if(ok==true) {
					ConsultationsBLL.editConsultation(c);
					StockBLL.decreaseQuantity();}
				else {list.add("out of necessary items in stock"); sendObject(list);System.out.println("out of stock");}}
		}break;
		case "editAnimal":{
			Animal a=(Animal)msg.get(1);
			AnimalsBLL.editAnimal(a);
		}break;
		case "deleteUser":{
			int userId=Integer.parseInt(msg.get(1).toString());
			UserBLL.deleteUser(userId);
		}break;
		case "deleteConsultation":{
			int id=Integer.parseInt(msg.get(1).toString());
			ConsultationsBLL.deleteConsultation(id);
		}break;
		case "deleteAnimal":{
			int animalId=Integer.parseInt(msg.get(1).toString());
			AnimalsBLL.deleteAnimal(animalId);
		}break;
		case "getAnimalsList":{

			list.add("getAnimalsList");
			list.add(msg.get(1));
			List<Object> animals = new ArrayList<>();
			animals=AnimalsBLL.viewAllAnimals();
			list.add(animals);
			sendObject(list);
		}break;
		case "getUsersList":{

			list.add("getUsersList");
			//list.add(msg.get(1));
			List<Object> users = new ArrayList<>();
			users=UserBLL.viewAllUsers();
			list.add(users);
			sendObject(list);
		}break;
		case "getConsultationsList":{
			list.add("getConsultationsList");
			List<Object> consultation = new ArrayList<>();
			consultation=ConsultationsBLL.viewAllConsultations();
			list.add(consultation);
			sendObject(list);
		}break;
		case "getScheduledConsultations":{
			list.add("getScheduledConsultations");
			String doctorName=msg.get(1).toString();
			List<Object> consultation = new ArrayList<>();
			consultation=ConsultationsBLL.findScheduledConsultations(doctorName);
			list.add(consultation);
			sendObject(list);
		}break;
		case "notifyDoctor":{
			//int id=UserOp.findDoctorId(msg.get(1).toString());
			list.add("notifyDoctor");
			list.add(msg.get(1).toString());
			for(Socket s:connectedClients.keySet()){
				if(connectedClients.get(s).equals(msg.get(1).toString()) ){
					list.add(s.toString()); //s.notify();
					//super.addObserver(null);
					//Server.sendSocket(msg.get(1).toString(),list);
					sendObject(list);

					//sendMessage(s, "New Consultation Scheduled!!!");
				}	
			}
			//Server.sendSocket(id,list);
		}break;
		case "generateReport":{
			list.add("generateReport");
			String type=msg.get(1).toString();
			int id=Integer.parseInt(msg.get(2).toString());
			String location=msg.get(3).toString();
			ConsultationsBLL.generateReport(type,id,location);
		}break;
		case "updateStock":{
			Stock s=(Stock)msg.get(1);
			StockBLL.updateStock(s);
		}break;
		case "getItemsList":{

			list.add("getItemsList");
			List<Object> items = new ArrayList<>();
			items=StockBLL.viewStock();
			list.add(items);
			sendObject(list);
		}break;
		default: break;

		}
	}

	@SuppressWarnings("unchecked")
	public void whileConnected() throws Exception{
		try {
			do{
				Object list=in.readObject();
				processMessage((List<Object>)list);
			}while(true);
		}catch(IOException e) {
			logger.severe(Arrays.toString(e.getStackTrace()));
			
			connectedClients.remove(client);
			client.close();

		}
	}

	public void run() {
		try{
			connect();
			whileConnected();
		}catch(Exception e){
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}
	private void sendMessage(Socket client, String message) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(client.getOutputStream()));
		writer.write(message);
		System.out.println("[SERVER WRITE]: "+message);
		writer.flush();
	}

	public void notifyClient() throws IOException
	{
		for(Socket s : connectedClients.keySet())
		{
			sendMessage(s, "New Consultation Scheduled!!!");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
