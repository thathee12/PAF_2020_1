package model;

import java.sql.*;

public class Patient {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String addPatientDetails(String patientID, String firstname, String lastName, String gender, String phone,
			String address, String age, String bloodGroup, String nextOfKin) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement

			String query = " insert into patients (`Pno`,`patientID`,`firstname`,`lastName`,`gender`, `phone`, `address`,`age`,`bloodGroup`, `nextOfKin`)"
					+ " values (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, patientID);
			preparedStatement.setString(3, firstname);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, gender);
			preparedStatement.setString(6, phone);
			preparedStatement.setString(7, address);
			preparedStatement.setString(8, age);
			preparedStatement.setString(9, bloodGroup);
			preparedStatement.setString(10, nextOfKin);

			// execute the statement
			preparedStatement.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the patient detail.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String getPatientDetails() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error dtabase don't have any data.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>patient ID</th><th>Patient firstname</th><th>lastName</th><th>Gender</th><th>Phone</th><th>Address</th><th>Age</th><th>Blood Group</th><th>NextOfKin</th></tr>";
			String query = "select * from patients";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String patientID = rs.getString("patientID");
				String firstname = rs.getString("firstname");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String age = rs.getString("age");
				String bloodGroup = rs.getString("bloodGroup");
				String nextOfKin = rs.getString("nextOfKin");

				// Add into the html table
				output += "<tr><td>" + patientID + "</td>";
				output += "<td>" + firstname + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + bloodGroup + "</td>";
				output += "<td>" + nextOfKin + "</td>";

				// buttons
				output += "<input name=\"patientID\" type=\"hidden\" value=\"" + patientID + "\">"
						+ "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePatientDetails(String Pno, String patientID, String firstname, String lastName, String gender,
			String phone, String address, String age, String bloodGroup, String nextOfKin) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE patients SET `patientID`=?,`firstname`=?, `lastName`=?, `gender`=?, `phone`=?, `address`=?, `age`=?, `bloodGroup`=?, `nextOfKin`=?  WHERE `Pno`=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, patientID);
			preparedStmt.setString(2, firstname);
			preparedStmt.setString(3, lastName);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, address);
			preparedStmt.setString(7, age);
			preparedStmt.setString(8, bloodGroup);
			preparedStmt.setString(9, nextOfKin);
			preparedStmt.setInt(10, Integer.parseInt(Pno));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating a patient detail.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		public String deletePatientDetails(String patientID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
	// create a prepared statement
				String query = "delete from patients where patientID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
				preparedStmt.setString(1, patientID);
	// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the patient detail.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	

}
