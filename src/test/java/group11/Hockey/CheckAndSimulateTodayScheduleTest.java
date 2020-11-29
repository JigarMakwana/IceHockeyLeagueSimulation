package group11.Hockey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.models.*;
import org.junit.Test;

import group11.Hockey.BusinessLogic.LeagueSimulation.CheckAndSimulateTodaySchedule;

public class CheckAndSimulateTodayScheduleTest {

	@Test
	public void CheckAndSimulateTodayTest() {
		SimulationLeagueModelMock leagueModel = new SimulationLeagueModelMock();
		ILeague league = leagueModel.getLeagueInfo();

		HashMap<String, HashMap<ITeam, ITeam>> regularSchedule=new HashMap<>();
		HashMap<ITeam, ITeam> schedule = new HashMap<>();
		List<IPlayer> playerList = new ArrayList<>();
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

		team1 = new Team("Boston Bruins", null, coach, playerList);
		team2 = new Team("Buffalo Sabres", null, coach, playerList);
		team3 = new Team("Detroit Red Wings", null, coach, playerList);
		team4 = new Team("Florida Panthers", null, coach, playerList);

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

		CheckAndSimulateTodaySchedule simulateSchedule = new CheckAndSimulateTodaySchedule(regularSchedule,league);
		simulateSchedule.CheckAndSimulateToday("01/10/2020");
	}

}
