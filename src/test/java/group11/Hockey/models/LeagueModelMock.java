package group11.Hockey.models;

import java.util.ArrayList;
import java.util.List;

public class LeagueModelMock {
	private League league;

	public LeagueModelMock() {
		super();
		
		addLeague();
	}
	
	private void addLeague() {
		
		List<Player> playerList = new ArrayList<Player>();
		
		Player player1 = new Player(0, 0, 0, 0, "Player One", "forward", true, false, 0);
		Player player2 = new Player(0, 0, 0, 0, "Player Two", "defense", false, false, 0);
		Player player3 = new Player(0, 0, 0, 0, "Player Three", "goalie", false, false, 0);
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		
		List<Team> teamsList = new ArrayList<Team>();
		Team team1 = new Team("Boston", "Mister Fred", null, playerList);
		Team team2 = new Team("Vancouver Canucks", "John", null, null);
		teamsList.add(team1);
		teamsList.add(team2);
		
		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic", teamsList);
		divisionsList.add(atlanticDivision);
		List<Conference> conferenceList = new ArrayList<Conference>();
		Conference conference = new Conference("Eastern Conference", divisionsList);
		conferenceList.add(conference);
		// add free agents
		league = new League("Dalhousie Hockey League", conferenceList, null, null, null, null);
	}
	
	public League getLeagueInfo() {
		return league;
	}
	
}
