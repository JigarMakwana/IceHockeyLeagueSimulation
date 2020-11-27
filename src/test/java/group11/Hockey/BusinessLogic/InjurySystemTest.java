package group11.Hockey.BusinessLogic;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IInjuries;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class InjurySystemTest {

	@Test
	public void setInjuryToPlayersTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		League league = leagueModel.getLeagueInfo();
		List<Conference> conferences = league.getConferences();
		List<Division> divisions = conferences.get(0).getDivisions();
		List<Team> teams = divisions.get(0).getTeams();
		List<Player> players = teams.get(0).getPlayers();

		InjurySystem injurySystem = new InjurySystem(league);
		injurySystem.setInjuryToPlayers(teams.get(0));

		Assert.assertTrue(players.get(0).isInjured());
	}

	@Test
	public void determainIsPlayerInjuredTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		League league = leagueModel.getLeagueInfo();
		InjurySystem injurySystem = new InjurySystem(league);
		boolean flag = injurySystem.determainIsPlayerInjured();
		Assert.assertTrue(flag);
	}

	@Test
	public void determainNumberOfDaysOfInjuryTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		League league = leagueModel.getLeagueInfo();
		InjurySystem injurySystem = new InjurySystem(league);
		int injuredDays = injurySystem.determainNumberOfDaysOfInjury();
		IInjuries injuries = league.getGamePlayConfig().getInjuries();
		boolean flag = injuries.getInjuryDaysLow() < injuredDays || injuredDays >= injuries.getInjuryDaysHigh();
		Assert.assertTrue(flag);
	}

}
