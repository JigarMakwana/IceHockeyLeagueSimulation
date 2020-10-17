package group11.Hockey;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;

public class ParseConference extends ValidateJson implements IAttribute {

	private List<String> conferenceNamesList= new ArrayList<String>();
	@Override
	public JSONArray getJsonArray(JSONObject jsonObj) {
		return (JSONArray) jsonObj.get("conferences");
	}

	@Override
	public <T> void setAttributes(T object, JSONObject listJsonObject) throws Exception {
		setConferenceName((Conference) object,listJsonObject);
		
		ParseJson parseJson = new ParseJson();
		List<Division> divisionsList = parseJson.parseElement(Division.class, listJsonObject, new ParseDivision());
		setDivisions((Conference) object,divisionsList);

	}
	
	private void setConferenceName(Conference conference, JSONObject listJsonObject) throws Exception {
		String conferenceName = (String) listJsonObject.get("conferenceName");
		if (isNameAlreadyExists(conferenceNamesList, conferenceName)) {
			throw new Exception("Conference name " + conferenceName + " already exists");
		} else {
			conference.setConferenceName(conferenceName);
		}
	}
	
	private void setDivisions(Conference conference, List<Division> divisionsList) {
		conference.setDivisions(divisionsList);
	}

}
