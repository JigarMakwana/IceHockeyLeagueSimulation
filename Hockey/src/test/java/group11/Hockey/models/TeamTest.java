package group11.Hockey.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class TeamTest {
	@Test
	public void getTeamNameTest() {
		Team team = new Team("Vancouver Canucks", null, null, null);
		Assert.assertEquals("Vancouver Canucks", team.getTeamName());
	}

	@Test
	public void getGeneralManagerTest() {
		Team team = new Team("Vancouver Canucks", "John", null, null);
		Assert.assertEquals("John", team.getGeneralManager());
	}

	@Test
	public void getHeadCoachTest() {
		Team team = new Team("Vancouver Canucks", "John", "Peter", null);
		Assert.assertEquals("Peter", team.getHeadCoach());
	}

	// getPlayers
	@Test
	public void getPlayersTest() {
		List<Player> listOfPlayers = new ArrayList<Player>();
		Player player1 = new Player("Player1", "forward", true);
		Player player2 = new Player("Player2", "goalie", false);
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		Team team = new Team("Vancouver Canucks", "John", "Peter", listOfPlayers);
		Assert.assertEquals("playerName=" + player1.getPlayerName() + ", position=" + player1.getPosition()
				+ ", captain=" + player1.getCaptain(), team.getPlayers().get(0).toString());

		Assert.assertEquals("playerName=" + player2.getPlayerName() + ", position=" + player2.getPosition()
				+ ", captain=" + player2.getCaptain(), team.getPlayers().get(1).toString());
	}

	@Test
	public void getPlayersListSizeTest() {
		List<Player> listOfPlayers = new ArrayList<Player>();
		Player player1 = new Player("Player1", "forward", true);
		Player player2 = new Player("Player2", "goalie", false);
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		Team team = new Team("Vancouver Canucks", "John", "Peter", listOfPlayers);

		Assert.assertTrue(team.getPlayers().size() == 2);
	}

}
