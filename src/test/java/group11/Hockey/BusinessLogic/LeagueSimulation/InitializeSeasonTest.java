// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;

public class InitializeSeasonTest {

	@Test
	public void startSeasonsTest() {

		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		ILeague league = leagueMock.getLeagueInfo();

		league.setStartDate("29/10/2020");
		StateMachineState initialize=DefaultHockeyFactory.makeInitializeSeason(league, null,  null, null, null);
		initialize.startState();
		HashMap<String, HashMap<ITeam, ITeam>> schedule = league.getSchedule();
		Assert.assertEquals(1312, schedule.size());
	}
}

