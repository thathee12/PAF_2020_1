package com;

import java.sql.Date;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

import model.Appointment;


//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Appointment")
public class AppointmentService {
	
Appointment appointObj = new Appointment ();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment() {
		return appointObj.readAppointment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("pADate") String pADate, @FormParam("aDate") String aDate,
			@FormParam("aCause") String  aCause, @FormParam("aPatient") String aPatient, @FormParam("aDoctor") String aDoctor,@FormParam("aDay") String aDay) {
		String output = appointObj.insertAppointment(pADate , aDate, aCause, aPatient, aDoctor, aDay);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String AppointData) {
		// Convert the input string to a JSON object
		JsonObject	aObject = new JsonParser().parse(AppointData).getAsJsonObject();

		// Read the values from the JSON object
		String appointmentID = aObject.get("appointmentID").getAsString();
		String pADate = aObject.get("pADate").getAsString();
		String aDate = aObject.get("aDate").getAsString();
		String aCause = aObject.get("aCause").getAsString();
		String aPatient = aObject.get("aPatient").getAsString();
		String aDoctor = aObject.get("aDoctor").getAsString();
		String aDay = aObject.get("aDay").getAsString();

		String output = appointObj.updateAppointment(appointmentID, pADate,aDate,aCause, aPatient, aDoctor, aDay);

		return output;
	}
	

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String aData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(aData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String appointmentID = doc.select("appointmentID").text();

		String output = appointObj.deleteAppointment(appointmentID);

		return output;
	}
}
