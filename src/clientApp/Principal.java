package clientApp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;

import db.PeerDaoImpl;
import model.Conversation;
import model.Peer;

public class Principal {
	private CardLayout cardLayout;
	private JFrame frame;
	private static Peer peer;
	private static Panel panel;
	private static Chat chat;
	private PrintStream ps;

	public static void main(String[] args) {
		new Principal();
	}

	public Principal() {
		frame = new JFrame();
		frame.setSize(850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardLayout = new CardLayout(0, 0);
		frame.getContentPane().setLayout(cardLayout);
		frame.setLocationRelativeTo(null);
		Login login = new Login();
		// optimise memory :do not create this object(createAccount) unless it s
		// necessary!
		CreateAccount createAccount = new CreateAccount();
		chat = new Chat();
		frame.getContentPane().add("login", login);
		frame.getContentPane().add("createAccount", createAccount);
		frame.getContentPane().add("chat", chat);

		login.getSignUpButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), "createAccount");

				createAccount.getSignUpButton().addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						PeerDaoImpl peerDaoImpl = new PeerDaoImpl();
						peer = new Peer(createAccount.getEmail().getText(), createAccount.getPassword().getText());

						// add test about equality of the two passwords

						if (!peerDaoImpl.find(peer, false)) {
							peer.setUserName(createAccount.getUserName().getText());
							peerDaoImpl.insert(peer);
							cardLayout.show(frame.getContentPane(), "login");
						} else {
							createAccount.getEmail().setText("Email already exists!");
							createAccount.getPassword().setText("");
							createAccount.getRepeatedPassword().setText("");
						}
					}

				});
			}
		});
		login.getSignInButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PeerDaoImpl peerDaoImpl = new PeerDaoImpl();
				peer = new Peer(login.getEmail().getText(), login.getPassword().getText());
				boolean exists = peerDaoImpl.find(peer, true);

				chat.getSendButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							ps = new PrintStream(peer.peerSocket.get(peer.getActualFriend()).getOutputStream());
							ps.println(chat.getMessageZone().getText());
							peer.conversations.get(peer.getActualFriend()).addMessage(peer,
									chat.getMessageZone().getText());
							chat.getConversationZone().setText(peer.conversations.get(peer.getActualFriend()).getS());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				
				chat.getAdd().addActionListener(new ActionListener(){
        			public void actionPerformed(ActionEvent e){
        				Peer p=new Peer( chat.getRecherche().getText());
        				if(peerDaoImpl.find(p,false)){
        					if(!peerDaoImpl.isFriend(peer,p)){
        						peerDaoImpl.addfriend(peer, p);
        					}
        				}
        			}
        		});

				chat.getDisconnectButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						peerDaoImpl.connected(peer, false);
						cardLayout.show(frame.getContentPane(), "login");
						// add in the future a signal to all connected friend to
						// informe them about my disconnection to delete me from
						// their list of connected friends and close sockets
						// with me
					}
				});

				if (exists) {
					cardLayout.show(frame.getContentPane(), "chat");
					peer.generateSocketInformation();
					peer.startListening();
					peerDaoImpl.insertSocketInformation(peer);
					peerDaoImpl.connected(peer, true);

					Thread thread = new Thread(new AcceptSockets(peer.getServerSocket()));
					thread.start();

					ArrayList<Peer> connectedFriends = peerDaoImpl.recoverConnectedFriends(peer);
					panel = chat.getPlaceOfConnectedFriends();

					for (Peer connectedPeer : connectedFriends) {
						Socket socket = null;
						try {
							socket = new Socket(connectedPeer.getIp(), connectedPeer.getPort());
							Thread t = new Thread(new MessageListener(socket, connectedPeer));
							t.start();
							ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
							outToServer.writeObject(peer);
							peer.peerSocket.put(connectedPeer, socket);
							// outToServer.close();
						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						peer.conversations.put(connectedPeer, new Conversation());
						Label label = new Label(connectedPeer.getUserName());
						label.setForeground(Color.white);
						label.setFont(new Font("Dialog", Font.BOLD, 16));
						peer.labelPeer.put(label, connectedPeer);
						label.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								peer.setActualFriend(peer.labelPeer.get(label));
								chat.getConversationZone()
										.setText(peer.conversations.get(peer.getActualFriend()).getS());
								chat.getFriendUserName().setText(label.getText());
								chat.getOnlinePlace().setText("ONLINE");
							}
						});
						panel.add(label);
						panel.revalidate();
					}
				} else {
					login.getEmail().setText("");
					login.getPassword().setText("");
				}
			}
		});
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private static class MessageListener implements Runnable {
		private BufferedReader br;
		private Peer friendPeer;

		public MessageListener(Socket socket, Peer friendPeer) {
			this.friendPeer = friendPeer;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			while (true) {
				try {
					if (br.ready()) {
						peer.conversations.get(friendPeer).addMessage(friendPeer, br.readLine());
						chat.getConversationZone().setText(peer.conversations.get(peer.getActualFriend()).getS());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class AcceptSockets implements Runnable {
		private ServerSocket serverSocket;

		public AcceptSockets(ServerSocket serverSocket) {
			this.serverSocket = serverSocket;
		}

		public void run() {
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

					try {
						Peer peerFriend = (Peer) inFromServer.readObject();
						peer.peerSocket.put(peerFriend, socket);
						Thread t = new Thread(new MessageListener(socket, peerFriend));
						t.start();
						peer.conversations.put(peerFriend, new Conversation());
						Label label = new Label(peerFriend.getUserName());
						label.setForeground(Color.white);
						label.setFont(new Font("Dialog", Font.BOLD, 16));
						peer.labelPeer.put(label, peerFriend);
						label.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								peer.setActualFriend(peer.labelPeer.get(label));
								chat.getConversationZone()
										.setText(peer.conversations.get(peer.getActualFriend()).getS());
								// System.out.println(peer.conversations.get(peer.getActualFriend()).getS());
								chat.getFriendUserName().setText(label.getText());
								chat.getOnlinePlace().setText("ONLINE");
							}
						});
						panel.add(label);
						panel.revalidate();
						// inFromServer.close();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}