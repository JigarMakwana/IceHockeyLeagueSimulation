package group11.Hockey.db.League;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;

public class LeagueDbMock implements ILeagueDb {

	League leadDbMockObject = new League();
	List<League> leagueList = new ArrayList<League>();
	League league = new League();

	public LeagueDbMock() {
		leadDbMockObject = populateLeagueObject();
	}

	@Override
	public boolean insertLeagueInDb(ILeague league1, String conferenceName, String divisionName, ITeam team1, ICoach coach,
			IPlayer player) {
		Team team = new Team();
		team.setTeamName(team1.getTeamName());
		Division division = new Division(divisionName, Arrays.asList(team));
		Conference conference = new Conference("conferenceName", Arrays.asList(division));
		league.setLeagueName(league1.getLeagueName());
		league.setConferences(Arrays.asList(conference));
		leagueList.add(league);
		if (league.getLeagueName().equalsIgnoreCase(league1.getLeagueName()) && leagueList.size() == 2) {
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
