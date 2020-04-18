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

import model.Schedule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

@Path("/Schedules")
public class ScheduleService {
	
	
	Schedule schedule = new Schedule();
	
	// read schedules
	@GET
	@Path("/ScheduleDetails")
	@Produces(MediaType.TEXT_HTML)
	public String readAllTypes() {
		return schedule.readSchedule();
	}
	
	// add Schedule
			@POST
			@Path("/ScheduleDetails")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String enterType(String ScheduleData) {
				
			// Convert the input string to a JSON object
			JsonObject jsonObject = new JsonParser().parse(ScheduleData).getAsJsonObject();

			// Read the values from the JSON object
			int docId =jsonObject.get("docId").getAsInt();
			String workingDay = jsonObject.get("workingDay").getAsString();
			int max_num_of_patients =jsonObject.get("max_num_of_patients").getAsInt();
						
			String output = schedule.addSchedule(docId, workingDay, max_num_of_patients);
			return output;

		}

}
