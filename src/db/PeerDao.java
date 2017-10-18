package db;

import java.util.ArrayList;

import model.Peer;

public interface PeerDao {
	public abstract boolean isFriend(Peer p, Peer q);

	public abstract int insert(Peer p);

	public abstract boolean find(Peer p, boolean b);// true for signin

	public abstract int insertSocketInformation(Peer p);

	public abstract int connected(Peer p, boolean b);

	public abstract ArrayList<Peer> recoverConnectedFriends(Peer p);

}
