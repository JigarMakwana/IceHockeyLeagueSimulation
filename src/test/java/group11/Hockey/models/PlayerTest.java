package group11.Hockey.models;

import org.junit.Assert;
import org.junit.Test;


public class PlayerTest {
	
	//Player player = new Player();
	Player playerWithParams = new Player(0, 0, 0, 0, "Player1", "forward", false,false, 0);
	
	
	@Test
	public void PlayerDefaultParameterisedConstructorTest() {
		Player playerWithParams = new Player(0, 0, 0, 0, "Player 1", "Forward", false,false, 0);
		Assert.assertEquals("Player 1", playerWithParams.getPlayerName());
		Assert.assertEquals("Forward", playerWithParams.getPosition());
	}
	
	@Test
	public void setPlayerNameTest() {
		//player.setPlayerName("Player 1");
		//Assert.assertEquals("Player 1", player.getPlayerName());
	}
	
	@Test
	public void getPlayerNameTest() {
		Assert.assertEquals("Player1", playerWithParams.getPlayerName());
	}
	
	@Test
	public void setPositionTest() {
		//player.setPosition("Forward");
		//Assert.assertEquals("Forward", player.getPosition());
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
		Player player = new Player(0, 0, 0, 0, "Player1", "forward", false,false, 0);
		Assert.assertFalse(player.getCaptain());
	}
	
}
