package group11.Hockey;

import java.util.List;

import org.json.simple.JSONObject;

import group11.Hockey.models.Coach;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;

public class ParseRootcoaches implements IParseRootElement {

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		ParseJson parseJson = new ParseJson();
		// parse freeAgents
		List<Coach> coachesList = parseJson.parseElement(Coach.class, jsonObject, new ParseCoaches());
		leagueModelObj.setCoaches(coachesList);

	}

}
