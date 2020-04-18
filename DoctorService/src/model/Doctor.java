package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

public class Doctor {
	
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
	
	//------------------------- Read details from table -------------------------
	
		public String readDoctor() {

			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				
				//preparing the HTML table to be displayed 
				output = "<table border=\"1\"><tr><th>DocId</th>"
						+ "<th>Fname</th>"
						+ "<th>Lname</th>"
						+ "<th>Specialization</th>"
						+ "<th>Contact</th>"
						+ "<th>Email</th>"
						+ "<th>Address</th>"
						+ "<th>Gender</th>"
						+ "<th>DocCharge</th>"
						+ "<th>Hospital</th>"
						+ "<th>Password</th></tr>";
				
				String query = "SELECT * FROM doctor";
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				
				//iterate through the rows			
				while (resultSet.next()) {
					int docId =    resultSet.getInt("docId");
					String docFname =  resultSet.getString("docFname");
					String docLname =  resultSet.getString("docLname");
					String specialization =  resultSet.getString("specialization");
					int contact =  resultSet.getInt("contact");
					String email =  resultSet.getString("email");
					String address =  resultSet.getString("address");
					String gender =  resultSet.getString("gender");
					float docCharge = resultSet.getFloat("docCharge");
					int hospital =  resultSet.getInt("hospital");
					String passowrd =  resultSet.getString("password");
					
		
					//add into the HTML table
					output += "<tr><td>" + docId + "</td><td>" + docFname + "</td><td>" + docLname + "</td><td>" + specialization + "</td><td>" + contact + "</td><td>" + email + "</td><td>" + address + "</td><td>" + gender + "</td><td>" + docCharge + "</td><td>" + hospital + "</td><td>" + passowrd + "</td></tr>";

				}

				con.close();
				
				output += "</table>";

			} catch (Exception e) {
				output = "Error while reading the Doctors";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//------------------------- Inserting doctors to the table -------------------------
		
			public String addDoctor(int docId, String docFname, String docLname, String specialization, int contact, String email, String address, String gender, float docCharge, int hospital, String password) {

				String output = "";
				try {

					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database";
					}

					// create a prepared statement
					String query = " INSERT INTO doctor (docId, docFname, docLname, specialization, contact, email, address, gender, docCharge, hospital, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = con.prepareStatement(query);

					// binding values
					preparedStatement.setInt(1, docId);
					preparedStatement.setString(2, docFname);
					preparedStatement.setString(3, docLname);
					preparedStatement.setString(4, specialization);
					preparedStatement.setInt(5, contact);
					preparedStatement.setString(6, email);
					preparedStatement.setString(7, address);
					preparedStatement.setString(8, gender);
					preparedStatement.setFloat(9, docCharge);
					preparedStatement.setInt(10, hospital);
					preparedStatement.setString(11, password);
					

					// execute the statement
					preparedStatement.execute();
					con.close();
					output = "Inserted successfully";

				} 
				
				catch (Exception e) {
					output = "Error while inserting a Doctor";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			
}
