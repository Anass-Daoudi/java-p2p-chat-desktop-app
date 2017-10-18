package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
	Connection con = null;
	Statement st;

	public ConnectionDB() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Statement getSt() {
		return st;
	}

	public Connection getCon() {
		return con;
	}

}
