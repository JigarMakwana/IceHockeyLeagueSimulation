package group11.Hockey;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ParseFreeAgent extends ParsePlayer{
	@Override
	public JSONArray getJsonArray(JSONObject jsonObj) {
		return (JSONArray) jsonObj.get("freeAgents");
	}
}
