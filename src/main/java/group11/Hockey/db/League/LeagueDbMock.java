package group11.Hockey.db.League;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import group11.Hockey.models.Coach;
import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

public class LeagueDbMock implements ILeagueDb {

	League leadDbMockObject = new League();
	List<League> leagueList = new ArrayList<League>();
	League league = new League();

	public LeagueDbMock() {
		leadDbMockObject = populateLeagueObject();
	}

	@Override
	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
			String generalManger, String headcoachName, float skating, float shooting, float checking, float saving,
			String playerName, String playerPosition, boolean captain, float playerSkating, float playerShooting,
			float playerChecking, float playerSaving, int age) {
		Team team = new Team();
		team.setTeamName(teamName);
		Division division = new Division(divisionName, Arrays.asList(team));
		Conference conference = new Conference("conferenceName", Arrays.asList(division));
		league.setLeagueName(leagueName);
		league.setConferences(Arrays.asList(conference));
		leagueList.add(league);
		if (league.getLeagueName().equalsIgnoreCase(leagueName) && leagueList.size() == 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkLeagueNameExitsInDb(String leagueName) {
		boolean isLeagueNameValid;
		if (leadDbMockObject.getLeagueName().equalsIgnoreCase(leagueName)) {
			isLeagueNameValid = false;
		} else {
			isLeagueNameValid = true;
		}
		return isLeagueNameValid;
	}

	@Override
	public boolean insertLeagueFreeAgents(String leagueName, String freeAgentName, String position, float playerSkating,
			float playerShooting, float playerChecking, float playerSaving, int age) {
		List<Player> listOfFreeAgents = new ArrayList<Player>();
		listOfFreeAgents.add(new Player(0, 0, 0, 0, freeAgentName, position, false,false,age));
		leagueList.get(1).setFreeAgents(listOfFreeAgents);
		if ((leagueList.get(1).getLeagueName() != null
				&& leagueList.get(1).getLeagueName().equalsIgnoreCase(leagueName))
				&& leagueList.get(1).getFreeAgents().size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public League populateLeagueObject() {
		League league;
		List<Team> teamsList = new ArrayList<Team>();
		Team team = new Team("Toronto Maples", "Kenley", null, null);
		teamsList.add(team);

		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic Division", teamsList);
		divisionsList.add(atlanticDivision);
		List<Conference> conferenceList = new ArrayList<Conference>();
		Conference conference = new Conference("Westeren Conference", divisionsList);
		conferenceList.add(conference);
		// add free agents
		league = new League("NHL", conferenceList, null, null, null, null);
		leagueList.add(league);
		return league;

	}
}
