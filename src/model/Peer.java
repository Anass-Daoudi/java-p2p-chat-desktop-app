package model;

import java.awt.Label;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Peer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Peer actualFriend;
	private String email;
	private String password;
	private String userName;
	private String ip;
	private boolean connected = true;
	private int port;
	public HashMap<Peer, Conversation> conversations = new HashMap<Peer, Conversation>();
	private transient ServerSocket serverSocket;
	public HashMap<Label, Peer> labelPeer = new HashMap<Label, Peer>();
	public transient HashMap<Peer, Socket> peerSocket = new HashMap<Peer, Socket>();

	public Peer(String email) {
		this.email = email;

	}

	public Peer(int id, String email, String userName, String ip, int port, boolean connected) {
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.ip = ip;
		this.port = port;
		this.connected = connected;
	}

	public Peer(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public void generateSocketInformation() {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			this.ip = inetAddress.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void startListening() {
		try {
			serverSocket = new ServerSocket(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		port = serverSocket.getLocalPort();
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setActualFriend(Peer actualfriend) {
		this.actualFriend = actualfriend;
	}

	public Peer getActualFriend() {
		return actualFriend;
	}

	public String getIp() {
		return ip;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
