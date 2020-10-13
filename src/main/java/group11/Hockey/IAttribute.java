package group11.Hockey;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IAttribute {
	public JSONArray getJsonArray(JSONObject jsonObj);

	public <T> void setAttributes(T object, JSONObject listJsonObject) throws Exception;

}
