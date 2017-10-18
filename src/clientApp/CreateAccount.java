package clientApp;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;

public class CreateAccount extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Button button_1;
	private TextField textField,textField_1;

	public CreateAccount() {
		this.setSize(850, 550);
		this.setBackground(new Color(255, 242, 231));
		this.setLayout(null);

		Panel panel = new Panel();
		panel.setBounds(0, 31, 834, 72);
		this.add(panel);

		Label label = new Label("Create New Account");
		label.setFont(new Font("Calibri", Font.BOLD, 30));
		label.setForeground(new Color(114, 127, 144));
		label.setAlignment(Label.CENTER);
		panel.add(label);

		Panel panel_1 = new Panel();
		panel_1.setBounds(186, 151, 531, 49);
		this.add(panel_1);
		panel_1.setLayout(null);

		Label label_1 = new Label("Email");
		label_1.setForeground(new Color(114, 127, 144));
		label_1.setFont(new Font("Calibri", Font.BOLD, 22));
		label_1.setBounds(30, 10, 136, 35);
		panel_1.add(label_1);

		textField = new TextField();
		textField.setForeground(new Color(114, 127, 144));
		textField.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField.setBounds(232, 10, 277, 35);
		panel_1.add(textField);

		Panel panel_2 = new Panel();
		panel_2.setLayout(null);
		panel_2.setBounds(186, 217, 531, 49);
		this.add(panel_2);

		Label label_2 = new Label("Username");
		label_2.setForeground(new Color(114, 127, 144));
		label_2.setFont(new Font("Calibri", Font.BOLD, 22));
		label_2.setBounds(30, 10, 136, 35);
		panel_2.add(label_2);

		textField_1 = new TextField();
		textField_1.setForeground(new Color(114, 127, 144));
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_1.setBounds(232, 10, 277, 35);
		panel_2.add(textField_1);

		Panel panel_3 = new Panel();
		panel_3.setLayout(null);
		panel_3.setBounds(186, 281, 531, 49);
		this.add(panel_3);

		Label label_3 = new Label("Password");
		label_3.setForeground(new Color(114, 127, 144));
		label_3.setFont(new Font("Calibri", Font.BOLD, 22));
		label_3.setBounds(30, 10, 136, 35);
		panel_3.add(label_3);

		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(114, 127, 144));
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 22));
		passwordField.setBounds(232, 10, 277, 35);
		panel_3.add(passwordField);

		Panel panel_4 = new Panel();
		panel_4.setLayout(null);
		panel_4.setBounds(186, 351, 531, 49);
		this.add(panel_4);

		Label label_4 = new Label("Repeat password");
		label_4.setForeground(new Color(114, 127, 144));
		label_4.setFont(new Font("Calibri", Font.BOLD, 22));
		label_4.setBounds(30, 10, 196, 35);
		panel_4.add(label_4);

		passwordField_1 = new JPasswordField();
		passwordField_1.setForeground(new Color(114, 127, 144));
		passwordField_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		passwordField_1.setBounds(232, 10, 277, 35);
		panel_4.add(passwordField_1);

		button_1 = new Button("Sign up");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setForeground(new Color(255, 242, 231));
		button_1.setFont(new Font("Calibri", Font.BOLD, 22));
		button_1.setBackground(new Color(114, 127, 144));
		button_1.setBounds(422, 430, 124, 42);
		add(button_1);
		this.setVisible(true);
	}

	public Button getSignUpButton() {
		return button_1;
	}

	public TextField getEmail() {
		return textField;
	}
	
	public TextField getUserName() {
		return textField_1;
	}

	public JPasswordField getPassword() {
		return passwordField;
	}

	public JPasswordField getRepeatedPassword() {
		return passwordField_1;
	}
}