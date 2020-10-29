package group11.Hockey;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.InjurySystem;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.models.LeagueModelMock;

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

}