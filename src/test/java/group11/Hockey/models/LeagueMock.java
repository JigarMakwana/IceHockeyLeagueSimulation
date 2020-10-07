package group11.Hockey.models;

import java.util.ArrayList;
import java.util.List;

public class LeagueMock {
	private League league;

	public LeagueMock() {
		super();
		
		addLeague();
	}
	
	private void addLeague() {
		
		List<Player> playerList = new ArrayList<Player>();
		
		Player player1 = new Player("Player One", "forward", true);
		Player player2 = new Player("Player Two", "defense", false);
		Player player3 = new Player("Player Three", "goalie", false);
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		
		List<Team> teamsList = new ArrayList<Team>();
		Team team1 = new Team("Boston", "Mister Fred", "Mary Smith", playerList);
		Team team2 = new Team("Vancouver Canucks", "John", "Peter", null);
		teamsList.add(team1);
		teamsList.add(team2);
		
		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic", teamsList);
		divisionsList.add(atlanticDivision);
		List<Conference> conferenceList = new ArrayList<Conference>();
		Conference conference = new Conference("Eastern Conference", divisionsList);
		conferenceList.add(conference);
		// add free agents
		league = new League("Dalhousie Hockey League", conferenceList, null);
	}
	
	public League getLeagueInfo() {
		return league;
	}
	
}
