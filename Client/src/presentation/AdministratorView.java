package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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


public class AdministratorView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtLocation;
	private JTextField txtIDr;
	private JTextField txtType;
	private JTextField txtID2;
	private Logger logger;
	private AppClient client;


	/**
	 * Create the frame.
	 */
	public AdministratorView(AppClient c) throws UnknownHostException, IOException, ClassNotFoundException{

		client=c;
		client.start();



		this.setVisible(true);
		this.setTitle("Administrator");

		AdministratorController administratorController = new AdministratorController(client,this);
		client.setAdmin(this,administratorController);

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

		JLabel lblAdministrator = new JLabel("Administrator");
		lblAdministrator.setForeground(Color.WHITE);
		lblAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrator.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		//lblLogin.setBackground(Color.WHITE);
		panel.add(lblAdministrator);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 228, 196));
		panel1.setBounds(0, 75, 584, 286);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JButton btnInsertUser = new JButton("InsertUser");
		btnInsertUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showInsertUserView();

			}
		});
		//btnInsertUser.addActionListener(e-> setFalseAdministratorView());

		btnInsertUser.setForeground(new Color(153, 0, 51));
		btnInsertUser.setFont(new Font("Arial", Font.PLAIN, 20));
		btnInsertUser.setBounds(10, 11, 182, 35);
		panel1.add(btnInsertUser);

		JButton btnEditUser = new JButton("EditUser");
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEditUserView();
			}
		});
		//btnEditUser.addActionListener(e-> setFalseAdministratorView());

		btnEditUser.setForeground(new Color(153, 0, 51));
		btnEditUser.setFont(new Font("Arial", Font.PLAIN, 20));
		btnEditUser.setBounds(10, 57, 182, 35);
		panel1.add(btnEditUser);

		JButton btnDeleteUser = new JButton("DeleteUser");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDeleteUserView();

			}
		});
		//btnDeleteUser.addActionListener(e-> setFalseAdministratorView());

		btnDeleteUser.setForeground(new Color(153, 0, 51));
		btnDeleteUser.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDeleteUser.setBounds(10, 103, 182, 35);
		panel1.add(btnDeleteUser);

		JButton btnViewUsers = new JButton("ViewUsers");
		btnViewUsers.addActionListener(e->administratorController.viewAllUsers());

		btnViewUsers.setForeground(new Color(153, 0, 51));
		btnViewUsers.setFont(new Font("Arial", Font.PLAIN, 20));
		btnViewUsers.setBounds(10, 149, 182, 35);
		panel1.add(btnViewUsers);

		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addActionListener(e->administratorController.generateReport());

		btnGenerateReport.setForeground(new Color(153, 0, 51));
		btnGenerateReport.setFont(new Font("Arial", Font.PLAIN, 20));
		btnGenerateReport.setBounds(371, 240, 203, 35);
		panel1.add(btnGenerateReport);

		JButton btnBack = new JButton("Back");

		btnBack.addActionListener(e -> showLoginView());
		/*	btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(frame==null) System.out.println("frame null");
				frame.setVisible(false);
			}
		});*/btnBack.addActionListener(e-> setFalseAdministratorView());
		btnBack.setForeground(new Color(153, 0, 51));
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBack.setBounds(10, 240, 98, 35);
		panel1.add(btnBack);

		JButton btnView1User = new JButton("View1User");
		btnView1User.addActionListener(e->administratorController.view1User());

		btnView1User.setForeground(new Color(153, 0, 51));
		btnView1User.setFont(new Font("Arial", Font.PLAIN, 20));
		btnView1User.setBounds(76, 195, 150, 35);
		panel1.add(btnView1User);

	

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(10, 199, 22, 30);
		panel1.add(lblNewLabel);

		txtID = new JTextField();
		txtID.setBounds(42, 202, 24, 20);
		panel1.add(txtID);
		txtID.setColumns(10);

		txtLocation = new JTextField();
		txtLocation.setColumns(10);
		txtLocation.setBounds(235, 245, 126, 30);
		panel1.add(txtLocation);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblLocation.setBounds(268, 222, 70, 35);
		panel1.add(lblLocation);

		txtIDr = new JTextField();
		txtIDr.setColumns(10);
		txtIDr.setBounds(133, 250, 33, 20);
		panel1.add(txtIDr);

		txtType = new JTextField();
		txtType.setColumns(10);
		txtType.setBounds(176, 250, 49, 20);
		panel1.add(txtType);

		JLabel lblIDr = new JLabel("ID:");
		lblIDr.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblIDr.setBounds(133, 232, 33, 20);
		panel1.add(lblIDr);

		JLabel lblPdftxt = new JLabel("pdf/txt:");
		lblPdftxt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPdftxt.setBounds(173, 233, 62, 20);
		panel1.add(lblPdftxt);

		JButton btnInsertAnimal = new JButton("InsertAnimal");
		btnInsertAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showInsertAnimalView();

			}
		});
		//btnInsertAnimal.addActionListener(e-> setFalseAdministratorView());

		btnInsertAnimal.setForeground(new Color(153, 0, 51));
		btnInsertAnimal.setFont(new Font("Arial", Font.PLAIN, 20));
		btnInsertAnimal.setBounds(392, 11, 182, 35);
		panel1.add(btnInsertAnimal);

		JButton btnEditAnimal = new JButton("EditAnimal");
		btnEditAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEditAnimalView();

			}
		});
		//btnEditAnimal.addActionListener(e-> setFalseAdministratorView());

		btnEditAnimal.setForeground(new Color(153, 0, 51));
		btnEditAnimal.setFont(new Font("Arial", Font.PLAIN, 20));
		btnEditAnimal.setBounds(392, 57, 182, 35);
		panel1.add(btnEditAnimal);

		JButton btnDeleteAnimal = new JButton("DeleteAnimal");
		btnDeleteAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDeleteAnimalView();

			}
		});
		//btnDeleteAnimal.addActionListener(e-> setFalseAdministratorView());

		btnDeleteAnimal.setForeground(new Color(153, 0, 51));
		btnDeleteAnimal.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDeleteAnimal.setBounds(392, 103, 182, 35);
		panel1.add(btnDeleteAnimal);

		JButton btnViewAnimals = new JButton("ViewAnimals");
		btnViewAnimals.addActionListener(e->administratorController.viewAllAnimals());

		btnViewAnimals.setForeground(new Color(153, 0, 51));
		btnViewAnimals.setFont(new Font("Arial", Font.PLAIN, 20));
		btnViewAnimals.setBounds(392, 149, 182, 35);
		panel1.add(btnViewAnimals);

		JButton btnView1animal = new JButton("View1Animal");
		btnView1animal.addActionListener(e->administratorController.view1Animal());

		btnView1animal.setForeground(new Color(153, 0, 51));
		btnView1animal.setFont(new Font("Arial", Font.PLAIN, 20));
		btnView1animal.setBounds(424, 194, 150, 35);
		panel1.add(btnView1animal);

		txtID2 = new JTextField();
		txtID2.setColumns(10);
		txtID2.setBounds(390, 205, 24, 20);
		panel1.add(txtID2);

		JLabel lblID2 = new JLabel("ID:");
		lblID2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblID2.setBounds(358, 199, 22, 30);
		panel1.add(lblID2);

		JButton btnUpdateStock = new JButton("UpdateStock");
		btnUpdateStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showUpdateStockView();

			}
		});
		btnUpdateStock.setForeground(new Color(153, 0, 51));
		btnUpdateStock.setFont(new Font("Arial", Font.PLAIN, 20));
		btnUpdateStock.setBounds(200, 32, 182, 35);
		panel1.add(btnUpdateStock);

		JButton btnViewStock = new JButton("ViewStock");
		btnViewStock.addActionListener(e->administratorController.viewAllItems());
		btnViewStock.setForeground(new Color(153, 0, 51));
		btnViewStock.setFont(new Font("Arial", Font.PLAIN, 20));
		btnViewStock.setBounds(200, 78, 182, 35);
		panel1.add(btnViewStock);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				client.stop();
			}
		});


		this.setLocationRelativeTo(null);
	}

	public void setFalseAdministratorView() {

		this.setVisible(false);

	}

	public void showLoginView() {

		try {
			new LoginView().setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.severe(Arrays.toString(e.getStackTrace()));
			
		}

	}


	public void showInsertUserView() {

		new InsertUserView(client).setVisible(true);

	}
	public void showEditUserView() {

		new EditUserView(client).setVisible(true);

	}
	public void showDeleteUserView() {

		new DeleteUserView(client).setVisible(true);

	}

	public void showInsertAnimalView() {

		new InsertAnimalView(client).setVisible(true);

	}
	public void showEditAnimalView() {

		new EditAnimalView(client).setVisible(true);

	}
	public void showDeleteAnimalView() {

		new DeleteAnimalView(client).setVisible(true);

	}
	public void showUpdateStockView() {

		new UpdateStockView(client).setVisible(true);

	}



	public String getID() {

		return txtID.getText();

	}

	public String getID2() {

		return txtID2.getText();

	}

	public String getIDReport() {

		return txtIDr.getText();

	}

	public String getTypeReport() {

		return txtType.getText();

	}


	public String getReportLocation() {

		return txtLocation.getText();

	}




	public void showMessage(String message) {

		JOptionPane.showMessageDialog(this, message);

	}
}
