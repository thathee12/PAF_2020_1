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

	public String readPayments() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment Id</th><th>Name on card</th><th>Card type</th><th>bank</th><th>Total amount</th><th>Status</th></tr>";
			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String p_id = Integer.toString(rs.getInt("p_id"));
				String NameOnCard = rs.getString("NameOnCard");
				String cardType = rs.getString("cardType");
				String bank = rs.getString("bank");
				String totAmount = Double.toString(rs.getDouble("totAmount"));
				String Status = rs.getString("Status");

				// Add into the html table
				output += "<tr><td>" + p_id + "</td>";
				output += "<td>" + NameOnCard + "</td>";
				output += "<td>" + cardType + "</td>";
				output += "<td>" + bank + "</td>";
				output += "<td>" + totAmount + "</td>";
				output += "<td>" + Status + "</td>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
