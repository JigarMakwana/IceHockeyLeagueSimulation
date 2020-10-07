package group11.Hockey.models;

import org.junit.Test;

import junit.framework.Assert;

public class PlayerTest {
	@Test
	public void getPlayerNameTest() {
		Player player = new Player("Player1", "forward", false);
		Assert.assertEquals("Player1", player.getPlayerName());
	}

	@Test
	public void getPositionTest() {
		Player player = new Player("Player1", "forward", false);
		Assert.assertEquals("forward", player.getPosition());
	}

	@Test
	public void getCaptainTest() {
		Player player = new Player("Player1", "forward", false);
		Assert.assertFalse(player.getCaptain());
	}
}
