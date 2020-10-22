package group11.Hockey.models;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.db.IPlayerDb;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;

public class PlayerTest {

	private static Player playerWithParams;

	@BeforeClass
	public static void init() {
		playerWithParams = new Player((float) 10, (float) 10, (float) 10, (float) 10, "Player1", "forward", false,
				false, 30);
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
		when(playerDb.insertLeagueFreeAgents("league", "A1", "fwd", 0, 0, 0, 0, 0)).thenReturn(true);
		List<Player> listOfFreeAgents= new ArrayList<Player>();
		listOfFreeAgents.add(playerWithParams);
		Player player2 = new Player("league", playerDb);  
		boolean flag = player2.insertLeagueFreeAgents(listOfFreeAgents);
		Assert.assertFalse(flag);
	}
	
	@Test
	public void getPlayerStrengthTest() {
		float strength = playerWithParams.getPlayerStrength();
		Assert.assertEquals(strength, 25.0,25.0);
	}

}
