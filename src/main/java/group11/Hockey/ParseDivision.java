package group11.Hockey;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.models.Division;
import group11.Hockey.models.Team;

public class ParseDivision extends ValidateJson implements IAttribute {

	private List<String> divisionNamesList= new ArrayList<String>();
	@Override
	public JSONArray getJsonArray(JSONObject jsonObj) {
		return (JSONArray) jsonObj.get("divisions");
	}

	@Override
	public <T> void setAttributes(T object, JSONObject listJsonObject) throws Exception {
		String divisionName = (String) listJsonObject.get("divisionName");
		if (isNameAlreadyExists(divisionNamesList, divisionName)) {
			throw new Exception("Division name " + divisionName + " already exists");
		} else {
			((Division) object).setDivisionName(divisionName);
		}
		ParseJson parseJson = new ParseJson();
		List<Team> teamsList = parseJson.parseElement(Team.class, listJsonObject, new ParseTeams());
		((Division) object).setTeams(teamsList);

	}

}
