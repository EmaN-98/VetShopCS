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


public class UpdateStockView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtItemQuantity;
	static EditUserView frame;
	private JTextField textId;
	private Logger logger;
	private AppClient client;

	/**
	 * Create the frame.
	 */
	public UpdateStockView(AppClient c) {

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

		JLabel lblStock = new JLabel("UpdateStock");
		lblStock.setForeground(Color.WHITE);
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));

		panel.add(lblStock);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 228, 196));
		panel1.setBounds(0, 75, 584, 286);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JButton btnUpdateStock = new JButton("UpdateStock");
		btnUpdateStock.addActionListener(e->administratorController.updateStock(Integer.parseInt(getId()),getItem(),Integer.parseInt(getItemQuantity())));

		btnUpdateStock.setForeground(new Color(153, 0, 51));
		btnUpdateStock.setFont(new Font("Arial", Font.PLAIN, 20));
		btnUpdateStock.setBounds(405, 240, 169, 35);
		panel1.add(btnUpdateStock);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e-> setFalseUpdateStockView());
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

		txtItemQuantity = new JTextField();
		txtItemQuantity.setColumns(10);
		txtItemQuantity.setBounds(210, 98, 314, 27);
		panel1.add(txtItemQuantity);

		JLabel lblName = new JLabel("ItemName");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(new Color(153, 0, 51));
		lblName.setFont(new Font("Arial", Font.BOLD, 20));
		lblName.setBounds(42, 17, 119, 35);
		panel1.add(lblName);

		JLabel lblItemQuantity = new JLabel("ItemQuantity");
		lblItemQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemQuantity.setForeground(new Color(153, 0, 51));
		lblItemQuantity.setFont(new Font("Arial", Font.BOLD, 20));
		lblItemQuantity.setBounds(10, 91, 151, 35);
		panel1.add(lblItemQuantity);

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
	public void setFalseUpdateStockView() {

		this.setVisible(false);

	}

	public void showAdministratorView() throws UnknownHostException, ClassNotFoundException, IOException {

		this.setVisible(true);

	}

	public String getId() {

		return textId.getText();

	}

	public String getItem() {

		return txtName.getText();

	}

	public String getItemQuantity() {

		return txtItemQuantity.getText();

	}



	public void showMessage(String message) {

		JOptionPane.showMessageDialog(this, message);

	}

}
