package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Hospital {
	
	//connection class created
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_ca", "root", "8172429Amma*");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 } 
	
		//insert class created
		public String inserthosbranch(String hosRegno, String hosname,String hostype, String hosCharge, String Address,String city,String Email ) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into hospital (hosID, hosRegno, hosname, hostype, hosCharge,Address,city,Email)"
						+ " values (?, ?, ?, ?, ?,?,?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, hosRegno);
				preparedStmt.setString(3, hosname);
				preparedStmt.setString(4, hostype);
				preparedStmt.setDouble(5, Double.parseDouble(hosCharge));
				preparedStmt.setString(6, Address);
				preparedStmt.setString(7, city);
				preparedStmt.setString(8, Email);
				

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
		//end of the insertion class
}
