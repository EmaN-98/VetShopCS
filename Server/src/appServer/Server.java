package appServer;

import java.net.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;


import java.io.*;



public class Server {

	private static int port=8789;
	private static Hashtable<ServerThread,Integer> clients;
	public static int count;
	private static ServerSocket serverSocket;
	private static Logger logger;

	public Server(){
		count=0; 
		clients=new Hashtable<ServerThread,Integer>();

	}

	public static void setIdClient(ServerThread cl,int id){
 		for(ServerThread s:clients.keySet()){
			if(s.equals(cl)){
				clients.put(s, id);
			}
		}
	}

	public static void sendSocket(int id, List<Object> x) throws IOException{
		for(ServerThread s:clients.keySet()){
			if(clients.get(s)==id ){
				s.sendObject((ArrayList<Object>) x);
			}	
		}
	}
	/*
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("update server");
		ArrayList<Object> ls=new ArrayList<Object>();
		ls.add("notify");
		for(ServerThread s:clients.keySet()){
			try {
				s.sendObject(ls);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
	public static void main(String []args){
		try{
			new Server();
			serverSocket = new ServerSocket(port);
			System.out.println("Server is running on port no: "+port);
			while(true){
				Socket client=serverSocket.accept();
				ServerThread st=new ServerThread(client,++count );
				System.out.println("client id:"+count);
				clients.put(st, count);
				Thread thr=new Thread(st);
				thr.start();
			}
		}catch(IOException e){
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}
	}


}
