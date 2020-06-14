package presentation;


import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.AppClient;
import controller.AdministratorController;


public class EditUserView extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtEmail;
	static EditUserView frame;
	private JTextField textId;
	private Logger logger;
	private AppClient client;

	/**
	 * Create the frame.
	 */
	public EditUserView(AppClient c) {

		client=c;
		client.start();
		AdministratorController administratorController = new AdministratorController(client,this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 584, 75);
		contentPane.add(panel);

		JLabel lblUser = new JLabel("EditUser");
		lblUser.setForeground(Color.WHITE);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));

		panel.add(lblUser);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 228, 196));
		panel1.setBounds(0, 75, 584, 286);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JButton btnEditUser = new JButton("EditUser");
		btnEditUser.addActionListener(e->administratorController.editUser());

		btnEditUser.setForeground(new Color(153, 0, 51));
		btnEditUser.setFont(new Font("Arial", Font.PLAIN, 20));
		btnEditUser.setBounds(405, 240, 169, 35);
		panel1.add(btnEditUser);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e-> setFalseEditUserView());
		btnBack.addActionListener(e-> {
			try {
				showAdministratorView();
			} catch (ClassNotFoundException|IOException e1) {
				// TODO Auto-generated catch block
				logger.severe(Arrays.toString(e1.getStackTrace()));
				
			}
		});

		btnBack.setForeground(new Color(153, 0, 51));
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBack.setBounds(10, 240, 110, 35);
		panel1.add(btnBack);

		txtName = new JTextField();
		txtName.setBounds(210, 24, 314, 27);
		panel1.add(txtName);
		txtName.setColumns(10);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(210, 98, 314, 27);
		panel1.add(txtUsername);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(210, 136, 314, 27);
		panel1.add(txtPassword);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(210, 60, 314, 27);
		panel1.add(txtEmail);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(new Color(153, 0, 51));
		lblName.setFont(new Font("Arial", Font.BOLD, 20));
		lblName.setBounds(42, 17, 119, 35);
		panel1.add(lblName);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(new Color(153, 0, 51));
		lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
		lblUsername.setBounds(42, 91, 119, 35);
		panel1.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(153, 0, 51));
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(42, 129, 119, 35);
		panel1.add(lblPassword);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(new Color(153, 0, 51));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
		lblEmail.setBounds(42, 53, 119, 35);
		panel1.add(lblEmail);

		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setForeground(new Color(153, 0, 51));
		lblId.setFont(new Font("Arial", Font.BOLD, 20));
		lblId.setBounds(42, 175, 119, 35);
		panel1.add(lblId);

		textId = new JTextField();
		textId.setColumns(10);
		textId.setBounds(210, 174, 314, 27);
		panel1.add(textId);



		this.setLocationRelativeTo(null);

	}
	public void setFalseEditUserView() {

		this.setVisible(false);

	}

	public void showAdministratorView() throws UnknownHostException, ClassNotFoundException, IOException {

		this.setVisible(true);

	}

	public String getId() {

		return textId.getText();

	}

	public String getName() {

		return txtName.getText();

	}

	public String getEmail() {

		return txtEmail.getText();

	}

	public String getUsername() {

		return txtUsername.getText();

	}


	public String getPassword() {

		return txtPassword.getText();

	}


	public void showMessage(String message) {

		JOptionPane.showMessageDialog(this, message);

	}

}
