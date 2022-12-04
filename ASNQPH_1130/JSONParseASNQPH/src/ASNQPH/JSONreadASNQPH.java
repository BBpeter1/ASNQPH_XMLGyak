package ASNQPH;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;

import javax.security.auth.login.AccountException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;


public class JSONreadASNQPH {
	
	public static void main(String[] args) {
		ConditionObject Jobj = new ConditionObject(); 	
		JSONParser parser = new JSONParser();		
		
		try (Reader reader = new FileReader("vizsgakASNQPH.json")) {
	
	        ConditionObject jsonObject = (ConditionObject) parser.parse(reader);	        
	
	        Jobj.put("root", jsonObject.get("root"));
	       
	        String jsonText = Jobj.toString();
	        System.out.println(jsonText);

	
	    } catch (IOException |  AccountException e) {
	        e.printStackTrace();
	    }
	}
}
