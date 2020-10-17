package group11.Hockey;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.models.Coach;
import group11.Hockey.models.Division;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

public class ParseTeams extends ValidateJson implements IAttribute {
	
	private List<String> teamNameList= new ArrayList<String>();

	@Override
	public JSONArray getJsonArray(JSONObject jsonObj) {
		return (JSONArray) jsonObj.get("teams");
	}

	@Override
	public <T> void setAttributes(T object, JSONObject listJsonObject) throws Exception {
		
		// get team name
		String teamName = (String) listJsonObject.get("teamName");

		if (isNameAlreadyExists(teamNameList, teamName)) {
			throw new Exception("Team name " + teamName + " already exists");
		} else {
			((Team) object).setTeamName(teamName);
		}

		// get generalManager
		String generalManager = (String) listJsonObject.get("generalManager");
		((Team) object).setGeneralManager(generalManager);

		setHeadCoach((Team) object,listJsonObject);
		
		ParseJson parseJson = new ParseJson();
		List<Player> playersList = parseJson.parseElement(Player.class, listJsonObject, new ParsePlayer());
		if (playersList != null && playersList.size() > 0) {
			((Team) object).setPlayers(playersList);

			if (hasInvalidCaptain(playersList)) {
				throw new Exception("Team " + teamName + " has no/more captain(s)");
			}
		}
		
		
	}
	
	private void setHeadCoach(Team team, JSONObject listJsonObject) {
		JSONObject headCoach = (JSONObject) listJsonObject.get("headCoach");
		String name = (String) headCoach.get("name");
		float skating = ((Double) headCoach.get("skating")).floatValue();
		float shooting = ((Double)  headCoach.get("shooting")).floatValue();
		float checking = ((Double)  headCoach.get("checking")).floatValue();
		float saving = ((Double)  headCoach.get("saving")).floatValue();
		Coach coach = new Coach(skating, shooting, checking, saving, name);
		
		team.setHeadCoach(coach);
	}

}
