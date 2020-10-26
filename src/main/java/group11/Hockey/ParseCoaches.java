package group11.Hockey;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.models.Coach;

public class ParseCoaches implements IAttribute {

	@Override
	public JSONArray getJsonArray(JSONObject jsonObj) {
		return (JSONArray) jsonObj.get("coaches");
	}

	@Override
	public <T> void setAttributes(T object, JSONObject listJsonObject) throws Exception {
		// get playerName
		String name = (String) listJsonObject.get("name");
		((Coach) object).setName(name);
		// get skating
		float skating = ((Double) listJsonObject.get("skating")).floatValue();
		((Coach) object).setSkating(skating);
		// get shooting
		float shooting = ((Double) listJsonObject.get("shooting")).floatValue();
		((Coach) object).setShooting(shooting);
		// get checking
		float checking = ((Double) listJsonObject.get("checking")).floatValue();
		((Coach) object).setChecking(checking);
		// get saving
		float saving = ((Double) listJsonObject.get("saving")).floatValue();
		((Coach) object).setSaving(saving);

	}

}
