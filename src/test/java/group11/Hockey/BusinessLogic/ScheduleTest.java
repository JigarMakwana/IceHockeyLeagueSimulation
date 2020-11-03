package group11.Hockey.BusinessLogic;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;

public class ScheduleTest {

	@Test
	public void getSeasonScheduleTest() {
		SimulationLeagueModelMock leagueModel = new SimulationLeagueModelMock();
		League league = leagueModel.getLeagueInfo();
		
		Schedule regularSeasonSchedule = new Schedule(league);
		HashMap<String, HashMap<Team, Team>> schedule = null;
		schedule = regularSeasonSchedule.getSeasonSchedule("29/09/2020");
		Assert.assertEquals(1312, schedule.size());
	}


}