/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.InputOutput.JsonParsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.IGeneralManager;
import group11.Hockey.BusinessLogic.models.ILeague;

public class ParseRootgeneralManagers implements IParseRootElement {
	private static Logger logger = LogManager.getLogger(ParseRootgeneralManagers.class);

	@Override
	public void parseRootElement(ILeague leagueModelObj, JSONObject jsonObject) throws Exception {
		logger.info("Parsing Managers from Json");
		List<IGeneralManager> generalManagersList = new ArrayList<>();
		IGeneralManager generalManager;
		JSONArray gameplayConfigJson = (JSONArray) jsonObject.get(Attributes.GENERALMANAGERS.getAttribute());
		Iterator<JSONObject> gmListIterator = gameplayConfigJson.iterator();
		while (gmListIterator.hasNext()) {

			JSONObject gmListJsonObject = gmListIterator.next();
			// get Name
			String name = (String) gmListJsonObject.get(Attributes.NAME.getAttribute());
			// get Personality
			String personality = (String) gmListJsonObject.get(Attributes.PERSONALITY.getAttribute());

			generalManager = DefaultHockeyFactory.makeGeneralManager(name, personality);
			generalManagersList.add(generalManager);
		}
		leagueModelObj.setGeneralManagers(generalManagersList);
	}
}
