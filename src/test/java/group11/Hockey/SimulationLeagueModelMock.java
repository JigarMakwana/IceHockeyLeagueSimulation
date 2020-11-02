package group11.Hockey;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.GameResolver;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.GeneralManager;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Trading;
import group11.Hockey.BusinessLogic.models.Training;

public class SimulationLeagueModelMock {

		private League league; 
		private List<Team> qualifiedTeams = new ArrayList<Team>();
		private String startDate;
		private List<Conference> conferences = new ArrayList<Conference>();

		public SimulationLeagueModelMock() {
			super();

			addLeague();
		}

		private void addLeague() {
			startDate="29/09/2020";
			Aging aging = new Aging(30, 55);
			GameResolver gameResolver = new GameResolver(0);
			Injuries injuries = new Injuries(1, 1, 100);
			Training training = new Training(0);
			Trading trading = new Trading(0, 0, 0, 0);

			GameplayConfig gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, trading);

			float skill = (float) 2.0;
			Coach coach = new Coach();
			coach.setChecking(skill);
			coach.setName("Dave");
			coach.setSaving(skill);
			coach.setShooting(skill);
			coach.setSkating(skill);
				
			List<Team> teamsList = new ArrayList<Team>();
			List<Player> playerList = new ArrayList<Player>();
			List<Division> divisionsList = new ArrayList<Division>();
			List<Conference> conferenceList = new ArrayList<Conference>();
			List<Team> qualifiedTeams = new ArrayList<Team>();		
			Team team1,team2,team3,team4,team5,team6,team7,team8;
			Player player1,player2,player3,player4;
			
