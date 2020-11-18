package group11.Hockey.InputOutput.JsonParsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class ParseRootconferences extends ValidateJsonSchema implements IParseRootElement {
	private List<String> conferenceNamesList = new ArrayList<String>();
	private List<String> divisionNamesList = new ArrayList<String>();
	private List<String> teamNameList = new ArrayList<String>();

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		List<Conference> conferencesList = parseConferences(jsonObject);
		leagueModelObj.setConferences(conferencesList);
	}

	private List<Conference> parseConferences(JSONObject jsonObject) throws Exception {
		Conference conference;
		List<Conference> conferencesList = new ArrayList<Conference>();
		JSONArray ConferenceJSONArray = (JSONArray) jsonObject.get(Attributes.CONFERENCES.getAttribute());
		Iterator<JSONObject> ConferencesListIterator = ConferenceJSONArray.iterator();
		while (ConferencesListIterator.hasNext()) {
			conference = new Conference();
			// get conferenceName
			JSONObject ConferencesListJsonObject = ConferencesListIterator.next();
			String conferenceName = (String) ConferencesListJsonObject.get(Attributes.CONFERENCENAME.getAttribute());
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
		JSONArray divisionsJSONArray = (JSONArray) ConferencesListJsonObject.get(Attributes.DIVISIONS.getAttribute());
		Iterator<JSONObject> divisionsListIterator = divisionsJSONArray.iterator();
		while (divisionsListIterator.hasNext()) {
			divisionObj = new Division();
			// get division name
			JSONObject divisionsListJsonObject = divisionsListIterator.next();
			String divisionName = (String) divisionsListJsonObject.get(Attributes.DIVISIONNAME.getAttribute());
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
		JSONArray teamsJSONArray = (JSONArray) divisionsListJsonObject.get(Attributes.TEAMS.getAttribute());
		Iterator<JSONObject> teamsListIterator = teamsJSONArray.iterator();
		while (teamsListIterator.hasNext()) {
			teamObj = new Team();
			JSONObject teamsListJsonObject = teamsListIterator.next();
			// get team name
			String teamName = (String) teamsListJsonObject.get(Attributes.TEAMNAME.getAttribute());
			if (isNameAlreadyExists(teamNameList, teamName)) {
				throw new Exception("Team name " + teamName + " already exists");
			} else {
				teamObj.setTeamName(teamName);
			}
			// get generalManager
			String generalManager = (String) teamsListJsonObject.get(Attributes.GENERALMANAGER.getAttribute());
			teamObj.setGeneralManager(generalManager);
			setHeadCoach(teamObj, teamsListJsonObject);
			// parse Teams
			List<Player> playersList = parsePlayers(teamsListJsonObject);
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
		JSONObject headCoach = (JSONObject) listJsonObject.get(Attributes.HEADCOACH.getAttribute());
		String name = (String) headCoach.get(Attributes.NAME.getAttribute());
		float skating = ((Double) headCoach.get(Attributes.SKATING.getAttribute())).floatValue();
		float shooting = ((Double) headCoach.get(Attributes.SHOOTING.getAttribute())).floatValue();
		float checking = ((Double) headCoach.get(Attributes.CHECKING.getAttribute())).floatValue();
		float saving = ((Double) headCoach.get(Attributes.SAVING.getAttribute())).floatValue();
		Coach coach = new Coach(skating, shooting, checking, saving, name);
		team.setHeadCoach(coach);
	}

	private List<Player> parsePlayers(JSONObject teamsListJsonObject) {
		Player playerObj;
		List<Player> playersList = new ArrayList<Player>();
		JSONArray playersJsonArray = (JSONArray) teamsListJsonObject.get(Attributes.PLAYERS.getAttribute());
		if (playersJsonArray == null) {
			return playersList;
		}
		Iterator<JSONObject> playersListIterator = playersJsonArray.iterator();
		while (playersListIterator.hasNext()) {
			playerObj = new Player();
			JSONObject playersListJsonObject = playersListIterator.next();
			// get playerName
			String playerName = (String) playersListJsonObject.get(Attributes.PLAYERNAME.getAttribute());
			playerObj.setPlayerName(playerName);
			// get position
			String position = (String) playersListJsonObject.get(Attributes.POSITION.getAttribute());
			playerObj.setPosition(position);
			// get captain
			Boolean captain = (Boolean) playersListJsonObject.get(Attributes.CAPTAIN.getAttribute());
			playerObj.setCaptain(captain);
			// get age
			float age = ((Long) playersListJsonObject.get(Attributes.AGE.getAttribute())).floatValue();
			playerObj.setAge(age);
			// get skating
			float skating = ((Long) playersListJsonObject.get(Attributes.SKATING.getAttribute())).intValue();
			playerObj.setSkating(skating);
			// get shooting
			float shooting = ((Long) playersListJsonObject.get(Attributes.SHOOTING.getAttribute())).intValue();
			playerObj.setShooting(shooting);
			// get checking
			float checking = ((Long) playersListJsonObject.get(Attributes.CHECKING.getAttribute())).intValue();
			playerObj.setChecking(checking);
			// get saving
			float saving = ((Long) playersListJsonObject.get(Attributes.SAVING.getAttribute())).intValue();
			playerObj.setSaving(saving);
			playersList.add(playerObj);
		}
		return playersList;
	}

	@Override
	public StateMachineState startState() {
		// TODO Auto-generated method stub
		return null;
	}
}
