package group11.Hockey;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.models.Player;

public class GoliePositionTest {

	@Test
	public void claculateStrengthTest() {
		Player player = new Player(10, 10, 10, 10, "player one", "goalie", true, false, 30);
		GoaliePosition position = new GoaliePosition(player);
		float strenght = position.claculateStrength();
		Assert.assertEquals(strenght, 20, 20);
	}

}
