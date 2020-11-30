/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;

public class ForwardPlayerActiveTest {

	@Test
	public void calculateAveragePlayersStrengthTest() {
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IConference conf = league.getConferences().get(0);
		IDivision div = conf.getDivisions().get(0);
		ITeam team = div.getTeams().get(0);
		List<IPlayer> playersList = team.getPlayers();

		IGameStrategy gs = DefaultHockeyFactory.makeForwardPlayerActive();
		int value = gs.calculateAveragePlayersStrength(playersList, team);
		Assert.assertEquals(10, value);
	}

	@Test
	public void playGameTest() {
		IGameStrategy gs = DefaultHockeyFactory.makeForwardPlayerActive();
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IConference conf = league.getConferences().get(0);
		IDivision div = conf.getDivisions().get(0);
		ITeam team = div.getTeams().get(0);
		List<IPlayer> playersList = team.getPlayers();
		playersList.addAll(playersList);
		gs.playGame(playersList, playersList, team, team, 2);
		int saves = team.getSavesInSeason() + 1;
		Assert.assertEquals(1, saves);
	}

}
