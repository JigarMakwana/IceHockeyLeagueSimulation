package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class CheckAndSimulateTodayScheduleTest {

	@Test
	public void CheckAndSimulateTodayTest() {
		SimulationLeagueModelMock leagueModel = new SimulationLeagueModelMock();
		League league = leagueModel.getLeagueInfo();

		HashMap<String, HashMap<Team, Team>> regularSchedule = new HashMap<>();
		HashMap<Team, Team> schedule = new HashMap<>();
		List<Player> playerList = new ArrayList<Player>();
		Team team1, team2, team3, team4;

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
		regularSchedule.put("01/10/2020T00:00:00", schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team3);
		regularSchedule.put("01/10/2020T02:00:00", schedule);
		schedule = new HashMap<>();
		schedule.put(team1, team4);
		regularSchedule.put("01/10/2020T04:00:00", schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team3);
		regularSchedule.put("01/10/2020T06:00:00", schedule);
		schedule = new HashMap<>();
		schedule.put(team2, team4);
		regularSchedule.put("01/10/2020T08:00:00", schedule);
		schedule = new HashMap<>();
		schedule.put(team3, team4);
		regularSchedule.put("01/10/2020T10:00:00", schedule);

		CheckAndSimulateTodaySchedule simulateSchedule = new CheckAndSimulateTodaySchedule(regularSchedule, league);
		simulateSchedule.CheckAndSimulateToday("01/10/2020");

		Assert.assertEquals(regularSchedule.size(), 6);
	}

}