package group11.Hockey.BusinessLogic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.League.ILeagueDb;

public class SimulateSeasonTest {

	@Test
	public void StartSimulatingSeasonTest() {
		
	/*	SimulationLeagueModelMock leagueModel = new SimulationLeagueModelMock();
		League league = leagueModel.getLeagueInfo();
		
		HashMap<String, HashMap<Team, Team>> regularSchedule=new HashMap<>();
		HashMap<Team, Team> schedule = new HashMap<>();
		List<Player> playerList = new ArrayList<Player>();
		Team team1,team2,team3,team4;
		
		float skill = (float) 2.0;
		Coach coach = new Coach();
		coach.setChecking(skill);
		coach.setName("Dave");
		coach.setSaving(skill);
		coach.setShooting(skill);
		coach.setSkating(skill);
		Player player1,player2,player3,player4;
		
		player1 = new Player(15, 18, 12, 1, "Tom", "forward", true, false, 25);
        player2 = new Player(10, 10, 10, 1, "Dick", "defense", false, false, 28);
        player3 = new Player(10, 4, 9, 18, "Harry", "goalie", false, false, 30);
        player4 = new Player(10, 10, 10, 1, "Jerry", "defense", false, false, 21);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team1 = new Team("Boston Bruins", "Don Sweeney", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(10, 20, 13, 1, "Ramesh", "forward", true, false, 30);
        player2= new Player(12, 10, 14, 1, "Suresh", "defense", false, false, 31);
        player3 = new Player(10, 8, 9, 12, "Mahesh", "goalie", false, false, 32);
        player4 = new Player(10, 15, 10, 1, "Lokesh", "defense", false, false, 33);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team2 = new Team("Buffalo Sabres", "Kevyn Adams", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(12, 11, 11, 1, "Jigar", "forward", true, false, 23);
        player2 = new Player(7, 8, 9, 1, "Raj", "defense", false, false, 24);
        player3= new Player(10, 5, 5, 18, "Jatin", "goalie", false, false, 25);
        player4 = new Player(10, 12, 10, 1, "Alex", "defense", false, false, 26);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team3 = new Team("Detroit Red Wings", "Steve Yzerman", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(15, 15, 15, 1, "Alfa", "forward", true, false, 23);
        player2 = new Player(12, 13, 14, 1, "Beta", "defense", false, false, 24);
        player3= new Player(10, 15, 9, 18, "Gama", "goalie", false, false, 25);
        player4 = new Player(10, 12, 10, 1, "Theta", "defense", false, false, 26);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team4 = new Team("Florida Panthers", "Bill Zito", coach, playerList);
		
		schedule = new HashMap<>();
		schedule.put(team1, team2);
		regularSchedule.put("01/10/2020T00:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team3);
		regularSchedule.put("01/10/2020T02:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team4);
		regularSchedule.put("01/10/2020T04:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team3);
		regularSchedule.put("01/10/2020T06:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team4);
		regularSchedule.put("01/10/2020T08:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team3, team4);
		regularSchedule.put("01/10/2020T10:00:00",schedule);
		
		playerList = new ArrayList<Player>();
		player1 = new Player(15, 18, 12, 1, "Tom2", "forward", true, false, 25);
        player2 = new Player(10, 10, 10, 1, "Dick2", "defense", false, false, 28);
        player3 = new Player(10, 4, 9, 18, "Harry2", "goalie", false, false, 30);
        player4 = new Player(10, 10, 10, 1, "Jerry2", "defense", false, false, 21);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team1 = new Team("Carolina Hurricanes", "Don Waddel", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(10, 20, 13, 1, "Ramesh2", "forward", true, false, 30);
        player2 = new Player(12, 10, 14, 1, "Suresh2", "defense", false, false, 31);
        player3 = new Player(10, 8, 9, 12, "Mahesh2", "goalie", false, false, 32);
        player4 = new Player(10, 15, 10, 1, "Lokesh2", "defense", false, false, 33);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team2 = new Team("Columbus Blue Jackets", "Jarmo Kekalainen", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(12, 11, 11, 1, "Jigar2", "forward", true, false, 23);
        player2 = new Player(7, 8, 9, 1, "Raj2", "defense", false, false, 24);
        player3 = new Player(10, 5, 5, 18, "Jatin2", "goalie", false, false, 25);
        player4 = new Player(10, 12, 10, 1, "Alex2", "defense", false, false, 26);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team3 = new Team("New Jersey Devils", "Tom Fitzgerald", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(15, 15, 15, 1, "Alfa2", "forward", true, false, 23);
        player2 = new Player(12, 13, 14, 1, "Beta2", "defense", false, false, 24);
        player3 = new Player(10, 15, 9, 18, "Gama2", "goalie", false, false, 25);
        player4 = new Player(10, 12, 10, 1, "Theta2", "defense", false, false, 26);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team4 = new Team("New York Islanders", "Lou Lamoriello", coach, playerList);
		
		schedule = new HashMap<>();
		schedule.put(team1, team2);
		regularSchedule.put("03/10/2020T00:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team3);
		regularSchedule.put("03/10/2020T02:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team4);
		regularSchedule.put("03/10/2020T04:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team3);
		regularSchedule.put("03/10/2020T06:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team4);
		regularSchedule.put("03/10/2020T08:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team3, team4);
		regularSchedule.put("03/10/2020T10:00:00",schedule);
		
		playerList = new ArrayList<Player>();
		player1 = new Player(15, 18, 12, 1, "Tom3", "forward", true, false, 25);
        player2 = new Player(10, 10, 10, 1, "Dick3", "defense", false, false, 28);
        player3 = new Player(10, 4, 9, 18, "Harry3", "goalie", false, false, 30);
        player4 = new Player(10, 10, 10, 1, "Jerry3", "defense", false, false, 21);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team1 = new Team("Chicago Blackhawks", "Stan Bowman", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(10, 20, 13, 1, "Ramesh3", "forward", true, false, 30);
        player2 = new Player(12, 10, 14, 1, "Suresh3", "defense", false, false, 31);
        player3 = new Player(10, 8, 9, 12, "Mahesh3", "goalie", false, false, 32);
        player4 = new Player(10, 15, 10, 1, "Lokesh3", "defense", false, false, 33);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team2 = new Team("Colorado Avalanche", "Joe Sakic", coach, playerList);	
		
		playerList = new ArrayList<Player>();
        player1 = new Player(12, 11, 11, 1, "Jigar3", "forward", true, false, 23);
        player2 = new Player(7, 8, 9, 1, "Raj3", "defense", false, false, 24);
        player3 = new Player(10, 5, 5, 18, "Jatin3", "goalie", false, false, 25);
        player4 = new Player(10, 12, 10, 1, "Alex3", "defense", false, false, 26);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team3 = new Team("Dallas Stars", "Jim Nill", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(15, 15, 15, 1, "Alfa3", "forward", true, false, 23);
        player2 = new Player(12, 13, 14, 1, "Beta3", "defense", false, false, 24);
        player3 = new Player(10, 15, 9, 18, "Gama3", "goalie", false, false, 25);
        player4 = new Player(10, 12, 10, 1, "Theta3", "defense", false, false, 26);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team4 = new Team("Minnesota Wild", "Bill Guerrin", coach, playerList);
		
		schedule = new HashMap<>();
		schedule.put(team1, team2);
		regularSchedule.put("06/10/2020T00:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team3);
		regularSchedule.put("06/10/2020T02:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team4);
		regularSchedule.put("06/10/2020T04:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team3);
		regularSchedule.put("06/10/2020T06:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team4);
		regularSchedule.put("06/10/2020T08:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team3, team4);
		regularSchedule.put("06/10/2020T10:00:00",schedule);
		
		playerList = new ArrayList<Player>();
		player1 = new Player(15, 18, 12, 1, "Tom4", "forward", true, false, 25);
        player2 = new Player(10, 10, 10, 1, "Dick4", "defense", false, false, 28);
        player3 = new Player(10, 4, 9, 18, "Harry4", "goalie", false, false, 30);
        player4 = new Player(10, 10, 10, 1, "Jerry4", "defense", false, false, 21);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);	
		team1 = new Team("Anaheim Ducks", "Bob Murray", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(10, 20, 13, 1, "Ramesh4", "forward", true, false, 30);
        player2 = new Player(12, 10, 14, 1, "Suresh4", "defense", false, false, 31);
        player3 = new Player(10, 8, 9, 12, "Mahesh4", "goalie", false, false, 32);
        player4 = new Player(10, 15, 10, 1, "Lokesh4", "defense", false, false, 33);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);	
		team2 = new Team("Arizona Coyotes", "Bill Armstrong", coach, playerList);	
		
		playerList = new ArrayList<Player>();
        player1 = new Player(12, 11, 11, 1, "Jigar4", "forward", true, false, 23);
        player2 = new Player(7, 8, 9, 1, "Raj4", "defense", false, false, 24);
        player3 = new Player(10, 5, 5, 18, "Jatin4", "goalie", false, false, 25);
        player4 = new Player(10, 12, 10, 1, "Alex4", "defense", false, false, 26);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);		
		team3 = new Team("Calgary Flames", "Brad Treliving", coach, playerList);
		
		playerList = new ArrayList<Player>();
        player1 = new Player(15, 15, 15, 1, "Alfa4", "forward", true, false, 23);
        player2 = new Player(12, 13, 14, 1, "Beta4", "defense", false, false, 24);
        player3 = new Player(10, 15, 9, 18, "Gama4", "goalie", false, false, 25);
        player4 = new Player(10, 12, 10, 1, "Theta4", "defense", false, false, 26);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);	
		team4 = new Team("Edmonton Oilers", "Ken Holland", coach, playerList);
		
		schedule = new HashMap<>();
		schedule.put(team1, team2);
		regularSchedule.put("09/10/2020T00:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team3);
		regularSchedule.put("09/10/2020T02:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team4);
		regularSchedule.put("09/10/2020T04:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team3);
		regularSchedule.put("09/10/2020T06:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team4);
		regularSchedule.put("09/10/2020T08:00:00",schedule);
		schedule = new HashMap<>();
		schedule.put(team3, team4);
		regularSchedule.put("09/10/2020T10:00:00",schedule);
		
		ILeagueDb leagueDb = mock(ILeagueDb.class);
		IGameplayConfigDb gameplayConfigDb = mock(IGameplayConfigDb.class);
		IPlayerDb playerDb = mock(IPlayerDb.class);
		ICoachDb coachDb = mock(ICoachDb.class);
		IManagerDb managerDb = mock(IManagerDb.class);

		when(leagueDb.insertLeagueInDb(null, "conf", "div", null, null, null)).thenReturn(true);
		when(gameplayConfigDb.insertGameplayConfig(null,null,null,null,null, "league")).thenReturn(true);
		when(playerDb.insertLeagueFreeAgents("league", null)).thenReturn(true);
		when(coachDb.insertCoaches("league", "c1", 0, 0, 0, 0)).thenReturn(true);
		when(managerDb.insertManager("league", "M1")).thenReturn(true);
		
		List<Conference> cconferenceList = league.getConferences();
		for (Conference conference : cconferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					Random r = new Random();
					int low = 10;
					int high = 100;
					int points = r.nextInt(high-low) + low;
					team.setPoints(points);				
				}
			}
		}	
		
		List<Team> teamList = new ArrayList<Team>();
		team1 = new Team("Boston Bruins", "Don Sweeney", coach, playerList);
		team2 = new Team("Arizona Coyotes", "Bill Armstrong", coach, playerList);	
		teamList.add(team1);
		teamList.add(team2);
		league.setQualifiedTeams(teamList);
		
		ISimulateSeason season=new SimulateSeason(regularSchedule,league, leagueDb,  gameplayConfigDb, playerDb, coachDb, managerDb);
		String endDate=season.StartSimulatingSeason("30/09/2020");
		Assert.assertEquals("29/09/2021", endDate);*/
}


}
