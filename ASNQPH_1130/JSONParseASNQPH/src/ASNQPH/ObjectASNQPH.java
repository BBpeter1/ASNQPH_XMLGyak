package ASNQPH;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ObjectASNQPH {
	
	public static void main(String[] args) {
		JSONObject Jobj = new JSONObject(); 	
		JSONParser parser = new JSONParser();		
		
		try (Reader reader = new FileReader("vizsgakASNQPH.json")) {
	
	        JSONObject jsonObject = (JSONObject) parser.parse(reader);	        
	
	        Jobj.put("root", jsonObject.get("root"));
	       
	        String jsonText = Jobj.toString();
	        System.out.println(jsonText);

	
	    } catch (IOException |  ParseException e) {
	        e.printStackTrace();
	    }
	}
}

