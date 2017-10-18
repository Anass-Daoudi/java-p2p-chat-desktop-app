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

public class Login extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private Button button, button_1;
	private TextField textField;

	public Login() {
		setSize(850, 550);
		setBackground(new Color(255, 242, 231));
		setLayout(null);

		button = new Button("Sign in");
		button.setForeground(new Color(255, 242, 231));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBackground(new Color(114, 127, 144));
		button.setFont(new Font("Calibri", Font.BOLD, 22));
		button.setBounds(355, 248, 124, 42);
		add(button);

		textField = new TextField();
		textField.setForeground(new Color(114, 127, 144));
		textField.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField.setBounds(285, 143, 277, 35);
		add(textField);

		Label label = new Label("Sign in");
		label.setForeground(new Color(114, 127, 144));
		label.setFont(new Font("Calibri", Font.BOLD, 30));
		label.setAlignment(Label.CENTER);
		label.setBounds(227, 44, 352, 61);
		add(label);

		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(114, 127, 144));
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 22));
		passwordField.setBounds(285, 190, 277, 35);
		add(passwordField);

		button_1 = new Button("Sign up");
		button_1.setForeground(new Color(255, 242, 231));

		button_1.setFont(new Font("Calibri", Font.BOLD, 22));
		button_1.setBackground(new Color(114, 127, 144));
		button_1.setBounds(355, 317, 124, 42);
		add(button_1);
		setVisible(true);
	}

	public Button getSignInButton() {
		return button;
	}

	public Button getSignUpButton() {
		return button_1;
	}

	public TextField getEmail() {
		return textField;
	}

	public JPasswordField getPassword() {
		return passwordField;
	}
}