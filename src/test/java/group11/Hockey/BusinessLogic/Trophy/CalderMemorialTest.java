// Author: Harry B00856244
package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.LeagueSimulation.SimulationLeagueModelMock;
import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;

public class CalderMemorialTest {

	@Test
	public void AwardTrophyTest() {
		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		ILeague league = leagueMock.getLeagueInfo();
		ITrophyObserver calderPlayers=DefaultHockeyFactory.makeCalderMemorial(league);
		calderPlayers.AwardTrophy();
		List<IPlayer> calder=league.getCalderPlayers();
		Assert.assertEquals(calder.size(), 1);
	}
}
