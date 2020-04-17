package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.Date;



public class Appointment {
	
	// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_ca", "root", "1Thathi/");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return con;
		}
		
public String insertAppointment( String placed_date , String appoint_date, String cause, String patientID, String doctorID, String day) {
			
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "ERROR while connecting to the database for inserting!";
				}

				// create a prepared statement
				String query = " insert into appointment(`appointmentID`,`pADate`,`aDate`,`aCause`,`aPatient`,`aDoctor`,`aDay`)"
						+ " values (?, ?, ?, ?, ?, ?,?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				//Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(placed_date); 
				
				
			
				 
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2,placed_date); 
				
				//preparedStmt.setDate(2, java.sql.Date(date1.getTime()); 
				
				preparedStmt.setString(3,appoint_date); 
				preparedStmt.setString(4, cause);
				preparedStmt.setInt(5, Integer.parseInt(patientID));
				preparedStmt.setInt(6, Integer.parseInt(doctorID));
				preparedStmt.setString(7, day);
				
				

				// execute the statement 
				preparedStmt.execute(); con.close();

				output = "Inserted successfully";
				
			} catch (Exception e) {
				output = "ERROR while inserting the Appointment!";
				System.err.println(e.getMessage());
			}

			return output;
		}
		

		//commit
}
