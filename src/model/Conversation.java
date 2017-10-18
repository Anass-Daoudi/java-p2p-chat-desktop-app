package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Message> conversation = new ArrayList<Message>();

	public void addMessage(Peer peer, String message) {
		conversation.add(new Message(peer, message));
	}

	public String getS() {
		String conv = "";

		for (Message str : conversation) {
			conv += str + "\n\n";
		}
		return conv;
	}
}
