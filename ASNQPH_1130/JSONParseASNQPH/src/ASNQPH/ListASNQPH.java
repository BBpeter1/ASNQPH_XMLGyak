package ASNQPH;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ListASNQPH {

	public static void main(String[] args) {
		
		JSONObject Jobj = new JSONObject();
		
		Jobj.put("neptunkod", "kkklll");
		Jobj.put("hallgato", "hallgato neve");
		
		JSONArray list = new JSONArray();
        list.add("PTI");
        list.add("M�rn�kinf�");
        list.add("Teszt�rt�k");

        Jobj.put("szak", list);
		
		String jsonText = Jobj.toString();
        System.out.println(jsonText);


	}

}