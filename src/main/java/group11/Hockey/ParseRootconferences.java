package group11.Hockey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.models.Coach;
import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

public class ParseRootconferences extends ValidateJson implements IParseRootElement {
	private List<String> conferenceNamesList = new ArrayList<String>();
	private List<String> divisionNamesList = new ArrayList<String>();
	private List<String> teamNameList = new ArrayList<String>();

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		// ParseJson parseJson = new ParseJson();

		// parse Conference
		// List<Conference> conferencesList = parseJson.parseElement(Conference.class,
		// jsonObject, new ParseConference());
		List<Conference> conferencesList = parseConferences(jsonObject);
		leagueModelObj.setConferences(conferencesList);

	}

	private List<Conference> parseConferences(JSONObject jsonObject) throws Exception {
		Conference conference;
		List<Conference> conferencesList = new ArrayList<Conference>();
		JSONArray ConferenceJSONArray = (JSONArray) jsonObject.get("conferences");
		Iterator<JSONObject> ConferencesListIterator = ConferenceJSONArray.iterator();
		while (ConferencesListIterator.hasNext()) {
			conference = new Conference();

			// get conferenceName
			JSONObject ConferencesListJsonObject = ConferencesListIterator.next();
			String conferenceName = (String) ConferencesListJsonObject.get("conferenceName");
			if (isNameAlreadyExists(conferenceNamesList, conferenceName)) {
				throw new Exception("Conference name " + conferenceName + " already exists");
			} else {
				conference.setConferenceName(conferenceName);
			}

			// parse Divisions
			List<Division> divisionsList = parseDivisions(ConferencesListJsonObject);
			conference.setDivisions(divisionsList);
			conferencesList.add(conference);

		}
		return conferencesList;
	}

	private List<Division> parseDivisions(JSONObject ConferencesListJsonObject) throws Exception {
		Division divisionObj;
		List<Division> divisionsList = new ArrayList<Division>();
		JSONArray divisionsJSONArray = (JSONArray) ConferencesListJsonObject.get("divisions");
		Iterator<JSONObject> divisionsListIterator = divisionsJSONArray.iterator();
		while (divisionsListIterator.hasNext()) {

			divisionObj = new Division();
			// get division name
			JSONObject divisionsListJsonObject = divisionsListIterator.next();
			String divisionName = (String) divisionsListJsonObject.get("divisionName");
			if (isNameAlreadyExists(divisionNamesList, divisionName)) {
				throw new Exception("Division name " + divisionName + " already exists");
			} else {
				divisionObj.setDivisionName(divisionName);

				// parse Teams
				List<Team> teamsList = parseTeams(divisionsListJsonObject);

				divisionObj.setTeams(teamsList);
				divisionsList.add(divisionObj);

			}
		}
		return divisionsList;
	}

	private List<Team> parseTeams(JSONObject divisionsListJsonObject) throws Exception {
		Team teamObj;
		List<Team> teamsList = new ArrayList<Team>();
		JSONArray teamsJSONArray = (JSONArray) divisionsListJsonObject.get("teams");
		Iterator<JSONObject> teamsListIterator = teamsJSONArray.iterator();
		while (teamsListIterator.hasNext()) {
			teamObj = new Team();
			JSONObject teamsListJsonObject = teamsListIterator.next();
			// get team name
			String teamName = (String) teamsListJsonObject.get("teamName");

			if (isNameAlreadyExists(teamNameList, teamName)) {
				throw new Exception("Team name " + teamName + " already exists");
			} else {
				teamObj.setTeamName(teamName);
			}

			// get generalManager
			String generalManager = (String) teamsListJsonObject.get("generalManager");
			teamObj.setGeneralManager(generalManager);

			setHeadCoach(teamObj, teamsListJsonObject);

			// parse Teams
			List<Player> playersList = parsePlayers(teamsListJsonObject);
//			if (playersList.size() > 0) {
//				teamObj.setPlayers(playersList);
//
//				// check valid captian
//				int captians = checkCaptains(playersList);
//				if (captians == 0) {
//					throw new Exception("Team " + teamName + " has no captain");
//				} else if (captians > 1) {
//					throw new Exception("Team " + teamName + " has " + captians + " captains");
//				}
//			}
			if (playersList != null && playersList.size() > 0) {
				teamObj.setPlayers(playersList);

				if (hasInvalidCaptain(playersList)) {
					throw new Exception("Team " + teamName + " has no/more captain(s)");
				}
			}
			teamsList.add(teamObj);

		}
		return teamsList;
	}

	private void setHeadCoach(Team team, JSONObject listJsonObject) {
		JSONObject headCoach = (JSONObject) listJsonObject.get("headCoach");
		String name = (String) headCoach.get("name");
		float skating = ((Double) headCoach.get("skating")).floatValue();
		float shooting = ((Double) headCoach.get("shooting")).floatValue();
		float checking = ((Double) headCoach.get("checking")).floatValue();
		float saving = ((Double) headCoach.get("saving")).floatValue();
		Coach coach = new Coach(skating, shooting, checking, saving, name);

		team.setHeadCoach(coach);
	}

	private List<Player> parsePlayers(JSONObject teamsListJsonObject) {

		Player playerObj;
		List<Player> playersList = new ArrayList<Player>();

		JSONArray playersJsonArray = (JSONArray) teamsListJsonObject.get("players");
		if (playersJsonArray == null) {
			return playersList;
		}
		Iterator<JSONObject> playersListIterator = playersJsonArray.iterator();
		while (playersListIterator.hasNext()) {
			playerObj = new Player();
			JSONObject playersListJsonObject = playersListIterator.next();
			// get playerName
			String playerName = (String) playersListJsonObject.get("playerName");
			playerObj.setPlayerName(playerName);
			// get position
			String position = (String) playersListJsonObject.get("position");
			playerObj.setPosition(position);
			// get captain
			Boolean captain = (Boolean) playersListJsonObject.get("captain");
			playerObj.setCaptain(captain);
			// get age
			float age = ((Long) playersListJsonObject.get("age")).floatValue();
			playerObj.setAge(age);
			// get skating
			float skating = ((Long) playersListJsonObject.get("skating")).intValue();
			playerObj.setSkating(skating);
			// get shooting
			float shooting = ((Long) playersListJsonObject.get("shooting")).intValue();
			playerObj.setShooting(shooting);
			// get checking
			float checking = ((Long) playersListJsonObject.get("checking")).intValue();
			playerObj.setChecking(checking);
			// get saving
			float saving = ((Long) playersListJsonObject.get("saving")).intValue();
			playerObj.setSaving(saving);
			playersList.add(playerObj);

		}
		return playersList;
	}

//	private int checkCaptains(List<Player> playersList) {
//		int captains = 0;
//		for (Player player : playersList) {
//			if (player.getCaptain()) {
//				captains = captains + 1;
//			}
//		}
//		return captains;
//	}

}
