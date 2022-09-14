package com.javacodegeeks.testng.maven;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import junit.framework.Assert;


public class TestNgMavenExample {

	static int ForeignPlayers=0;
	static int numberOfWicketKeepers=0;
	
	@Test
	public void exampleOfTestNgMaven() throws IOException, ParseException {
		
		FileReader reader = null;
		try {
			reader = new FileReader("C:\\Users\\user\\Desktop\\Anuja\\testNgMavenExample\\testNgMavenExample\\teamRCB.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONParser jsonParser = new JSONParser();	
		Object obj = jsonParser.parse(reader);
		JSONObject jsonObject = (JSONObject)obj;
		String name =(String) jsonObject.get("name");
		System.out.println("Name of the team "+name);
		JSONArray players = (JSONArray)jsonObject.get("player");
	
		players.forEach( player -> parseplayerObject( (JSONObject) player ) );
		
        if(ForeignPlayers>4) {
        	System.out.println("Team contains more than 4 foreign players");
        	Assert.assertFalse(true);
        }        
        
        if(numberOfWicketKeepers<1) {
        	System.out.println("Team contains less than 1 wicket-keeper");
        	Assert.assertFalse(true);
        	
        }
		
	}
	
	private static void parseplayerObject(JSONObject employee) 
    {
        
    	String countryName = (String) employee.get("country");         
        if (!countryName.equalsIgnoreCase("India")) {
        	ForeignPlayers++;
        }
    	
    	String role = (String) employee.get("role");         
        if(role.equalsIgnoreCase("Wicket-keeper")) {
        	numberOfWicketKeepers++;
        }
    }
    
	
	
}
