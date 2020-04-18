package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

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

	public String insertPayment(String name, String type, String bank, String tot)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payments(`p_id`,`NameOnCard`,`cardType`,`bank`,`totAmount`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, bank);
			preparedStmt.setDouble(5, Double.parseDouble(tot));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
