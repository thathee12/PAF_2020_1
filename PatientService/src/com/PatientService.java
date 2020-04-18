package com;

import model.Patient;

import java.sql.Date;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/patient")
public class PatientService {
	Patient patientObj = new Patient();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getPatientDetails() {
		return patientObj.getPatientDetails();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPatientDetails(@FormParam("patientID") String patientID, @FormParam("firstname") String firstname,
			@FormParam("lastName") String lastName, @FormParam("gender") String gender,
			@FormParam("phone") String phone, @FormParam("address") String address, @FormParam("age") String age,
			@FormParam("bloodGroup") String bloodGroup, @FormParam("nextOfKin") String nextOfKin) {
		String output = patientObj.addPatientDetails(patientID, firstname, lastName, gender, phone, address, age,
				bloodGroup, nextOfKin);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatientDetails(String patientData) {
		// Convert the input string to a JSON object
		JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
		// Read the values from the JSON object
		String Pno = patientObject.get("Pno").getAsString();
		String patientID = patientObject.get("patientID").getAsString();
		String firstname = patientObject.get("firstname").getAsString();
		String lastName = patientObject.get("lastName").getAsString();
		String gender = patientObject.get("gender").getAsString();
		String phone = patientObject.get("phone").getAsString();
		String address = patientObject.get("address").getAsString();
		String age = patientObject.get("age").getAsString();
		String bloodGroup = patientObject.get("bloodGroup").getAsString();
		String nextOfKin = patientObject.get("nextOfKin").getAsString();
		String output = patientObj.updatePatientDetails(Pno, patientID, firstname, lastName, gender, phone, address,
				age, bloodGroup, nextOfKin);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatientDetails(String patientData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());
		// Read the value from the element <patientID>
		String patientID = doc.select("patientID").text();
		String output = patientObj.deletePatientDetails(patientID);
		return output;
	}
}