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
			// Db CONNECTION
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
			//html table
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

	public String updatePayment(String ID, String name, String type, String bank, String tot, String status)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payments SET NameOnCard=?,cardType=?,bank=?,totAmount=?,Status=? WHERE p_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, bank);
			preparedStmt.setDouble(4, Double.parseDouble(tot));
			preparedStmt.setString(5, status);

			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String p_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from payments where p_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(p_id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateStat(String ID)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payments SET Status='REFUND'  WHERE p_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
