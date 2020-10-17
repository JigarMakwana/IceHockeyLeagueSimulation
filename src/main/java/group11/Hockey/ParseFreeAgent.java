package group11.Hockey;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.models.Player;

public class ParseFreeAgent extends ValidateJson implements IAttribute {
	@Override
	public JSONArray getJsonArray(JSONObject jsonObj) {
		return (JSONArray) jsonObj.get("freeAgents");
	}

	@Override
	public <T> void setAttributes(T object, JSONObject listJsonObject) throws Exception {
		// get playerName
		String playerName = (String) listJsonObject.get("playerName");
		((Player) object).setPlayerName(playerName);
		// get position
		String position = (String) listJsonObject.get("position");
		((Player) object).setPosition(position);
		// get age
		int age = ((Long)  listJsonObject.get("age")).intValue();
		((Player) object).setAge(age);
		// get skating
		float skating = ((Long)  listJsonObject.get("skating")).intValue();
		((Player) object).setSkating(skating);
		// get shooting
		float shooting = ((Long)   listJsonObject.get("shooting")).intValue();
		((Player) object).setShooting(shooting);
		// get checking
		float checking = ((Long)  listJsonObject.get("checking")).intValue();
		((Player) object).setChecking(checking);
		// get saving
		float saving = ((Long)   listJsonObject.get("saving")).intValue();
		((Player) object).setSaving(saving);
	}
}
