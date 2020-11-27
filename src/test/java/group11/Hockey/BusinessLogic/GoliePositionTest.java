package group11.Hockey.BusinessLogic;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.IPlayer;

public class GoliePositionTest {

	@Test
	public void claculateStrengthTest() {
		IPlayer player = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "player one", "forward", true, false, 30);
		IPlayerStrengthStrategy position = DefaultHockeyFactory.makeGoaliePosition(player);
		float strenght = position.claculateStrength();
		Assert.assertEquals(strenght, 20, 20);
	}

}
