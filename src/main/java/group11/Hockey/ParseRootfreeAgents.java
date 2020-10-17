package group11.Hockey;

import java.util.List;

import org.json.simple.JSONObject;

import group11.Hockey.models.League;
import group11.Hockey.models.Player;

public class ParseRootfreeAgents implements IParseRootElement {

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		ParseJson parseJson = new ParseJson();
		// parse freeAgents
		List<Player> playersList = parseJson.parseElement(Player.class, jsonObject, new ParseFreeAgent());
		leagueModelObj.setFreeAgents(playersList);

	}

}
