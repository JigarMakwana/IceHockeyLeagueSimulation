package group11.Hockey.BusinessLogic;

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
			Player player1 = null,player2,player3,player4;
				
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom1"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick1"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry1"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
	        player3 = new Player(10, 4, 9, 18, "Harry11", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry12", "goalie", false, false, 30);
			playerList.add(player3);
			team1 = new Team("Boston Bruins", "Don Sweeney", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom2"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick2"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry2"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry21", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry22", "goalie", false, false, 30);
			playerList.add(player3);
			team2 = new Team("Buffalo Sabres", "Kevyn Adams", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom3"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick3"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry3"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry31", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry32", "goalie", false, false, 30);
			playerList.add(player3);
			team3 = new Team("Detroit Red Wings", "Steve Yzerman", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom4"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick4"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry4"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry41", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry42", "goalie", false, false, 30);
			playerList.add(player3);
			team4 = new Team("Florida Panthers", "Bill Zito", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom5"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick5"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry5"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry51", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry52", "goalie", false, false, 30);
			playerList.add(player3);
			team5 = new Team("Montreal Canadiens", "Marc Bergevin", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom6"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick6"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry6"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry61", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry62", "goalie", false, false, 30);
			playerList.add(player3);
			team6 = new Team("Ottawa Senators", "Pierre Dorion", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom7"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick7"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry7"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry71", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry72", "goalie", false, false, 30);
			playerList.add(player3);
			team7 = new Team("Tampa Bay Lightning", "Julien BriseBois", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom8"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick8"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry8"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry81", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry82", "goalie", false, false, 30);
			playerList.add(player3);
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
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom9"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick9"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry9"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry91", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry92", "goalie", false, false, 30);
			playerList.add(player3);
			team1 = new Team("Carolina Hurricanes", "Don Waddel", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom10"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick10"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry10"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry101", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry102", "goalie", false, false, 30);
			playerList.add(player3);
			team2 = new Team("Columbus Blue Jackets", "Jarmo Kekalainen", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom11"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick11"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry11"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry111", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry112", "goalie", false, false, 30);
			playerList.add(player3);
			team3 = new Team("New Jersey Devils", "Tom Fitzgerald", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom12"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick12"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry12"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry121", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry122", "goalie", false, false, 30);
			playerList.add(player3);
			team4 = new Team("New York Islanders", "Lou Lamoriello", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom13"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick13"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry13"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry131", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry132", "goalie", false, false, 30);
			playerList.add(player3);
			team5 = new Team("New York Rangers", "Jeff Gorton", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom14"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick14"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry14"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry141", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry142", "goalie", false, false, 30);
			playerList.add(player3);
			team6 = new Team("Philidelphia Flyers", "Check Fletcher", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom15"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick15"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry15"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry151", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry152", "goalie", false, false, 30);
			playerList.add(player3);
			team7 = new Team("Pittsburgh Penguins", "Jim Rutherford", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom16"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick16"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry16"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry161", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry162", "goalie", false, false, 30);
			playerList.add(player3);
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
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom17"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick17"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry17"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry171", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry172", "goalie", false, false, 30);
			playerList.add(player3);
			team1 = new Team("Chicago Blackhawks", "Stan Bowman", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom18"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick18"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry18"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry181", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry182", "goalie", false, false, 30);
			playerList.add(player3);
			team2 = new Team("Colorado Avalanche", "Joe Sakic", coach, playerList);	
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom19"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick19"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry19"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry191", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry192", "goalie", false, false, 30);
			playerList.add(player3);
			team3 = new Team("Dallas Stars", "Jim Nill", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom20"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick20"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry20"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry201", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry202", "goalie", false, false, 30);
			playerList.add(player3);
			team4 = new Team("Minnesota Wild", "Bill Guerrin", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom21"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick21"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry21"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry211", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry212", "goalie", false, false, 30);
			playerList.add(player3);
			team5 = new Team("Nashville Predators", "David Poile", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom22"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick22"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry22"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry221", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry222", "goalie", false, false, 30);
			playerList.add(player3);
			team6 = new Team("St. Louis Blues", "Doug Armstrong", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom23"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick23"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry23"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry231", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry232", "goalie", false, false, 30);
			playerList.add(player3);
			team7 = new Team("Winnipeg Jets", "Kevin Cheveldayoff", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom24"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick24"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry24"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry241", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry242", "goalie", false, false, 30);
			playerList.add(player3);
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
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom25"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick25"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry25"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry251", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry252", "goalie", false, false, 30);
			playerList.add(player3);
			team1 = new Team("Anaheim Ducks", "Bob Murray", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom26"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick26"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry26"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry271", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry272", "goalie", false, false, 30);
			playerList.add(player3);
			team2 = new Team("Arizona Coyotes", "Bill Armstrong", coach, playerList);	
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom27"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick27"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry27"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry281", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry282", "goalie", false, false, 30);
			playerList.add(player3);
			team3 = new Team("Calgary Flames", "Brad Treliving", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom28"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick28"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry28"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry281", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry282", "goalie", false, false, 30);
			playerList.add(player3);
			team4 = new Team("Edmonton Oilers", "Ken Holland", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom29"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick29"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry29"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry291", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry292", "goalie", false, false, 30);
			playerList.add(player3);
			team5 = new Team("Los Angeles Kings", "Rob Blake", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom30"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick30"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry30"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry301", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry302", "goalie", false, false, 30);
			playerList.add(player3);
			team6 = new Team("San Jose Sharks", "Doug Wilson", coach, playerList);
			
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom31"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick31"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry31"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry311", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry312", "goalie", false, false, 30);
			playerList.add(player3);
			team7 = new Team("Vancouver Canucks", "Jim Benning", coach, playerList);
			playerList = new ArrayList<Player>();
			for(int i=0;i<=5;i++) {
				player1 = new Player(15, 18, 12, 1, "Tom32"+i, "forward", true, false, 25);
		        player2 = new Player(10, 10, 10, 1, "Dick32"+i, "defense", false, false, 28);
		        player4 = new Player(10, 10, 10, 1, "Jerry32"+i, "defense", false, false, 21);
				playerList.add(player1);
				playerList.add(player2);
				playerList.add(player4);	
			}
			player3 = new Player(10, 4, 9, 18, "Harry321", "goalie", false, false, 30);
			playerList.add(player3);
	        player3 = new Player(10, 4, 9, 18, "Harry322", "goalie", false, false, 30);
			playerList.add(player3);
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
