package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.ISimulateSeason;
import group11.Hockey.BusinessLogic.SimulateSeason;
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
		
		SimulationLeagueModelMock leagueModel = new SimulationLeagueModelMock();
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
		
		Player player1 = new Player(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		Player player2 = new Player(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		Player player3 = new Player(10, 10, 10, 10, "Player Three", "goalie", false, false, 20);

		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		
		team1 = new Team("Boston Bruins", "Don Sweeney", coach, playerList);
		team2 = new Team("Buffalo Sabres", "Kevyn Adams", coach, playerList);		
		team3 = new Team("Detroit Red Wings", "Steve Yzerman", coach, playerList);
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
		
		team1 = new Team("Carolina Hurricanes", "Don Waddel", coach, playerList);
		team2 = new Team("Columbus Blue Jackets", "Jarmo Kekalainen", coach, playerList);		
		team3 = new Team("New Jersey Devils", "Tom Fitzgerald", coach, playerList);
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
		
		team1 = new Team("Chicago Blackhawks", "Stan Bowman", coach, playerList);
		team2 = new Team("Colorado Avalanche", "Joe Sakic", coach, playerList);		
		team3 = new Team("Dallas Stars", "Jim Nill", coach, playerList);
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
		
		team1 = new Team("Anaheim Ducks", "Bob Murray", coach, playerList);
		team2 = new Team("Arizona Coyotes", "Bill Armstrong", coach, playerList);		
		team3 = new Team("Calgary Flames", "Brad Treliving", coach, playerList);
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
		Assert.assertEquals("29/09/2021", endDate);
}


}
