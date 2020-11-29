//package group11.Hockey;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Random;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
//import group11.Hockey.BusinessLogic.StateMachineState;
//import group11.Hockey.BusinessLogic.LeagueSimulation.IPlayoffSchedule;
//import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleStrategy;
//import group11.Hockey.BusinessLogic.LeagueSimulation.PlayoffSchedule;
//import group11.Hockey.BusinessLogic.models.Coach;
//import group11.Hockey.BusinessLogic.models.Conference;
//import group11.Hockey.BusinessLogic.models.Division;
//import group11.Hockey.BusinessLogic.models.IConference;
//import group11.Hockey.BusinessLogic.models.ILeague;
//import group11.Hockey.BusinessLogic.models.ITeam;
//import group11.Hockey.BusinessLogic.models.ITimeLine;
//import group11.Hockey.BusinessLogic.models.League;
//import group11.Hockey.BusinessLogic.models.Player;
//import group11.Hockey.BusinessLogic.models.Team;
//import group11.Hockey.BusinessLogic.models.TimeLine;
//import group11.Hockey.db.League.ILeagueDb;
//
//public class PlayoffScheduleTest {
//	
//	@Test
//	public void getSchedule() {
//		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
//		ILeague league = leagueMock.getLeagueInfo();
//		
//		List<IConference> cconferenceList = league.getConferences();
//		for (IConference conference : cconferenceList) {
//			List<Division> divisionList = conference.getDivisions();
//			for (Division division : divisionList) {
//				List<ITeam> teamList = division.getTeams();
//				for (ITeam team : teamList) {
//					Random r = new Random();
//					int low = 10;
//					int high = 100;
//					int points = r.nextInt(high-low) + low;
//					team.setPoints(points);				
//				}
//			}
//		}	
//		league.setStartDate("01-10-2020");
//		IScheduleStrategy playoff=DefaultHockeyFactory.makePlayoffSchedule();
//		playoff.getSchedule(league, null);
//		HashMap<String, HashMap<ITeam, ITeam>> playoffSchedule = league.getSchedule();
//		int size=playoffSchedule.size();
//		System.out.println("round1: "+size);
//		Assert.assertEquals(56, size);
//		
//		
//	}
//
//	
//	  @Test public void generatePlayoffScheduleReaminingRoundsTest() {
//	  SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
//	  League league = leagueMock.getLeagueInfo(); Team team1,team2;
//	  
//	  float skill = (float) 2.0; Coach coach = new Coach();
//	  coach.setChecking(skill); coach.setName("Dave"); coach.setSaving(skill);
//	  coach.setShooting(skill); coach.setSkating(skill);
//	  
//	  List<Player> playerList = new ArrayList<Player>(); Player player1 = new
//	  Player(10, 10, 10, 10, "Player One", "forward", true, false, 50); Player
//	  player2 = new Player(10, 10, 10, 10, "Player Two", "defense", false, false,
//	  20); Player player3 = new Player(10, 10, 10, 10, "Player Three", "goalie",
//	  false, false, 20);
//	  
//	  playerList.add(player1); playerList.add(player2); playerList.add(player3);
//	  
//	  team1 = new Team("Boston Bruins", "Don Sweeney", coach, playerList); team2 =
//	  new Team("Arizona Coyotes", "Bill Armstrong", coach, playerList); List<Team>
//	  teamList = new ArrayList<Team>(); teamList.add(team1); teamList.add(team2);
//	  league.setQualifiedTeams(teamList);
//	  
//	  PlayoffSchedule playoff=new PlayoffSchedule(league); HashMap<String,
//	  HashMap<Team, Team>> playoffSchedule = playoff.getSchedule(); int
//	  size=playoffSchedule.size(); Assert.assertEquals(7, size); }
//	 
//}