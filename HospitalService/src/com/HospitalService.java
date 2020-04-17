package com;

import model.Hospital;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospitals")


public class HospitalService {
	Hospital hosObj = new Hospital();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readhosbranch()
	 {
	 return hosObj.readhosbranch();
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserthosbranch(@FormParam("hosRegno") String hosRegno,
	 @FormParam("hosname") String hosname,
	 @FormParam("hostype") String hostype,
	 @FormParam("hosCharge") String hosCharge,
	 @FormParam("Address") String Address,
	 @FormParam("city") String city,
	 @FormParam("Email") String Email )
	{
	 String output = hosObj.inserthosbranch(hosRegno, hosname, hostype, hosCharge,Address,city,Email);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatehosbranch(String hosData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(hosData).getAsJsonObject();
	//Read the values from the JSON object
	 String hosID = itemObject.get("hosID").getAsString();
	 String hosRegno = itemObject.get("hosRegno").getAsString();
	 String hosname = itemObject.get("hosname").getAsString();
	 String hostype = itemObject.get("hostype").getAsString();
	 String hosCharge = itemObject.get("hosCharge").getAsString();
	 String Address = itemObject.get("Address").getAsString();
	 String city = itemObject.get("city").getAsString();
	 String Email = itemObject.get("Email").getAsString();
	 String output = hosObj.updatehosbranch(hosID, hosRegno, hosname, hostype, hosCharge, Address, city, Email);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletehosbranch(String hosData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(hosData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String hosID = doc.select("hosID").text();
	 String output = hosObj.deletehosbranch(hosID);
	return output;
	}
}
