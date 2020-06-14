package presentation;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.AppClient;
import controller.UserController;



public class EditAnimalView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtOwner;
	private JTextField txtAge;
	private JTextField txtSpecies;
	static EditAnimalView frame;
	private JTextField txtDiseases;
	private JTextField txtTreatments;
	private JTextField txtID;

	private AppClient client;
	/**
	 * Create the frame.
	 */
	public EditAnimalView(AppClient c) {

		client=c;
		client.start();

		UserController userController = new UserController(client,this);

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

		JLabel lblAnimal = new JLabel("EditAnimal");
		lblAnimal.setForeground(Color.WHITE);
		lblAnimal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimal.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		//lblLogin.setBackground(Color.WHITE);
		panel.add(lblAnimal);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 228, 196));
		panel1.setBounds(0, 75, 584, 286);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JButton btnEditAnimal = new JButton("EditAnimal");
		btnEditAnimal.addActionListener(e->userController.editAnimal());
		btnEditAnimal.setForeground(new Color(153, 0, 51));
		btnEditAnimal.setFont(new Font("Arial", Font.PLAIN, 20));
		btnEditAnimal.setBounds(392, 240, 182, 35);
		panel1.add(btnEditAnimal);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e-> setFalseEditAnimalView());
		//btnBack.addActionListener(e-> showUserView());

		btnBack.setForeground(new Color(153, 0, 51));
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBack.setBounds(10, 240, 110, 35);
		panel1.add(btnBack);

		txtName = new JTextField();
		txtName.setBounds(210, 24, 314, 27);
		panel1.add(txtName);
		txtName.setColumns(10);

		txtOwner = new JTextField();
		txtOwner.setColumns(10);
		txtOwner.setBounds(210, 98, 314, 27);
		panel1.add(txtOwner);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(210, 136, 314, 27);
		panel1.add(txtAge);

		txtSpecies = new JTextField();
		txtSpecies.setColumns(10);
		txtSpecies.setBounds(210, 60, 314, 27);
		panel1.add(txtSpecies);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(new Color(153, 0, 51));
		lblName.setFont(new Font("Arial", Font.BOLD, 20));
		lblName.setBounds(42, 17, 119, 35);
		panel1.add(lblName);

		JLabel lblOwner = new JLabel("Owner");
		lblOwner.setHorizontalAlignment(SwingConstants.CENTER);
		lblOwner.setForeground(new Color(153, 0, 51));
		lblOwner.setFont(new Font("Arial", Font.BOLD, 20));
		lblOwner.setBounds(42, 91, 119, 35);
		panel1.add(lblOwner);

		JLabel lblAge = new JLabel("Age");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setForeground(new Color(153, 0, 51));
		lblAge.setFont(new Font("Arial", Font.BOLD, 20));
		lblAge.setBounds(42, 129, 119, 35);
		panel1.add(lblAge);

		JLabel lblSpecies = new JLabel("Species");
		lblSpecies.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpecies.setForeground(new Color(153, 0, 51));
		lblSpecies.setFont(new Font("Arial", Font.BOLD, 20));
		lblSpecies.setBounds(42, 53, 119, 35);
		panel1.add(lblSpecies);

		txtDiseases = new JTextField();
		txtDiseases.setColumns(10);
		txtDiseases.setBounds(210, 174, 314, 27);
		panel1.add(txtDiseases);

		txtTreatments = new JTextField();
		txtTreatments.setColumns(10);
		txtTreatments.setBounds(210, 212, 314, 27);
		panel1.add(txtTreatments);

		JLabel lblDiseases = new JLabel("Diseases");
		lblDiseases.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiseases.setForeground(new Color(153, 0, 51));
		lblDiseases.setFont(new Font("Arial", Font.BOLD, 20));
		lblDiseases.setBounds(42, 166, 119, 35);
		panel1.add(lblDiseases);

		JLabel lblTreatments = new JLabel("Treatments");
		lblTreatments.setHorizontalAlignment(SwingConstants.CENTER);
		lblTreatments.setForeground(new Color(153, 0, 51));
		lblTreatments.setFont(new Font("Arial", Font.BOLD, 20));
		lblTreatments.setBounds(42, 204, 119, 35);
		panel1.add(lblTreatments);

		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(210, 247, 98, 27);
		panel1.add(txtID);

		JLabel lblID = new JLabel("ID:");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setForeground(new Color(153, 0, 51));
		lblID.setFont(new Font("Arial", Font.BOLD, 20));
		lblID.setBounds(142, 248, 53, 27);
		panel1.add(lblID);



		this.setLocationRelativeTo(null);
	}

	public void setFalseEditAnimalView() {

		this.setVisible(false);

	}

	public void showUserView() {

		this.setVisible(true);

	}

	public String getID() {

		return txtID.getText();

	}

	public String getName() {

		return txtName.getText();

	}

	public String getSpecies() {

		return txtSpecies.getText();

	}

	public String getOowner() {

		return txtOwner.getText();

	}


	public String getAge() {

		return txtAge.getText();

	}

	public String getDiseases() {

		return txtDiseases.getText();

	}

	public String getTreatments() {

		return txtTreatments.getText();

	}


	public void showMessage(String message) {

		JOptionPane.showMessageDialog(this, message);

	}
}
