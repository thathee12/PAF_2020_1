package com;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Doctor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

@Path("/Doctors")
public class DoctorService {

Doctor doctor = new Doctor();
	
	// read Doctors 
		@GET
		@Path("/DoctorDetails")
		@Produces(MediaType.TEXT_HTML)
		public String readAllTypes() {
			return doctor.readDoctor();
		}
		
		// add Doctor
		
				@POST 
				@Path("/DoctorDetails")
				@Consumes(MediaType.APPLICATION_JSON)
				@Produces(MediaType.TEXT_PLAIN)
				public String enterType(String DoctorData) {
					// Convert the input string to a JSON object
					JsonObject jsonObject = new JsonParser().parse(DoctorData).getAsJsonObject();

					// Read the values from the JSON object
					int docId = jsonObject.get("docId").getAsInt();
					String fname = jsonObject.get("docFname").getAsString();
					String lname = jsonObject.get("docLname").getAsString();
					String specialization = jsonObject.get("specialization").getAsString();
					int phone =jsonObject.get("contact").getAsInt();
					String email = jsonObject.get("email").getAsString();
					String address = jsonObject.get("address").getAsString();
					String gender = jsonObject.get("gender").getAsString();
					float charge =jsonObject.get("docCharge").getAsFloat();
					int hospital = jsonObject.get("hospital").getAsInt();
					String password = jsonObject.get("password").getAsString();
					
					String output = doctor.addDoctor(docId, fname, lname, specialization, phone, email, address, gender, charge, hospital, password);
					return output;

				}
				
}
