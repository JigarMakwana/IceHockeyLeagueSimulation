package group11.Hockey.InputOutput.JsonParsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.BusinessLogic.models.GeneralManager;
import group11.Hockey.BusinessLogic.models.League;

public class ParseRootgeneralManagers implements IParseRootElement {

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		List<GeneralManager> generalManagersList = new ArrayList<GeneralManager>();
		GeneralManager generalManager;
		JSONArray gameplayConfigJson = (JSONArray) jsonObject.get(Attributes.GENERALMANAGERS.getAttribute());
		for (Object object : gameplayConfigJson) {
			generalManager = new GeneralManager(object.toString());
			generalManagersList.add(generalManager);
		}
		leagueModelObj.setGeneralManagers(generalManagersList);
	}
}
