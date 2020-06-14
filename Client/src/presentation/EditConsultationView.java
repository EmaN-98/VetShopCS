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

public class EditConsultationView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAnimalName;
	private JTextField txtDate;
	private JTextField txtDiagnosis;
	private JTextField txtDoctorName;
	static EditConsultationView frame;
	private JTextField txtTreatment;
	private JTextField txtID;

	private AppClient client;
	private JTextField txtState;


	/**
	 * Create the frame.
	 */
	public EditConsultationView(AppClient c) {

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

		JLabel lblConsultation = new JLabel("EditConsultation");
		lblConsultation.setForeground(Color.WHITE);
		lblConsultation.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultation.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		//lblLogin.setBackground(Color.WHITE);
		panel.add(lblConsultation);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 228, 196));
		panel1.setBounds(0, 75, 584, 286);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JButton btnEditConsultation = new JButton("EditConsultation");
		btnEditConsultation.addActionListener(e->userController.editConsultation());
		btnEditConsultation.setForeground(new Color(153, 0, 51));
		btnEditConsultation.setFont(new Font("Arial", Font.PLAIN, 20));
		btnEditConsultation.setBounds(368, 240, 206, 35);
		panel1.add(btnEditConsultation);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e-> setFalseEditConsultationView());
		btnBack.addActionListener(e-> showUserView());

		btnBack.setForeground(new Color(153, 0, 51));
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBack.setBounds(10, 240, 110, 35);
		panel1.add(btnBack);

		txtAnimalName = new JTextField();
		txtAnimalName.setBounds(210, 24, 314, 27);
		panel1.add(txtAnimalName);
		txtAnimalName.setColumns(10);

		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(210, 98, 314, 27);
		panel1.add(txtDate);

		txtDiagnosis = new JTextField();
		txtDiagnosis.setColumns(10);
		txtDiagnosis.setBounds(210, 136, 314, 27);
		panel1.add(txtDiagnosis);

		txtDoctorName = new JTextField();
		txtDoctorName.setColumns(10);
		txtDoctorName.setBounds(210, 60, 314, 27);
		panel1.add(txtDoctorName);

		JLabel lblAnimalName = new JLabel("AnimalName");
		lblAnimalName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimalName.setForeground(new Color(153, 0, 51));
		lblAnimalName.setFont(new Font("Arial", Font.BOLD, 20));
		lblAnimalName.setBounds(42, 17, 119, 35);
		panel1.add(lblAnimalName);

		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(new Color(153, 0, 51));
		lblDate.setFont(new Font("Arial", Font.BOLD, 20));
		lblDate.setBounds(42, 91, 119, 35);
		panel1.add(lblDate);

		JLabel lblDiagnosis = new JLabel("Diagnosis");
		lblDiagnosis.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiagnosis.setForeground(new Color(153, 0, 51));
		lblDiagnosis.setFont(new Font("Arial", Font.BOLD, 20));
		lblDiagnosis.setBounds(42, 129, 119, 35);
		panel1.add(lblDiagnosis);

		JLabel lblDoctorName = new JLabel("DoctorName");
		lblDoctorName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctorName.setForeground(new Color(153, 0, 51));
		lblDoctorName.setFont(new Font("Arial", Font.BOLD, 20));
		lblDoctorName.setBounds(42, 53, 119, 35);
		panel1.add(lblDoctorName);

		txtTreatment = new JTextField();
		txtTreatment.setColumns(10);
		txtTreatment.setBounds(210, 174, 314, 27);
		panel1.add(txtTreatment);

		JLabel lblTreatment = new JLabel("Treatment");
		lblTreatment.setHorizontalAlignment(SwingConstants.CENTER);
		lblTreatment.setForeground(new Color(153, 0, 51));
		lblTreatment.setFont(new Font("Arial", Font.BOLD, 20));
		lblTreatment.setBounds(42, 166, 119, 35);
		panel1.add(lblTreatment);

		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(210, 247, 86, 27);
		panel1.add(txtID);

		JLabel lblID = new JLabel("ID:");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setForeground(new Color(153, 0, 51));
		lblID.setFont(new Font("Arial", Font.BOLD, 20));
		lblID.setBounds(130, 240, 74, 35);
		panel1.add(lblID);

		JLabel lblState = new JLabel("State");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setForeground(new Color(153, 0, 51));
		lblState.setFont(new Font("Arial", Font.BOLD, 20));
		lblState.setBounds(42, 205, 119, 35);
		panel1.add(lblState);

		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(210, 209, 314, 27);
		panel1.add(txtState);



		this.setLocationRelativeTo(null);
	}

	public void setFalseEditConsultationView() {

		this.setVisible(false);

	}

	public void showUserView() {

		new UserView(client).setVisible(true);

	}

	public String getID() {

		return txtID.getText();
	}

	public String getAnimalName() {

		return txtAnimalName.getText();
	}

	public String getDoctorName() {

		return txtDoctorName.getText();
	}

	public String getDate() {

		return txtDate.getText();

	}

	public String getDiagnosis() {

		return txtDiagnosis.getText();

	}

	public String getTreatment() {

		return txtTreatment.getText();

	}

	public String getSstate() {

		return txtState.getText();

	}


	public void showMessage(String message) {

		JOptionPane.showMessageDialog(this, message);

	}
}
