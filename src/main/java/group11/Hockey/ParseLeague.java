package group11.Hockey;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.models.Conference;
import group11.Hockey.models.League;

public class ParseLeague extends ValidateJson implements IAttribute {

	public ParseLeague(ILeagueDb leagueDb) {
		super(leagueDb);
	}
	
	@Override
	public JSONArray getJsonArray(JSONObject jsonObj) {
		return null;
	}

	@Override
	public <T> void setAttributes(T object, JSONObject listJsonObject) throws Exception {
		String leagueName = (String) listJsonObject.get("leagueName");
		if (isValidLeagueName(leagueName)) {
			((League) object).setLeagueName(leagueName);
		} else {
			throw new Exception(leagueName + " -> League name already exists in the system");
		}

		ParseJson parseJson = new ParseJson();
		List<Conference> conferencesList = parseJson.parseElement(Conference.class, listJsonObject,
				new ParseConference());
		((League) object).setConferences(conferencesList);

	}

}
