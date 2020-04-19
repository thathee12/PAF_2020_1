package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import been.p_registerBeen;
import util.DBConnection;

public class p_RegisterDao {
	
	public static int registerUser(p_registerBeen r) {
		
		int result=0;
		
		try {
			Connection con = DBConnection.createConnection();
			String query = " insert into user (`first_name`, `last_name`, `e_mail`, `mob_number`, `address`, `NIC_no`, `password`, `cn_password`)" + " values ( ?,?,?, ?, ?, ?,?,?)";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);																							// 'USERS'
											
			preparedStatement.setString(1, r.getFirstname());
			preparedStatement.setString(2, r.getLastname());
			preparedStatement.setString(3, r.getEmail());
			preparedStatement.setString(4, r.getMobile());
			preparedStatement.setString(5, r.getAddress());
			preparedStatement.setString(6, r.getnIC());
			preparedStatement.setString(7, r.getPassword());
			preparedStatement.setString(8, r.getConpassword());
			result= preparedStatement.executeUpdate();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}	

}

