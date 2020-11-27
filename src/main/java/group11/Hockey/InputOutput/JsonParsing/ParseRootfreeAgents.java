/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.InputOutput.JsonParsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;

public class ParseRootfreeAgents implements IParseRootElement {

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		List<Player> playersList = parseFreeAgent(jsonObject);
		leagueModelObj.setFreeAgents(playersList);
	}

	private List<Player> parseFreeAgent(JSONObject teamsListJsonObject) {
		Player freeAgentsObj;
		List<Player> freeAgentsList = new ArrayList<Player>();
		JSONArray playersList = (JSONArray) teamsListJsonObject.get(Attributes.FREEAGENTS.getAttribute());
		Iterator<JSONObject> playersListIterator = playersList.iterator();
		while (playersListIterator.hasNext()) {

			JSONObject playersListJsonObject = playersListIterator.next();
			// get playerName
			String playerName = (String) playersListJsonObject.get(Attributes.PLAYERNAME.getAttribute());
			// get position
			String position = (String) playersListJsonObject.get(Attributes.POSITION.getAttribute());
			// get age
			float age = ((Long) playersListJsonObject.get(Attributes.AGE.getAttribute())).floatValue();
			// get skating
			float skating = ((Long) playersListJsonObject.get(Attributes.SKATING.getAttribute())).intValue();
			// get shooting
			float shooting = ((Long) playersListJsonObject.get(Attributes.SHOOTING.getAttribute())).intValue();
			// get checking
			float checking = ((Long) playersListJsonObject.get(Attributes.CHECKING.getAttribute())).intValue();
			// get saving
			float saving = ((Long) playersListJsonObject.get(Attributes.SAVING.getAttribute())).intValue();

			freeAgentsObj = new Player(skating, shooting, checking, saving, playerName, position, false, true, age);
			freeAgentsList.add(freeAgentsObj);
		}
		return freeAgentsList;
	}

}
