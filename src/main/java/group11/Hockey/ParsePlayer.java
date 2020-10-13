package group11.Hockey;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.models.Player;

public class ParsePlayer extends ValidateJson implements IAttribute {

	@Override
	public JSONArray getJsonArray(JSONObject jsonObj) {
		return (JSONArray) jsonObj.get("players");
	}

	@Override
	public <T> void setAttributes(T object, JSONObject listJsonObject) throws Exception {
		// get playerName
		String playerName = (String) listJsonObject.get("playerName");
		((Player) object).setPlayerName(playerName);
		// get position
		String position = (String) listJsonObject.get("position");
		((Player) object).setPosition(position);
		// get captain
		Boolean captain = (Boolean) listJsonObject.get("captain");
		((Player) object).setCaptain(captain);

	}

	

}
