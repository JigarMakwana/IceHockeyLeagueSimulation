package group11.Hockey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.PlayoffSchedule;
import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class PlayoffScheduleTest {
	
	@Test
	public void generatePlayoffScheduleRound1Test() {
		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		League league = leagueMock.getLeagueInfo();
		
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
		
		PlayoffSchedule playoff=new PlayoffSchedule(league);
		HashMap<String, HashMap<Team, Team>> playoffSchedule = playoff.generatePlayoffScheduleRound1("14/04/2021");
		int size=playoffSchedule.size();
		System.out.println("round1: "+size);
		Assert.assertEquals(56, size);
		
		
	}

	@Test
	public void generatePlayoffScheduleReaminingRoundsTest() {
		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		League league = leagueMock.getLeagueInfo();		
		Team team1,team2;	
		
		float skill = (float) 2.0;
		Coach coach = new Coach();
		coach.setChecking(skill);
		coach.setName("Dave");
		coach.setSaving(skill);
		coach.setShooting(skill);
		coach.setSkating(skill);
		
		List<Player> playerList = new ArrayList<Player>();
		Player player1 = new Player(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		Player player2 = new Player(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		Player player3 = new Player(10, 10, 10, 10, "Player Three", "goalie", false, false, 20);

		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		
		team1 = new Team("Boston Bruins", "Don Sweeney", coach, playerList);
		team2 = new Team("Arizona Coyotes", "Bill Armstrong", coach, playerList);	
		List<Team> teamList = new ArrayList<Team>();
		teamList.add(team1);
		teamList.add(team2);
		league.setQualifiedTeams(teamList);
		
		PlayoffSchedule playoff=new PlayoffSchedule(league);
		HashMap<String, HashMap<Team, Team>> playoffSchedule = playoff.generatePlayoffScheduleRemainingRounds("03/05/2021");
		int size=playoffSchedule.size();
		Assert.assertEquals(7, size);
}
}