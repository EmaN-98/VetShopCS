package presentation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.AppClient;
import model.User;

public class LoginView extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextField usernameField;
	private final JPasswordField passwordField;
	private AppClient client;
	private Logger logger;
	
	public LoginView() throws Exception{

		//LoginController loginController = new LoginController(this);
		client = new AppClient(this);
		client.start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 584, 75);
		contentPane.add(panel);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));

		panel.add(lblLogin);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 228, 196));
		panel1.setBounds(0, 75, 584, 386);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(new Color(153, 0, 51));
		lblUsername.setFont(new Font("Arial", Font.BOLD, 25));
		lblUsername.setBounds(100, 70, 150, 40);
		panel1.add(lblUsername);

		usernameField = new JTextField();
		usernameField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		usernameField.setToolTipText("");
		usernameField.setForeground(new Color(204, 204, 204));
		usernameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		usernameField.setBounds(313, 70, 200, 35);
		panel1.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(153, 0, 51));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(100, 170, 150, 35);
		panel1.add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(204, 204, 204));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(313, 170, 200, 35);
		panel1.add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.setForeground(new Color(153, 0, 51));
		loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
		//		loginButton.addActionListener(e -> LoginController.login());

		loginButton.setBounds(220, 259, 99, 35);
		panel1.add(loginButton);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ArrayList<Object> login=new ArrayList<Object>();
					login.add("login");
					User u=new User();
					u.setUsername(getUsername());
					u.setPassword(getPassword());
					if(u.getUsername().equals("admin"))
						{login.add("admin");}
					else {login.add("user");}
					login.add(u);
					client.writeObject(login);

				}catch(Exception e){
					logger.severe(Arrays.toString(e.getStackTrace()));
					
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});



		this.setLocationRelativeTo(null);
	}

	public void setFalseLoginView() {

		this.setVisible(false);

	}

	public void showAdministratorView() throws UnknownHostException, ClassNotFoundException, IOException {

		new AdministratorView(client).setVisible(true);

	}


	public void showUserView() {

		new UserView(client).setVisible(true);

	}


	public String getUsername() {

		return usernameField.getText();

	}


	@SuppressWarnings("deprecation")
	public String getPassword() {

		return passwordField.getText();

	}

	public void login1(String type){
		try{
			switch(type){
			case "admin":{
				AdministratorView a=new AdministratorView(client);
				a.setVisible(true);
				a.setTitle(usernameField.getText());
				a.repaint();
				a.revalidate();
				this.dispose();
			}break;
			case "user":{
				UserView u=new UserView(client);
				u.setVisible(true);
				u.setTitle(usernameField.getText());
				u.repaint();
				u.revalidate();
				this.dispose();
			}break;

			default: showErrorMessage("Wrong username or password "); break;
			}
			usernameField.setText("");
			passwordField.setText("");	
			
		}catch(Exception e){
			logger.severe(Arrays.toString(e.getStackTrace()));
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void showErrorMessage(String message) {

		JOptionPane.showMessageDialog(this, message);

	}


}
