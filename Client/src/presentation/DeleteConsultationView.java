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

public class DeleteConsultationView extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtConsultationId;
	static DeleteConsultationView frame;

	private AppClient client;
	/**
	 * Create the frame.
	 */
	public DeleteConsultationView(AppClient c) {

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

		JLabel lblConsultation = new JLabel("DeleteConsultation");
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

		JButton btnDeleteConsultation = new JButton("DeleteConsultation");
		btnDeleteConsultation.addActionListener(e->userController.deleteConsultation());

		btnDeleteConsultation.setForeground(new Color(153, 0, 51));
		btnDeleteConsultation.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDeleteConsultation.setBounds(338, 240, 236, 35);
		panel1.add(btnDeleteConsultation);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e-> setFalseDeleteConsultationView());
		btnBack.addActionListener(e->showUserView());

		btnBack.setForeground(new Color(153, 0, 51));
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBack.setBounds(10, 240, 110, 35);
		panel1.add(btnBack);

		txtConsultationId = new JTextField();
		txtConsultationId.setColumns(10);
		txtConsultationId.setBounds(210, 60, 314, 27);
		panel1.add(txtConsultationId);

		JLabel lblConsultationId = new JLabel("Consultation ID:");
		lblConsultationId.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultationId.setForeground(new Color(153, 0, 51));
		lblConsultationId.setFont(new Font("Arial", Font.BOLD, 20));
		lblConsultationId.setBounds(10, 53, 168, 35);
		panel1.add(lblConsultationId);



		this.setLocationRelativeTo(null);
	}

	public String getID() {

		return txtConsultationId.getText();

	}

	public void setFalseDeleteConsultationView() {

		this.setVisible(false);

	}

	public void showUserView() {

		new UserView(client).setVisible(true);

	}
	public void showMessage(String message) {

		JOptionPane.showMessageDialog(this, message);

	}




}
