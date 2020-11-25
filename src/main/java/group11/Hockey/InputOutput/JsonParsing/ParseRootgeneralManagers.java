package group11.Hockey.InputOutput.JsonParsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Coach;
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
		Iterator<JSONObject> gmListIterator = gameplayConfigJson.iterator();
		while (gmListIterator.hasNext()) {

			JSONObject gmListJsonObject = gmListIterator.next();
			// get Name
			String name = (String) gmListJsonObject.get(Attributes.NAME.getAttribute());
			// get Personality
			String personality = (String) gmListJsonObject.get(Attributes.PERSONALITY.getAttribute());

			generalManager = new GeneralManager(name, personality);
			generalManagersList.add(generalManager);
		}
		leagueModelObj.setGeneralManagers(generalManagersList);
	}
}
