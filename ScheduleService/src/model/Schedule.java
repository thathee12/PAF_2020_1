package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

public class Schedule {
	
	public Connection connect() {
		Connection con = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospitaldb", "root", "");	
			System.out.print("Successfully connected");

		} catch (Exception e) {

			System.out.print("connection fail");
			e.printStackTrace();
			System.out.print(e);
		}

		return con;
	}
	
	
	//------------------------- Read schedules from the table -------------------------
	
		public String readSchedule() {

			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>DoctorId</th>"
						+ "<th>WorkingDay</th>" 
						+ "<th>PatientCount</th></tr>"; 
								
				String query = "SELECT * FROM schedule";
				
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(query);

				
				while (resultSet.next()) {
					int docId = resultSet.getInt("docId");
					String workingDay =  resultSet.getString("workingDay");
					int max_num_of_patients =  resultSet.getInt("max_num_of_patients");
									
					output += "<tr><td>" + docId + "</td><td>" + workingDay + "</td><td>" + max_num_of_patients + "</td></tr>";
				
				}

				con.close();
				
				output += "</table>";

			} catch (Exception e) {
				output = "Error while reading the Schedules";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//------------------------- Insert a schedule into the table -------------------------
		
		public String addSchedule(int docId, String workingDay, int max_num_of_patients) {

			String output = "";
			try {

				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = "INSERT INTO schedule (docId, workingDay, max_num_of_patients) VALUES (?, ?, ?)";
				PreparedStatement preparedStatement = con.prepareStatement(query);

				// binding values
				preparedStatement.setInt(1, docId);
				preparedStatement.setString(2, workingDay);
				preparedStatement.setInt(3, max_num_of_patients);
			

				// execute the statement
				preparedStatement .execute();
				con.close();
				output = "Inserted successfully";

			} catch (Exception e) {
				output = "Error while inserting a Schedule";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//------------------------- Delete a schedule from the table -------------------------
		
		public String removeSchedule(int docId, String workingDay) {
			String output = "";
			try {

				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "DELETE FROM schedule WHERE docId=? AND workingDay =?";
				PreparedStatement preparedStatement = con.prepareStatement(query);

				// binding values
				preparedStatement.setInt(1, docId);
				preparedStatement.setString(2, workingDay);

				// execute the statement
				preparedStatement.execute();
				con.close();
				output = "Deleted successfully";

			} catch (Exception e) {

				output = "Error while deleting the Schedule";
				System.err.println(e.getMessage());
			}

			return output;
		}
}
