package group11.Hockey.db.League;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

public class LeagueDbMock implements ILeagueDb {
	
	League leadDbMockObject = new League();
	List<League> leagueList = new ArrayList<League>();
	
	public LeagueDbMock(){
		leadDbMockObject = populateLeagueObject();
	}

	@Override
	public int insertLeagueInDb(String leagueName) {
		League leagueObject = new League();
		leagueObject.setLeagueName(leagueName);
		leagueList.add(leagueObject);
		return leagueList.size();
	}
	
	public League populateLeagueObject() {
		League league;
		List<Team> teamsList = new ArrayList<Team>();
		Team team = new Team("Toronto Maples", "Kenley", "Scott", null);
		teamsList.add(team);

		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic Division", teamsList);
		divisionsList.add(atlanticDivision);
		List<Conference> conferenceList = new ArrayList<Conference>();
		Conference conference = new Conference("Westeren Conference", divisionsList);
		conferenceList.add(conference);
		// add free agents
		league = new League("NHL", conferenceList, null);
		leagueList.add(league);
		return league;

	}

	@Override
	public boolean checkLeagueNameExitsInDb(String leagueName) {
		boolean isLeagueNameValid;
		if(leadDbMockObject.getLeagueName().equalsIgnoreCase(leagueName)) {
			isLeagueNameValid = false;
		}
		else {
			isLeagueNameValid = true;
		}
		return isLeagueNameValid;
	}
}
