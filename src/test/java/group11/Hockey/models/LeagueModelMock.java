package group11.Hockey.models;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.GameResolver;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Trading;
import group11.Hockey.BusinessLogic.models.Training;

public class LeagueModelMock {
	private League league;

	public LeagueModelMock() {
		super();

		addLeague();
	}

	private void addLeague() {

		List<Player> playerList = new ArrayList<Player>();
		
		Aging aging = new Aging(30, 55);
		GameResolver gameResolver = new GameResolver(0);
		Injuries injuries = new Injuries(1, 1, 100);
		Training training = new Training(0);
		Trading trading = new Trading(0, 0, 0, 0);
		
		GameplayConfig gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, trading);

		Player player1 = new Player(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		Player player2 = new Player(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		Player player3 = new Player(10, 10, 10, 10, "Player Three", "goalie", false, false, 20);
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);

		List<Team> teamsList = new ArrayList<Team>();
		Team team1 = new Team("Boston", "Mister Fred", null, playerList);
		
		playerList = new ArrayList<Player>();
		Player player4 = new Player(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		Player player5 = new Player(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		playerList.add(player4);
		playerList.add(player5);
		
		
		
		Team team2 = new Team("Vancouver Canucks", "John", null, playerList);
		teamsList.add(team1);
		teamsList.add(team2);

		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic", teamsList);
		divisionsList.add(atlanticDivision);
		List<Conference> conferenceList = new ArrayList<Conference>();
		List<Player> freeAgentsList = new ArrayList<Player>();
		Conference conference = new Conference("Eastern Conference", divisionsList);
		conferenceList.add(conference);
		// add free agents
		league = new League("Dalhousie Hockey League", conferenceList, freeAgentsList, gameplayConfig, null, null);
		playerList = new ArrayList<Player>();
		playerList.add(player1);
		league.setRetiredPlayers(playerList);
	}

	public League getLeagueInfo() {
		return league;
	}

}
