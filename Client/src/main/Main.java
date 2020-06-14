package main;
import java.util.Arrays;
import java.util.logging.Logger;

import presentation.LoginView;

public class Main {

	private static Logger logger;
	
	public static void main(String []args){
		try{
			LoginView l=new LoginView();
			l.setVisible(true);
		}catch(Exception e){
			logger.severe(Arrays.toString(e.getStackTrace()));
		}
	}

}
