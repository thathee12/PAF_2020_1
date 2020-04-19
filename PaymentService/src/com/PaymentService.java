package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/HospitalManagement")
public class PaymentService {
	Payment payObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return payObj.readPayments();
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insert(@FormParam("NameOnCard") String NameOnCard, @FormParam("cardType") String cardType,
			@FormParam("bank") String bank, @FormParam("totAmount") String totAmount) {
		String output = payObj.insertPayment(NameOnCard, cardType, bank, totAmount);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject payObject = new JsonParser().parse(paymentData).getAsJsonObject();
		// Read the values from the JSON object
		String p_id = payObject.get("p_id").getAsString();
		String NameOnCard = payObject.get("NameOnCard").getAsString();
		String cardType = payObject.get("cardType").getAsString();
		String bank = payObject.get("bank").getAsString();
		String totAmount = payObject.get("totAmount").getAsString();
		String Status = payObject.get("Status").getAsString();
		String output = payObj.updatePayment(p_id, NameOnCard, cardType, bank, totAmount, Status);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String p_id = doc.select("p_id").text();
		String output = payObj.deletePayment(p_id);
		return output;
	} 
	
	
}
	
