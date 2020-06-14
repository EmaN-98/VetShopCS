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

public class DeleteAnimalView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtAnimalId;
	static DeleteAnimalView frame;

	private AppClient client;
	/**
	 * Create the frame.
	 */
	public DeleteAnimalView(AppClient c) {

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

		JLabel lblAnimal = new JLabel("DeleteAnimal");
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

		JButton btnDeleteAnimal = new JButton("DeleteAnimal");
		btnDeleteAnimal.addActionListener(e->userController.deleteAnimal());

		btnDeleteAnimal.setForeground(new Color(153, 0, 51));
		btnDeleteAnimal.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDeleteAnimal.setBounds(380, 240, 194, 35);
		panel1.add(btnDeleteAnimal);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e-> setFalseDeleteAnimalView());
		//btnBack.addActionListener(e->showUserView());

		btnBack.setForeground(new Color(153, 0, 51));
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBack.setBounds(10, 240, 110, 35);
		panel1.add(btnBack);

		txtAnimalId = new JTextField();
		txtAnimalId.setColumns(10);
		txtAnimalId.setBounds(210, 60, 314, 27);
		panel1.add(txtAnimalId);

		JLabel lblAnimalId = new JLabel("Animal ID:");
		lblAnimalId.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimalId.setForeground(new Color(153, 0, 51));
		lblAnimalId.setFont(new Font("Arial", Font.BOLD, 20));
		lblAnimalId.setBounds(30, 53, 148, 35);
		panel1.add(lblAnimalId);



		this.setLocationRelativeTo(null);
	}

	public String getID() {

		return txtAnimalId.getText();

	}

	public void setFalseDeleteAnimalView() {

		this.setVisible(false);

	}

	public void showUserView() {

		this.setVisible(true);

	}
	public void showMessage(String message) {

		JOptionPane.showMessageDialog(this, message);

	}

}
