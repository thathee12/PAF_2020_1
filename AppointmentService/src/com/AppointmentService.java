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
}
