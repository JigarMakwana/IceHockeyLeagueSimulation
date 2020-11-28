/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class RetirePlayerTest {

	@Test
	public void checkForRetirementTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		ILeague league = leagueModel.getLeagueInfo();

		AgePlayer retirePlayer = new AgePlayer();
		boolean isRetired = retirePlayer.checkForRetirement(league, 55);
		Assert.assertTrue(isRetired);
	}

	@Test
	public void retireAndReplacePlayer() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		ILeague league = leagueModel.getLeagueInfo();

		AgePlayer retirePlayer = new AgePlayer();
		retirePlayer.retireAndReplacePlayer(league);
		List<IConference> conferences = league.getConferences();
		List<Division> divisions = conferences.get(0).getDivisions();
		List<Team> teams = divisions.get(0).getTeams();
		List<Player> players = teams.get(0).getPlayers();
		Assert.assertEquals(players.size(), 3);
	}

}
