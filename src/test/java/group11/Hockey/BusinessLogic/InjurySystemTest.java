/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IInjuries;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;

public class InjurySystemTest {

	@Test
	public void setInjuryToPlayersTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		ILeague league = leagueModel.getLeagueInfo();
		List<IConference> conferences = league.getConferences();
		List<Division> divisions = conferences.get(0).getDivisions();
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

}
