package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Payment {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf_ca", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
