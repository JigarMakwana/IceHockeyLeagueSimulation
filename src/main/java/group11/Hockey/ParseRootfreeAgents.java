package group11.Hockey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.models.League;
import group11.Hockey.models.Player;

public class ParseRootfreeAgents implements IParseRootElement {

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		//ParseJson parseJson = new ParseJson();
		// parse freeAgents
		//List<Player> playersList = parseJson.parseElement(Player.class, jsonObject, new ParseFreeAgent());
		List<Player> playersList=parseFreeAgent(jsonObject);
		leagueModelObj.setFreeAgents(playersList);

	}

	private List<Player> parseFreeAgent(JSONObject teamsListJsonObject) {
		Player freeAgentsObj;
		List<Player> freeAgentsList = new ArrayList<Player>();
		JSONArray playersList = (JSONArray) teamsListJsonObject.get("freeAgents");
		Iterator<JSONObject> playersListIterator = playersList.iterator();
		while (playersListIterator.hasNext()) {

			JSONObject playersListJsonObject = playersListIterator.next();
			// get playerName
			String playerName = (String) playersListJsonObject.get("playerName");
			// get position
			String position = (String) playersListJsonObject.get("position");
			// get age
			float age = ((Long) playersListJsonObject.get("age")).floatValue();
			// get skating
			float skating = ((Long) playersListJsonObject.get("skating")).intValue();
			// get shooting
			float shooting = ((Long) playersListJsonObject.get("shooting")).intValue();
			// get checking
			float checking = ((Long) playersListJsonObject.get("checking")).intValue();
			// get saving
			float saving = ((Long) playersListJsonObject.get("saving")).intValue();

			freeAgentsObj = new Player(skating, shooting, checking, saving, playerName, position, false, true, age);
			freeAgentsList.add(freeAgentsObj);
		}
		return freeAgentsList;
	}

}
