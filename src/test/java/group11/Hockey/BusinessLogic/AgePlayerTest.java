package group11.Hockey.BusinessLogic;

import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

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

		AgePlayer agePlayer = (AgePlayer) DefaultHockeyFactory.makeAgePlayer(league, 365, null, null);
		agePlayer.agePlayers();
		List<Conference> conferences = league.getConferences();
		List<Division> divisions = conferences.get(0).getDivisions();
		List<Team> teams = divisions.get(0).getTeams();
		List<Player> players = teams.get(0).getPlayers();
		Player player = players.get(0);
		Assert.assertEquals(20, player.getAge(), 20);
	}

}
