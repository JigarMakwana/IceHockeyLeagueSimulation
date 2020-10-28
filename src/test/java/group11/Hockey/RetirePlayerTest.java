package group11.Hockey;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.LeagueModelMock;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

public class RetirePlayerTest {

	@Test
	public void checkForRetirementTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		League league = leagueModel.getLeagueInfo();

		AgePlayer retirePlayer = new AgePlayer();
		boolean isRetired = retirePlayer.checkForRetirement(league, 50);
		Assert.assertTrue(isRetired);
	}
	
	@Test
	public void retireAndReplacePlayer() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		League league = leagueModel.getLeagueInfo();

		AgePlayer retirePlayer = new AgePlayer();
		retirePlayer.retireAndReplacePlayer(league);
		List<Conference> conferences = league.getConferences();
		List<Division> divisions = conferences.get(0).getDivisions();
		List<Team> teams = divisions.get(0).getTeams();
		List<Player> players = teams.get(0).getPlayers();
		Assert.assertEquals(players.size(), 2);
	}

}
