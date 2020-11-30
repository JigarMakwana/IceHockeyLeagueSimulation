/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.Positions;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.PlayerStrength.IPlayerStrengthStrategy;
import group11.Hockey.BusinessLogic.models.IPlayer;

public class DefensePositionTest {

	@Test
	public void claculateStrengthTest() {
		IPlayer player = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "player one", "defense", true, false, 30);
		IPlayerStrengthStrategy position = DefaultHockeyFactory.makeForwarsPosition(player);
		float strenght = position.claculateStrength();
		Assert.assertEquals(strenght, 25, 25);

	}

}
