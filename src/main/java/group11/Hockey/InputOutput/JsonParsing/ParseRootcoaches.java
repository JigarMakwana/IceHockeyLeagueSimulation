package group11.Hockey.InputOutput.JsonParsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.League;

public class ParseRootcoaches implements IParseRootElement {

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		List<Coach> coachesList = parseCoaches(jsonObject);
		leagueModelObj.setCoaches(coachesList);
	}

	private List<Coach> parseCoaches(JSONObject jsonObject) {
		Coach coach;
		List<Coach> coachesList = new ArrayList<Coach>();
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

			coach = new Coach(skating, shooting, checking, saving, name);
			coachesList.add(coach);
		}
		return coachesList;
	}

}
