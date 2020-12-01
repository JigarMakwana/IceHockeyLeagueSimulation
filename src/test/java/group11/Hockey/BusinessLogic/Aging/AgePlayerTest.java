/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.Aging;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IParse;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;

public class AgePlayerTest {
	private static ILeague league;

	@BeforeClass
	public static void init() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		league = leagueModel.getLeagueInfo();
	}

	@Test
	public void startStateTest() {

		StateMachineState agePlayer = DefaultHockeyFactory.makeAgePlayer(league, 365, null, null);
		StateMachineState currentState = agePlayer.startState();
		Assert.assertNotNull(currentState);
	}

	@Test
	public void increaseAgeTest() {

		IAgePlayer agePlayer = (IAgePlayer) DefaultHockeyFactory.makeAgePlayer(league, 365, null, null);
		agePlayer.agePlayers();
		List<IConference> conferences = league.getConferences();
		List<IDivision> divisions = conferences.get(0).getDivisions();
		List<ITeam> teams = divisions.get(0).getTeams();
		List<IPlayer> players = teams.get(0).getPlayers();
		IPlayer player = players.get(0);
		Assert.assertEquals(20, player.getAge(), 20);
	}
	
	@Test
	public void getPlayerBirthDateTest() {
		AgePlayer agePlayer = (AgePlayer) DefaultHockeyFactory.makeAgePlayer(league, 365, null, null);
		agePlayer.agePlayers();
		List<IConference> conferences = league.getConferences();
		List<IDivision> divisions = conferences.get(0).getDivisions();
		List<ITeam> teams = divisions.get(0).getTeams();
		List<IPlayer> players = teams.get(0).getPlayers();
		IPlayer player = players.get(0);
		IParse parse = DefaultHockeyFactory.makeParse();
		agePlayer.getPlayerBirthDate(player, parse);
		Assert.assertEquals(20, player.getAge(), 20);
	}

}
