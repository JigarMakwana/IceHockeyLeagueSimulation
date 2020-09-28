package group11.Hockey;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.FreeAgent;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

public class ImportJson implements IFileOperations {

	private Object fileObj;
	private League leagueModelObj;
	List<String> teamNameList = new ArrayList<String>();
	List<String> generalManagerList = new ArrayList<String>();
	List<String> headCoachList = new ArrayList<String>();
	List<String> divisionNamesList = new ArrayList<String>();
	List<String> conferenceNamesList = new ArrayList<String>();

	public League parseFile(String fileName) {
		JSONParser parser = new JSONParser();
		try {
			fileObj = parser.parse(new FileReader(fileName));
			leagueModelObj = parseJson();

		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
			// return null;
		}
		return leagueModelObj;
	}

	private League parseJson() {
		leagueModelObj = new League();
		try {
			JSONObject jsonObject = (JSONObject) fileObj;
			// parse league name
			String leagueName = (String) jsonObject.get("leagueName");
			leagueModelObj.setLeagueName(leagueName);
			// parse Conferences
			List<Conference> conferencesList = parseConferences(jsonObject);
			leagueModelObj.setConferences(conferencesList);
			// parse freeAgents
			List<FreeAgent> freeAgentsList = parseFreeAgent(jsonObject);
			leagueModelObj.setFreeAgents(freeAgentsList);

		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		return leagueModelObj;
	}

	private List<Conference> parseConferences(JSONObject jsonObject) throws Exception {
		Conference conference;
		List<Conference> conferencesList = new ArrayList<Conference>();
		JSONArray ConferenceJSONArray = (JSONArray) jsonObject.get("conferences");
		Iterator<JSONObject> ConferencesListIterator = ConferenceJSONArray.iterator();
		System.out.println("ConferencesList size -> " + ConferenceJSONArray.size());
		while (ConferencesListIterator.hasNext()) {
			conference = new Conference();
			// get conferenceName
			JSONObject ConferencesListJsonObject = ConferencesListIterator.next();
			String conferenceName = (String) ConferencesListJsonObject.get("conferenceName");
			if (conferenceNamesList.contains(conferenceName.toLowerCase())) {
				throw new Exception("Conference name " + conferenceName + " already exists");
			} else {
				conference.setConferenceName(conferenceName);
				conferenceNamesList.add(conferenceName.toLowerCase());
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
		System.out.println("divisionsList size -> " + divisionsJSONArray.size());
		while (divisionsListIterator.hasNext()) {

			divisionObj = new Division();
			// get division name
			JSONObject divisionsListJsonObject = divisionsListIterator.next();
			String divisionName = (String) divisionsListJsonObject.get("divisionName");
			if (divisionNamesList.contains(divisionName.toLowerCase())) {
				throw new Exception("Division name " + divisionName + " already exists");
			} else {
				divisionObj.setDivisionName(divisionName);
				divisionNamesList.add(divisionName.toLowerCase());
			}

			// parse Teams
			List<Team> teamsList = parseTeams(divisionsListJsonObject);

			divisionObj.setTeams(teamsList);
			divisionsList.add(divisionObj);

		}
		return divisionsList;
	}

	private List<Team> parseTeams(JSONObject divisionsListJsonObject) throws Exception {
		Team teamObj;
		List<Team> teamsList = new ArrayList<Team>();
		JSONArray teamsJSONArray = (JSONArray) divisionsListJsonObject.get("teams");
		Iterator<JSONObject> teamsListIterator = teamsJSONArray.iterator();
		System.out.println("teamsList size -> " + teamsJSONArray.size());
		while (teamsListIterator.hasNext()) {
			teamObj = new Team();
			JSONObject teamsListJsonObject = teamsListIterator.next();
			// get team name
			String teamName = (String) teamsListJsonObject.get("teamName");
			if (teamNameList.contains(teamName.toLowerCase())) {
				throw new Exception("Team name " + teamName + " already exists");
			} else {
				teamObj.setTeamName(teamName);
				teamNameList.add(teamName.toLowerCase());
			}

			// get generalManager
			String generalManager = (String) teamsListJsonObject.get("generalManager");
			if (generalManagerList.contains(generalManager.toLowerCase())) {
				throw new Exception("GeneralManager " + generalManager + " already exists");
			} else {
				teamObj.setGeneralManager(generalManager);
				generalManagerList.add(generalManager.toLowerCase());
			}

			// get headCoach
			String headCoach = (String) teamsListJsonObject.get("headCoach");
			if (headCoachList.contains(headCoach.toLowerCase())) {
				throw new Exception("HeadCoach " + headCoach + " already exists");
			} else {
				teamObj.setHeadCoach(headCoach);
				headCoachList.add(headCoach.toLowerCase());
			}

			// parse Teams
			List<Player> playersList = parsePlayers(teamsListJsonObject);
			teamObj.setPlayers(playersList);
			teamsList.add(teamObj);

			// check valid captian
			int captians = checkCaptains(playersList);
			if (captians == 0) {
				throw new Exception("Team " + teamName + " has no captain");
			} else if (captians > 1) {
				throw new Exception("Team " + teamName + " has " + captians + " captains");
			}

		}
		return teamsList;
	}

	private List<Player> parsePlayers(JSONObject teamsListJsonObject) {

		Player playerObj;
		List<Player> playersList = new ArrayList<Player>();

		JSONArray playersJsonArray = (JSONArray) teamsListJsonObject.get("players");
		Iterator<JSONObject> playersListIterator = playersJsonArray.iterator();
		System.out.println("playersList size -> " + playersJsonArray.size());
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
			playersList.add(playerObj);

		}
		return playersList;
	}

	private List<FreeAgent> parseFreeAgent(JSONObject teamsListJsonObject) {
		FreeAgent freeAgentsObj;
		List<FreeAgent> freeAgentsList = new ArrayList<FreeAgent>();
		JSONArray playersList = (JSONArray) teamsListJsonObject.get("freeAgents");
		Iterator<JSONObject> playersListIterator = playersList.iterator();
		System.out.println("freeAgent playersList size -> " + playersList.size());
		while (playersListIterator.hasNext()) {

			JSONObject playersListJsonObject = playersListIterator.next();
			// get playerName
			String playerName = (String) playersListJsonObject.get("playerName");
			// get position
			String position = (String) playersListJsonObject.get("position");
			// get captain
			Boolean captain = (Boolean) playersListJsonObject.get("captain");

			freeAgentsObj = new FreeAgent(playerName, position, captain);
			freeAgentsList.add(freeAgentsObj);
		}
		return freeAgentsList;
	}

	private int checkCaptains(List<Player> playersList) {
		int captains = 0;
		for (Player player : playersList) {
			if (player.getCaptain()) {
				captains = captains + 1;
			}
		}
		return captains;
	}

}
