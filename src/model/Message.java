package model;

public class Message {
	private String message;
	private String username;

	public Message(Peer peer, String message) {
		this.username = peer.getUserName();
		this.message = message;
	}

	public String toString() {
		return username + ": " + message;
	}
}
