/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.InputOutput.JsonParsing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Enums.PlayerDraft;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ParseRootconferences extends ValidateJsonAttributes implements IParseRootElement {
	private List<String> conferenceNamesList = new ArrayList<String>();
	private List<String> divisionNamesList = new ArrayList<String>();
	private List<String> teamNameList = new ArrayList<String>();
	private static Logger logger = LogManager.getLogger(ParseRootconferences.class);

	@Override
	public void parseRootElement(ILeague leagueModelObj, JSONObject jsonObject) throws Exception {
		List<IConference> conferencesList = parseConferences(jsonObject);
		leagueModelObj.setConferences(conferencesList);
	}

	private List<IConference> parseConferences(JSONObject jsonObject) throws Exception {
		logger.debug("Parsing Conference from Json");
		IConference conference;
		List<IConference> conferencesList = new ArrayList<>();
		JSONArray ConferenceJSONArray = (JSONArray) jsonObject.get(Attributes.CONFERENCES.getAttribute());
		Iterator<JSONObject> ConferencesListIterator = ConferenceJSONArray.iterator();
		while (ConferencesListIterator.hasNext()) {
			conference = DefaultHockeyFactory.makeConference();
			// get conferenceName
			JSONObject ConferencesListJsonObject = ConferencesListIterator.next();
			String conferenceName = (String) ConferencesListJsonObject.get(Attributes.CONFERENCENAME.getAttribute());
			if (isNameAlreadyExists(conferenceNamesList, conferenceName)) {
				throw new Exception("Conference name " + conferenceName + " already exists");
			} else {
				conference.setConferenceName(conferenceName);
			}
			// parse Divisions
			List<IDivision> divisionsList = parseDivisions(ConferencesListJsonObject);
			conference.setDivisions(divisionsList);
			conferencesList.add(conference);
		}
		return conferencesList;
	}

	private List<IDivision> parseDivisions(JSONObject ConferencesListJsonObject) throws Exception {
		IDivision divisionObj;
		List<IDivision> divisionsList = new ArrayList<>();
		JSONArray divisionsJSONArray = (JSONArray) ConferencesListJsonObject.get(Attributes.DIVISIONS.getAttribute());
		Iterator<JSONObject> divisionsListIterator = divisionsJSONArray.iterator();
		while (divisionsListIterator.hasNext()) {
			divisionObj = DefaultHockeyFactory.makeDivision();
			// get division name
			JSONObject divisionsListJsonObject = divisionsListIterator.next();
			String divisionName = (String) divisionsListJsonObject.get(Attributes.DIVISIONNAME.getAttribute());
			if (isNameAlreadyExists(divisionNamesList, divisionName)) {
				throw new Exception("Division name " + divisionName + " already exists");
			} else {
				divisionObj.setDivisionName(divisionName);
				// parse Teams
				List<ITeam> teamsList = parseTeams(divisionsListJsonObject);
				divisionObj.setTeams(teamsList);
				divisionsList.add(divisionObj);
			}
		}
		return divisionsList;
	}

	private List<ITeam> parseTeams(JSONObject divisionsListJsonObject) throws Exception {
		ITeam teamObj;
		List<ITeam> teamsList = new ArrayList<>();
		JSONArray teamsJSONArray = (JSONArray) divisionsListJsonObject.get(Attributes.TEAMS.getAttribute());
		Iterator<JSONObject> teamsListIterator = teamsJSONArray.iterator();
		while (teamsListIterator.hasNext()) {
			teamObj = DefaultHockeyFactory.makeTeam();
			JSONObject teamsListJsonObject = teamsListIterator.next();
			// get team name
			String teamName = (String) teamsListJsonObject.get(Attributes.TEAMNAME.getAttribute());
			if (isNameAlreadyExists(teamNameList, teamName)) {
				throw new Exception("Team name " + teamName + " already exists");
			} else {
				teamObj.setTeamName(teamName);
			}
			setGeneralManager(teamObj, teamsListJsonObject);
			setHeadCoach(teamObj, teamsListJsonObject);
			// parse Teams
			List<IPlayer> playersList = parsePlayers(teamsListJsonObject);
			if (playersList != null && playersList.size() > 0) {
				teamObj.setPlayers(playersList);
				if (hasInvalidCaptain(playersList)) {
					throw new Exception("Team " + teamName + " has no/more captain(s)");
				}
			}
			IRoster roster = DefaultHockeyFactory.makeRoster(teamName, playersList);
			teamObj.setRoster(roster);
			List<Boolean> tradedPicks = new ArrayList<>(
					Collections.nCopies(PlayerDraft.PLAYER_DRAFT_ROUNDS.getNumVal(), false));
			teamObj.setTradedPicks(tradedPicks);
			teamsList.add(teamObj);
		}
		return teamsList;
	}

	private void setGeneralManager(ITeam team, JSONObject listJsonObject) {
		JSONObject generalManager = (JSONObject) listJsonObject.get(Attributes.GENERALMANAGER.getAttribute());
		String name = (String) generalManager.get(Attributes.NAME.getAttribute());
		String personality = (String) generalManager.get(Attributes.PERSONALITY.getAttribute());
		IGeneralManager gm = DefaultHockeyFactory.makeGeneralManager();
		gm.setName(name);
		gm.setPersonality(personality);
		team.setGeneralManager(gm);

	}

	private void setHeadCoach(ITeam team, JSONObject listJsonObject) {
		JSONObject headCoach = (JSONObject) listJsonObject.get(Attributes.HEADCOACH.getAttribute());
		String name = (String) headCoach.get(Attributes.NAME.getAttribute());
		float skating = ((Double) headCoach.get(Attributes.SKATING.getAttribute())).floatValue();
		float shooting = ((Double) headCoach.get(Attributes.SHOOTING.getAttribute())).floatValue();
		float checking = ((Double) headCoach.get(Attributes.CHECKING.getAttribute())).floatValue();
		float saving = ((Double) headCoach.get(Attributes.SAVING.getAttribute())).floatValue();
		ICoach coach = DefaultHockeyFactory.makeCoach(skating, shooting, checking, saving, name);
		team.setHeadCoach(coach);
	}

	private List<IPlayer> parsePlayers(JSONObject teamsListJsonObject) {
		IPlayer playerObj;
		List<IPlayer> playersList = new ArrayList<>();
		JSONArray playersJsonArray = (JSONArray) teamsListJsonObject.get(Attributes.PLAYERS.getAttribute());
		if (playersJsonArray == null) {
			return playersList;
		}
		Iterator<JSONObject> playersListIterator = playersJsonArray.iterator();
		while (playersListIterator.hasNext()) {
			playerObj = DefaultHockeyFactory.makePlayer();
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
			int birthDay = ((Long) playersListJsonObject.get(Attributes.BIRTHDAY.getAttribute())).intValue();
			int birthMonth = ((Long) playersListJsonObject.get(Attributes.BIRTHMONTH.getAttribute())).intValue();
			int birthYear = ((Long) playersListJsonObject.get(Attributes.BIRTHYEAR.getAttribute())).intValue();
			playerObj.setSaving(saving);
			playerObj.setBirthDay(birthDay);
			playerObj.setBirthMonth(birthMonth);
			playerObj.setBirthYear(birthYear);
			playersList.add(playerObj);
		}
		return playersList;
	}
}
