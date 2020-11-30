/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.GameSimulation;

import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.IGameSimulation;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import junit.framework.Assert;

public class GameSimulationTest {

	@Test(expected = NullPointerException.class)
	public void startGamePlayTest() {
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IConference conf = league.getConferences().get(0);
		IDivision div = conf.getDivisions().get(0);
		ITeam team = div.getTeams().get(0);
		IGameSimulation gameSimulation = DefaultHockeyFactory.makeGameSimulation(league, team, team);
		ITeam teamObj = gameSimulation.startGamePlay();
		Assert.assertNotNull(teamObj);
	}

}
