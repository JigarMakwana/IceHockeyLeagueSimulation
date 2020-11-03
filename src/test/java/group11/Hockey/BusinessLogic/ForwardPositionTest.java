package group11.Hockey.BusinessLogic;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.ForwardPosition;
import group11.Hockey.BusinessLogic.models.Player;

public class ForwardPositionTest {

	@Test
	public void claculateStrengthTest() {
		Player player = new Player(10, 10, 10, 10, "player one", "forward", true, false, 30);
		ForwardPosition position = new ForwardPosition(player);
		float strenght = position.claculateStrength();
		Assert.assertEquals(strenght, 25, 25);
	}

}
