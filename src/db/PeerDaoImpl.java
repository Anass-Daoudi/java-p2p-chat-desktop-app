package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Peer;

public class PeerDaoImpl implements PeerDao {
	public int insert(Peer p) {
		ConnectionDB con = new ConnectionDB();
		String req = "INSERT INTO peer (email,username,password) Values('" + p.getEmail() + "','" + p.getUserName()
				+ "','" + p.getPassword() + "');";
		int r = 0;
		try {
			r = con.getSt().executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.getCon().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public boolean find(Peer p, boolean b) {
		ConnectionDB con = new ConnectionDB();
		String req = "SELECT * FROM peer WHERE email='" + p.getEmail() + "'";
		if (b)
			req += "AND password='" + p.getPassword() + "';";
		ResultSet rs;
		try {
			rs = con.getSt().executeQuery(req);

			if (rs.next()) {
				p.setId(rs.getInt(1));
				p.setUserName(rs.getString(3));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.getCon().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public int insertSocketInformation(Peer p) {
		ConnectionDB con = new ConnectionDB();
		String req = "Update  peer SET ip='" + p.getIp() + "', port='" + p.getPort() + "' WHERE id=" + p.getId() + ";";
		int r = 0;
		try {
			r = con.getSt().executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.getCon().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	public int connected(Peer p, boolean b) {
		ConnectionDB con = new ConnectionDB();
		String req = "Update Peer SET connected=";

		req += b + " WHERE id=" + p.getId() + ";";
		int r = 0;
		try {
			r = con.getSt().executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.getCon().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	public ArrayList<Peer> recoverConnectedFriends(Peer p) {
		ArrayList<Peer> lf = new ArrayList<Peer>();
		ConnectionDB con = new ConnectionDB();
		String req = "select idfriend from friends where id=" + p.getId() + ";";
		ResultSet rs = null;
		try {
			rs = con.getSt().executeQuery(req);
			ResultSet rs1;
			Statement st = con.getCon().createStatement();
			while (rs.next()) {
				String req1 = "select * from peer where id=" + rs.getInt(1) + " AND connected=true ;";
				rs1 = st.executeQuery(req1);
				if (rs1.next()) {
					Peer fp = new Peer(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getString(5),
							rs1.getInt(6), rs1.getBoolean(7));
					lf.add(fp);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.getCon().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lf;
	}

	// modified
	public int addfriend(Peer p, Peer q) {
		ConnectionDB con = new ConnectionDB();
		String req = "INSERT INTO friends values (" + p.getId() + "," + q.getId() + "),(" + q.getId() + "," + p.getId()
				+ ");";
		int r = 0;
		try {

			r = con.getSt().executeUpdate(req);
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean isFriend(Peer p, Peer q) {
		ConnectionDB con = new ConnectionDB();
		String req = "SELECT * FROM friends WHERE id=" + p.getId() + " and idfriend=" + q.getId() + ";";
		ResultSet rs = null;
		try {
			rs = con.getSt().executeQuery(req);
			if (rs.next())
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				con.getCon().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String> rechercher(Peer p, String s) {
		ConnectionDB con = new ConnectionDB();
		ResultSet rs = null;
		ArrayList<String> fl = new ArrayList<String>();
		String req = "SELECT username FROM Peer,friends WHERE idfriend=peer.id AND username REGEXP '^" + s
				+ "'AND connected=true AND friends.id=" + p.getId() + " ;";
		try {
			rs = con.getSt().executeQuery(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				fl.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.getCon().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fl;
	}

}