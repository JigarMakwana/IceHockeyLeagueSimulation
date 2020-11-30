// Author: Harry B00856244
package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.LeagueSimulation.SimulationLeagueModelMock;
import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.ILeague;

public class JackAdamsTest {

	@Test
	public void AwardTrophyTest() {
		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		ILeague league = leagueMock.getLeagueInfo();
		ITrophyObserver jackAdamsPlayers=DefaultHockeyFactory.makeJackAdams(league);
		jackAdamsPlayers.AwardTrophy();
		List<ICoach> jackAdams=league.getJackAdamsCoaches();
		Assert.assertEquals(jackAdams.size(), 1);
	}
}
