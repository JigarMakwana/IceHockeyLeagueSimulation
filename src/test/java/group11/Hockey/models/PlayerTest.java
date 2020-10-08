package group11.Hockey.models;

import org.junit.Assert;
import org.junit.Test;


public class PlayerTest {
	
	Player player = new Player();
	Player playerWithParams = new Player("Player1", "forward", Boolean.FALSE);
	
	@Test
	public void PlayerDefaultConstructorTest() {
		Assert.assertNull(player.getPlayerName());
		Assert.assertNull(player.getPosition());
		Assert.assertNull(player.getCaptain());
	}
	
	@Test
	public void PlayerDefaultParameterisedConstructorTest() {
		Player playerWithParams = new Player("Player 1", "Forward", Boolean.FALSE);
		Assert.assertEquals("Player 1", playerWithParams.getPlayerName());
		Assert.assertEquals("Forward", playerWithParams.getPosition());
	}
	
	@Test
	public void setPlayerNameTest() {
		player.setPlayerName("Player 1");
		Assert.assertEquals("Player 1", player.getPlayerName());
	}
	
	@Test
	public void getPlayerNameTest() {
		Assert.assertEquals("Player1", playerWithParams.getPlayerName());
	}
	
	@Test
	public void setPositionTest() {
		player.setPosition("Forward");
		Assert.assertEquals("Forward", player.getPosition());
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
		Player player = new Player("Player1", "forward", false);
		Assert.assertFalse(player.getCaptain());
	}
	
}
