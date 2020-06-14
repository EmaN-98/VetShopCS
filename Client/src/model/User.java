package model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	public int userId;
	private String name;
	public String email;
	public String username;
	private String password;


	public User(int userId, String name, String email, String username, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}


	public User(String name, String email, String username, String password) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}


	public User() {
		super();
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		userId=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String[] getHeader(){
		String []head=new String[5];
		head[0]="userId";
		head[1]="name";
		head[2]="email";
		head[3]="username";
		head[4]="password";
		return head;
	}

	public static Object[][] getData(List<Object> list){
		Object [][]data=new Object[5][list.size()];
		int i=0;
		for(Object o:list){
			User u=(User)o;
			//////////////////userId?	
			data[i][0]=u.getUserId();
			data[i][1]=u.getName();
			data[i][2]=u.getEmail();
			data[i][3]=u.getUsername();
			data[i][4]=u.getPassword();
			i++;
		}
		return data;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", password=" + password + "]\n";
	}


}




