/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.Enums.Positions;
import group11.Hockey.BusinessLogic.Trading.TradingMockFactory;
import group11.Hockey.BusinessLogic.Trading.TradingModelMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.IInjuries;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;

public class InjurySystemTest {
	private TradingModelMock leagueModel;
	private ILeague leagueObj;
	private IInjurySystem injurySystem;

	@Before
	public void setUp() throws Exception {
		leagueModel = TradingMockFactory.makeTradingMock(1.0f, 1.0f);
		leagueObj = leagueModel.getLeagueInfo();
		injurySystem = DefaultHockeyFactory.makeInjurySystem(leagueObj);
	}

	@Test
	public void setInjuryToPlayersTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		ILeague league = leagueModel.getLeagueInfo();
		List<IConference> conferences = league.getConferences();
		List<IDivision> divisions = conferences.get(0).getDivisions();
		List<ITeam> teams = divisions.get(0).getTeams();
		List<IPlayer> players = teams.get(0).getPlayers();

		IInjurySystem injurySystem = DefaultHockeyFactory.makeInjurySystem(league);
		injurySystem.setInjuryToPlayers(teams.get(0));

		Assert.assertTrue(players.get(0).isInjured());
	}

	@Test
	public void determainIsPlayerInjuredTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		ILeague league = leagueModel.getLeagueInfo();
		IInjurySystem injurySystem = DefaultHockeyFactory.makeInjurySystem(league);
		boolean flag = injurySystem.determainIsPlayerInjured();
		Assert.assertTrue(flag);
	}

	@Test
	public void determainNumberOfDaysOfInjuryTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		ILeague league = leagueModel.getLeagueInfo();
		IInjurySystem injurySystem = DefaultHockeyFactory.makeInjurySystem(league);
		int injuredDays = injurySystem.determainNumberOfDaysOfInjury();
		IInjuries injuries = league.getGamePlayConfig().getInjuries();
		boolean flag = injuries.getInjuryDaysLow() < injuredDays || injuredDays >= injuries.getInjuryDaysHigh();
		Assert.assertTrue(flag);
	}

	@Test
	public void settleRecoveredPlayerTest() {
		injurySystem.settleRecoveredPlayer(leagueModel.getTeam2().getRoster(),leagueModel.getTeam2().getPlayers().get(27));
		Assert.assertEquals(leagueModel.getTeam2().getRoster().getActiveRoster().size(), 20);
		Assert.assertEquals(leagueModel.getTeam2().getRoster().getInActiveRoster().size(), 10);
	}

	@Test
	public void settleInjuredPlayerTest() {
		injurySystem.settleInjuredPlayer(leagueModel.getTeam2().getRoster(),leagueModel.getTeam2().getPlayers().get(0));
		Assert.assertEquals(leagueModel.getTeam2().getRoster().getActiveRoster().size(), 20);
		Assert.assertEquals(leagueModel.getTeam2().getRoster().getInActiveRoster().size(), 10);
	}

	@Test
	public void isInjuredSwappingPossibleTest() {
		Assert.assertTrue(injurySystem.isInjuredSwappingPossible(leagueModel.getTeam2().getRoster(),
				leagueModel.getTeam2().getPlayers().get(0)));
	}

	@Test
	public void findInjuredPlayerPositionTest() {
		Assert.assertEquals(injurySystem.findInjuredPlayerPosition(leagueModel.getTeam2().getPlayers().get(0)), Positions.FORWARD);
	}

	@Test
	public void isUnInjuredPlayerAvailableTest() {
		Assert.assertTrue(injurySystem.isUnInjuredPlayerAvailable(leagueModel.getTeam2().getRoster(), Positions.FORWARD));
		Assert.assertFalse(injurySystem.isUnInjuredPlayerAvailable(leagueModel.getTeam1().getRoster(), Positions.FORWARD));
	}
}
