package group11.Hockey;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefensePosition;
import group11.Hockey.BusinessLogic.models.Player;

public class DefensePositionTest {

	@Test
	public void claculateStrengthTest() {
		Player player = new Player(10, 10, 10, 10, "player one", "defense", true, false, 30);
		DefensePosition position = new DefensePosition(player);
		float strenght = position.claculateStrength();
		Assert.assertEquals(strenght, 25, 25);

	}

}
