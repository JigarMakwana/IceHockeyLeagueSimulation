// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.ISchedule;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.ILeague;

public class ScheduleTest {

	@Test
	public void getSeasonScheduleTest() {
		SimulationLeagueModelMock leagueModel = new SimulationLeagueModelMock();
		ILeague league = leagueModel.getLeagueInfo();
		
		league.setStartDate("29/10/2020");
		ITimeLine timeline=DefaultHockeyFactory.makeTimeLine();
		timeline.setStartDate("29/10/2020");
		league.setTimeLine(timeline);

		ISchedule regularSeasonSchedule = DefaultHockeyFactory.makeSchedule(league);
		HashMap<String, HashMap<ITeam, ITeam>> schedule = regularSeasonSchedule.getSeasonSchedule();
		Assert.assertEquals(1312, schedule.size());
	}

}
