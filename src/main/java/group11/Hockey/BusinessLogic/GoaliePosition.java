/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.IPlayer;

public class GoaliePosition implements IPlayerStrengthStrategy {
	private IPlayer player;
	private static Logger logger = LogManager.getLogger(GoaliePosition.class);

	public GoaliePosition(IPlayer player) {
		super();
		this.player = player;
	}

	@Override
	public float claculateStrength() {
		int reduceStrengthBy = BusinessConstants.Reduce_strength_by.getIntValue();
		float playerStrength = player.getSkating() + player.getSaving();
		if (player.isInjured()) {
			logger.warn("Player strength is reduced by " + reduceStrengthBy);
			playerStrength = playerStrength / reduceStrengthBy;
		}
		return playerStrength;
	}

}
