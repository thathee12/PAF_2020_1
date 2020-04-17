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
}
