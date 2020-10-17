package group11.Hockey;

import java.util.List;

import org.json.simple.JSONObject;

import group11.Hockey.models.Conference;
import group11.Hockey.models.League;

public class ParseRootconferences implements IParseRootElement{

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		ParseJson parseJson = new ParseJson();
		
		// parse Conference
		List<Conference> conferencesList = parseJson.parseElement(Conference.class, jsonObject,
				new ParseConference());
		leagueModelObj.setConferences(conferencesList);
		
	}

}
