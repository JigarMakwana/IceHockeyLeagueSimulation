// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

import org.junit.Test;

public class CheckAndSimulateTodayScheduleTest {

	@Test
	public void CheckAndSimulateTodayTest() {
		SimulationLeagueModelMock leagueModel = new SimulationLeagueModelMock();
		ILeague league = leagueModel.getLeagueInfo();

		HashMap<String, HashMap<ITeam, ITeam>> regularSchedule=new HashMap<>();
		HashMap<ITeam, ITeam> schedule = new HashMap<>();
		List<IPlayer> playerList = new ArrayList<>();
		ITeam team1,team2,team3,team4;

		ICoach coach = DefaultHockeyFactory.makeCoach("Dave", null);		

		IPlayer player1 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		IPlayer player2 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		IPlayer player3 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player Three", "goalie", false, false, 20);

		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);

		team1 = DefaultHockeyFactory.makeTeam("Boston Bruins", null, coach, playerList);
		team2 = DefaultHockeyFactory.makeTeam("Buffalo Sabres", null, coach, playerList);
		team3 = DefaultHockeyFactory.makeTeam("Detroit Red Wings", null, coach, playerList);
		team4 = DefaultHockeyFactory.makeTeam("Florida Panthers", null, coach, playerList);

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
