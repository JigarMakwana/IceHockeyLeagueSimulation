package group11.Hockey.parse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.models.League;
import group11.Hockey.models.GeneralManager;

public class ParseRootgeneralManagers implements IParseRootElement {

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		List<GeneralManager> generalManagersList = new ArrayList<GeneralManager>();
		GeneralManager generalManager;
		JSONArray gameplayConfigJson = (JSONArray) jsonObject.get("generalManagers");
		for (Object object : gameplayConfigJson) {
			generalManager = new GeneralManager(object.toString());
			generalManagersList.add(generalManager);
		}
		leagueModelObj.setGeneralManagers(generalManagersList);

	}

}
