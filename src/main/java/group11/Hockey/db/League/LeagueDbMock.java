package group11.Hockey.db.League;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.*;

public class LeagueDbMock implements ILeagueDb {

	League leadDbMockObject = new League();
	List<League> leagueList = new ArrayList<League>();
	League league = new League();

	public LeagueDbMock() {
		leadDbMockObject = populateLeagueObject();
	}

	@Override
	public boolean insertLeagueInDb(ILeague league) {
//		Team team = new Team();
//		team.setTeamName(team1.getTeamName());
//		Division division = new Division(divisionName, Arrays.asList(team));
//		Conference conference = new Conference("conferenceName", Arrays.asList(division));
//		league.setLeagueName(league1.getLeagueName());
//		league.setConferences(Arrays.asList(conference));
//		leagueList.add(league);
//		if (league.getLeagueName().equalsIgnoreCase(league1.getLeagueName()) && leagueList.size() == 2) {
//			return true;
//		} else {
//			return false;
		// }
		return false;
	}

	public League populateLeagueObject() {
		League league;
		List<Team> teamsList = new ArrayList<Team>();
		GeneralManager gm = new GeneralManager("Kenley", "normal");
		Team team = new Team("Toronto Maples", gm, null, null);
		teamsList.add(team);

		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic Division", teamsList);
		divisionsList.add(atlanticDivision);
		List<IConference> conferenceList = new ArrayList<>();
		IConference conference = new Conference("Westeren Conference", divisionsList);
		conferenceList.add(conference);
		// add free agents
		league = new League("NHL", conferenceList, null, null, null, null);
		leagueList.add(league);
		return league;

	}

	@Override
	public ILeague loadLeague() {
		// TODO Auto-generated method stub
		return null;
	}
}
