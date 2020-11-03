package group11.Hockey.models;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.db.IPlayerDb;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;

public class PlayerTest {

	private static Player playerWithParams;
	private static float age = 30;

	@BeforeClass
	public static void init() {
		playerWithParams = new Player((float) 10, (float) 10, (float) 10, (float) 10, "Player1", "forward", false,
				false, age);
	}

	@Test
	public void PlayerDefaultParameterisedConstructorTest() {
		Assert.assertEquals("Player1", playerWithParams.getPlayerName());
		Assert.assertEquals("forward", playerWithParams.getPosition());
	}

	@Test
	public void setPlayerNameTest() {
		playerWithParams.setPlayerName("Player 1");
		Assert.assertEquals("Player 1", playerWithParams.getPlayerName());
	}

	@Test
	public void getPlayerNameTest() {
		Assert.assertEquals("Player1", playerWithParams.getPlayerName());
	}

	@Test
	public void setPositionTest() {
		playerWithParams.setPosition("forward");
		Assert.assertEquals("forward", playerWithParams.getPosition());
	}

	@Test
	public void getPositionTest() {
		Assert.assertEquals("forward", playerWithParams.getPosition());
	}

	@Test
	public void setCaptainTest() {
		Assert.assertFalse(playerWithParams.getCaptain());
	}

	@Test
	public void getCaptainTest() {
		Assert.assertFalse(playerWithParams.getCaptain());
	}

	@Test
	public void insertLeagueFreeAgentsTest() {
		IPlayerDb playerDb = mock(IPlayerDb.class);
		when(playerDb.insertLeagueFreeAgents("league", playerWithParams)).thenReturn(true);
		List<Player> listOfFreeAgents = new ArrayList<Player>();
		listOfFreeAgents.add(playerWithParams);
		Player player2 = new Player("league", playerDb);
		boolean flag = player2.insertLeagueFreeAgents(listOfFreeAgents);
		Assert.assertTrue(flag);
	}

	@Test
	public void insertLeagueRetiredPlayersTest() {
		IPlayerDb playerDb = mock(IPlayerDb.class);
		when(playerDb.insertLeagueRetiredPlayers("league", playerWithParams)).thenReturn(true);
		List<Player> listOfFreeAgents = new ArrayList<Player>();
		listOfFreeAgents.add(playerWithParams);
		Player player2 = new Player("league", playerDb);
		boolean flag = player2.insertLeagueRetiredPlayers(listOfFreeAgents);
		Assert.assertTrue(flag);
	}

	@Test
	public void deleteLeaguePlayersTest() {
		IPlayerDb playerDb = mock(IPlayerDb.class);
		when(playerDb.deleteLeaguePlayers("league")).thenReturn(true);
		List<Player> listOfFreeAgents = new ArrayList<Player>();
		listOfFreeAgents.add(playerWithParams);
		Player player2 = new Player("league", playerDb);
		boolean flag = player2.deleteLeaguePlayers();
		Assert.assertTrue(flag);
	}

	@Test
	public void getPlayerStrengthTest() {
		float strength = playerWithParams.getPlayerStrength();
		Assert.assertEquals(strength, 25.0, 25.0);
	}

	@Test
	public void increaseAgeTest() {
		LeagueModelMock leagueModel = new LeagueModelMock();
		League league = leagueModel.getLeagueInfo();
		playerWithParams.increaseAge(league, 365);
		Assert.assertEquals(age + 1, 31, 31);
	}

	@Test
	public void replacePlayerWithFreeAgentTest() {
		Player player1 = new Player(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		Player player2 = new Player(10, 10, 10, 10, "Agent one", "forward", true, true, 20);
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);

		LeagueModelMock leagueModel = new LeagueModelMock();
		League league = leagueModel.getLeagueInfo();
		List<Player> playerList2 = new ArrayList<Player>();
		playerList2.add(player2);
		league.setFreeAgents(playerList2);

		playerWithParams.replacePlayerWithFreeAgent(league, playerList);
		Assert.assertEquals(playerList.size(), 2);
	}

	@Test
	public void removeFreeAgentsFromLeagueTest() {
		LeagueModelMock leagueModelMock = new LeagueModelMock();
		League league = leagueModelMock.getLeagueInfo();
		Player player = new Player();
		Player freeAgent1 = new Player(10, 10, 10, 10, "Player 1", "forward", true, false, 50);
		Player freeAgent2 = new Player(10, 10, 10, 10, "Player 2", "forward", true, false, 50);
		List<Player> freeAgents = new ArrayList<Player>();
		freeAgents.add(freeAgent1);
		freeAgents.add(freeAgent2);
		player.removeFreeAgentsFromLeague(league, freeAgents);
		Assert.assertTrue(league.getFreeAgents().size() == 18);
	}

}