			//Atlantic Division, Eastern Conference
			player1 = new Player(15, 18, 12, 1, "Tom1", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick1", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry1", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry1", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);			
			team1 = new Team("Boston Bruins", "Don Sweeney", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom2", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick2", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry2", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry2", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);	
			team2 = new Team("Buffalo Sabres", "Kevyn Adams", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom3", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick3", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry3", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry3", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team3 = new Team("Detroit Red Wings", "Steve Yzerman", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom4", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick4", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry4", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry4", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team4 = new Team("Florida Panthers", "Bill Zito", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom5", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick5", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry5", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry5", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team5 = new Team("Montreal Canadiens", "Marc Bergevin", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom6", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick6", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry6", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry6", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team6 = new Team("Ottawa Senators", "Pierre Dorion", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom7", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick7", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry7", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry7", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team7 = new Team("Tampa Bay Lightning", "Julien BriseBois", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom8", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick8", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry8", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry8", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team8 = new Team("Toronto Maple Leafs", "Kyle Dubas", coach, playerList);
			
			teamsList.add(team1);
			teamsList.add(team2);
			teamsList.add(team3);
			teamsList.add(team4);
			teamsList.add(team5);
			teamsList.add(team6);
			teamsList.add(team7);
			teamsList.add(team8);
			
			Division division = new Division("Atlantic", teamsList);
			divisionsList.add(division);
			
			//Metropolitan Division, Eastern Conference
			teamsList = new ArrayList<Team>();
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom9", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick9", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry9", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry9", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team1 = new Team("Carolina Hurricanes", "Don Waddel", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom10", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick10", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry10", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry10", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team2 = new Team("Columbus Blue Jackets", "Jarmo Kekalainen", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom11", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick11", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry11", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry11", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team3 = new Team("New Jersey Devils", "Tom Fitzgerald", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom12", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick12", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry12", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry12", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team4 = new Team("New York Islanders", "Lou Lamoriello", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom13", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick13", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry13", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry13", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team5 = new Team("New York Rangers", "Jeff Gorton", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom14", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick14", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry14", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry14", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team6 = new Team("Philidelphia Flyers", "Check Fletcher", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom15", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick15", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry15", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry15", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team7 = new Team("Pittsburgh Penguins", "Jim Rutherford", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom16", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick16", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry16", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry16", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team8 = new Team("Washington Capitals", "Brian MacLellan", coach, playerList);
			
			teamsList.add(team1);
			teamsList.add(team2);
			teamsList.add(team3);
			teamsList.add(team4);
			teamsList.add(team5);
			teamsList.add(team6);
			teamsList.add(team7);
			teamsList.add(team8);
			
			qualifiedTeams.add(team1);
			Division division1 = new Division("Metropolitan", teamsList);
			divisionsList.add(division1);
			
			Conference conference = new Conference("Eastern Conference", divisionsList);
			conferenceList.add(conference);
			
			//Central Division, Western Conference
			divisionsList = new ArrayList<Division>();
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom17", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick17", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry17", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry17", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team1 = new Team("Chicago Blackhawks", "Stan Bowman", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom18", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick18", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry18", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry18", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team2 = new Team("Colorado Avalanche", "Joe Sakic", coach, playerList);	
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom19", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick19", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry19", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry19", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team3 = new Team("Dallas Stars", "Jim Nill", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom20", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick20", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry20", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry20", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team4 = new Team("Minnesota Wild", "Bill Guerrin", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom21", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick21", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry21", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry21", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team5 = new Team("Nashville Predators", "David Poile", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom22", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick22", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry22", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry22", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team6 = new Team("St. Louis Blues", "Doug Armstrong", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom23", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick23", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry23", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry23", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team7 = new Team("Winnipeg Jets", "Kevin Cheveldayoff", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom24", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick24", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry24", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry24", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team8 = new Team("Minnesota Blues", "Kelly McCrimmons", coach, playerList);
			
			teamsList = new ArrayList<Team>();
			teamsList.add(team1);
			teamsList.add(team2);
			teamsList.add(team3);
			teamsList.add(team4);
			teamsList.add(team5);
			teamsList.add(team6);
			teamsList.add(team7);
			teamsList.add(team8);
			
			Division division2 = new Division("Central", teamsList);
			divisionsList.add(division2);
			
			//Pacific Division, Western Conference
			teamsList = new ArrayList<Team>();
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom25", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick25", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry25", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry25", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team1 = new Team("Anaheim Ducks", "Bob Murray", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom26", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick26", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry26", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry26", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team2 = new Team("Arizona Coyotes", "Bill Armstrong", coach, playerList);	
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom27", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick27", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry27", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry27", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team3 = new Team("Calgary Flames", "Brad Treliving", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom28", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick28", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry28", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry28", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team4 = new Team("Edmonton Oilers", "Ken Holland", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom29", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick29", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry29", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry29", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team5 = new Team("Los Angeles Kings", "Rob Blake", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom30", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick30", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry30", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry30", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team6 = new Team("San Jose Sharks", "Doug Wilson", coach, playerList);
			
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom31", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick31", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry31", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry31", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team7 = new Team("Vancouver Canucks", "Jim Benning", coach, playerList);
			playerList = new ArrayList<Player>();
			player1 = new Player(15, 18, 12, 1, "Tom32", "forward", true, false, 25);
	        player2 = new Player(10, 10, 10, 1, "Dick32", "defense", false, false, 28);
	        player3 = new Player(10, 4, 9, 18, "Harry32", "goalie", false, false, 30);
	        player4 = new Player(10, 10, 10, 1, "Jerry32", "defense", false, false, 21);
			playerList.add(player1);
			playerList.add(player2);
			playerList.add(player3);
			playerList.add(player4);
			team8 = new Team("Vegas Golden Knights", "BKelly McCrimmon", coach, playerList);
			
			teamsList.add(team1);
			teamsList.add(team2);
			teamsList.add(team3);
			teamsList.add(team4);
			teamsList.add(team5);
			teamsList.add(team6);
			teamsList.add(team7);
			teamsList.add(team8);
			
			qualifiedTeams.add(team1);
			Division division3 = new Division("Pacific", teamsList);
			divisionsList.add(division3);
			
			Conference conference2 = new Conference("Western Conference", divisionsList);
			conferenceList.add(conference2);
			
			
			List<Player> freeAgentsList = new ArrayList<Player>();
			league = new League("Dalhousie Hockey League", conferenceList, freeAgentsList, gameplayConfig, null, null);
			playerList = new ArrayList<Player>();
			List<Coach> coachList = new ArrayList<Coach>();
			coachList.add(new Coach((float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, "Coach 1"));
			league.setCoaches(coachList);
			List<GeneralManager> generalManagerList = new ArrayList<GeneralManager>();
			GeneralManager generalManager = new GeneralManager("General Manager 1");
			generalManagerList.add(generalManager);
			league.setGeneralManagers(generalManagerList);
			populateFreeAgents(league);
			playerList.add(player1);
			league.setRetiredPlayers(playerList);
		}

		public void populateFreeAgents(League league) {
			List<Player> freeAgents = new ArrayList<Player>();
			freeAgents.add(new Player(10, 10, 10, 10, "Player 1", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 2", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 3", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 4", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 5", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 6", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 7", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 8", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 9", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 10", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 11", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 12", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 13", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 14", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 15", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 16", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 17", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 18", "forward", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 19", "goalie", true, false, 50));
			freeAgents.add(new Player(10, 10, 10, 10, "Player 20", "goalie", true, false, 50));
			league.setFreeAgents(freeAgents);
		}

		public League getLeagueInfo() {
			return league;
		}
		
		public List<Team> getQualifiedTeams() {
			return qualifiedTeams;
		}

		public void setQualifiedTeams(List<Team> qualifiedTeams) {
			this.qualifiedTeams = qualifiedTeams;
		}
		
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}


}
