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
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.ILeague;

public class ParseRootcoaches implements IParseRootElement {
	private static Logger logger = LogManager.getLogger(ParseRootcoaches.class);

	@Override
	public void parseRootElement(ILeague leagueModelObj, JSONObject jsonObject) throws Exception {
		List<ICoach> coachesList = parseCoaches(jsonObject);
		leagueModelObj.setCoaches(coachesList);
	}

	private List<ICoach> parseCoaches(JSONObject jsonObject) {
		logger.debug("Parsing Coaches from json");
		ICoach coach;
		List<ICoach> coachesList = new ArrayList<>();
		JSONArray coachesJSONArray = (JSONArray) jsonObject.get(Attributes.COACHES.getAttribute());
		Iterator<JSONObject> coachesListIterator = coachesJSONArray.iterator();
		while (coachesListIterator.hasNext()) {

			JSONObject coachListJsonObject = coachesListIterator.next();
			// get playerName
			String name = (String) coachListJsonObject.get(Attributes.NAME.getAttribute());
			// get skating
			float skating = ((Double) coachListJsonObject.get(Attributes.SKATING.getAttribute())).floatValue();
			// get shooting
			float shooting = ((Double) coachListJsonObject.get(Attributes.SHOOTING.getAttribute())).floatValue();
			// get checking
			float checking = ((Double) coachListJsonObject.get(Attributes.CHECKING.getAttribute())).floatValue();
			// get saving
			float saving = ((Double) coachListJsonObject.get(Attributes.SAVING.getAttribute())).floatValue();

			coach = DefaultHockeyFactory.makeCoach(skating, shooting, checking, saving, name);
			coachesList.add(coach);
		}
		return coachesList;
	}

}
