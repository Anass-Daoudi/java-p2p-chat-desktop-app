package clientApp;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

public class Chat extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button button, button_1, button_2;
	private Panel panel;
	private TextArea textArea, textArea_1;
	private Label label, label_1;
	private TextField textField_1;

	public Chat() {
		this.setSize(850, 550);
		this.setLayout(null);

		// panel contient la liste des amis connectés
		panel = new Panel();
		panel.setBackground(new Color(114, 127, 144));
		panel.setBounds(0, 51, 200, 411);
		add(panel);

		// panel des messages
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(255, 242, 231));
		panel_1.setBounds(212, 0, 622, 512);
		add(panel_1);
		panel_1.setLayout(null);

		// label en haut
		label = new Label();
		label.setFont(new Font("Astron Boy Video", Font.PLAIN, 24));
		label.setForeground(new Color(104, 117, 134));
		label.setBounds(10, 10, 265, 33);
		panel_1.add(label);

		// online (inchangeable)
		label_1 = new Label("");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(33, 35, 77, 33);
		label_1.setForeground(new Color(50, 205, 50));
		panel_1.add(label_1);

		textArea = new TextArea();
		textArea.setBounds(10, 404, 468, 98);
		textArea.setFont(new Font("Calibri", Font.BOLD, 16));
		panel_1.add(textArea);

		button = new Button("SEND");
		button.setFont(new Font("Calibri", Font.BOLD, 14));
		button.setBackground(new Color(50, 205, 50));
		button.setForeground(new Color(255, 242, 231));
		button.setBounds(492, 460, 116, 42);
		panel_1.add(button);

		button_1 = new Button("Disconnect");
		button_1.setForeground(new Color(255, 242, 231));
		button_1.setFont(new Font("Calibri", Font.BOLD, 14));
		button_1.setBackground(new Color(204, 0, 0));
		button_1.setBounds(492, 10, 116, 42);
		panel_1.add(button_1);

		button_2 = new Button("Add Friend");
		button_2.setForeground(new Color(255, 242, 231));
		button_2.setFont(new Font("Calibri", Font.BOLD, 14));
		button_2.setBackground(new Color(50, 205, 50));
		button_2.setBounds(348, 10, 116, 42);
		panel_1.add(button_2);

		textArea_1 = new TextArea();
		textArea_1.setBounds(10, 93, 468, 284);
		textArea_1.setFont(new Font("Calibri", Font.BOLD, 16));
		panel_1.add(textArea_1);

		Panel panel_2 = new Panel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(200, 0, 13, 512);
		add(panel_2);

		Panel panel_3 = new Panel();
		panel_3.setBounds(0, 462, 200, 50);
		add(panel_3);
		panel_3.setBackground(new Color(114, 127, 144));
		panel_3.setLayout(null);

		textField_1 = new TextField();
		textField_1.setText("Search friend");
		textField_1.setBounds(10, 0, 180, 28);
		textField_1.setForeground(new Color(114, 127, 144));
		textField_1.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_3.add(textField_1);

		Panel panel_4 = new Panel();
		panel_4.setBackground(new Color(114, 127, 144));
		panel_4.setBounds(0, 0, 200, 50);
		add(panel_4);
		panel_4.setLayout(null);

		Label label_2 = new Label("Connected Friends");
		label_2.setForeground(new Color(255, 242, 231));
		label_2.setFont(new Font("Dialog", Font.BOLD, 18));
		label_2.setAlignment(Label.CENTER);
		label_2.setBounds(10, 10, 180, 30);
		panel_4.add(label_2);

	}

	public Button getSendButton() {
		return button;
	}

	public Button getDisconnectButton() {
		return button_1;
	}

	public Panel getPlaceOfConnectedFriends() {
		return panel;
	}

	public TextArea getMessageZone() {
		return textArea;
	}

	public Label getFriendUserName() {
		return label;
	}

	public Label getOnlinePlace() {
		return label_1;
	}

	public TextArea getConversationZone() {
		return textArea_1;
	}

	public Button getAdd() {
		return button_2;
	}

	public TextField getRecherche() {
		return textField_1;
	}
}