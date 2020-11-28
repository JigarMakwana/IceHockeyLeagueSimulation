/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.IPlayer;

public class DefensePosition implements IPlayerStrengthStrategy {
	private IPlayer player;
	private static Logger logger = LogManager.getLogger(DefensePosition.class);

	public DefensePosition(IPlayer player) {
		super();
		this.player = player;
	}

	@Override
	public float claculateStrength() {
		logger.info("Entered claculateStrength()");
		float playerStrength = player.getSkating() + player.getChecking() + (player.getShooting() / 2);
		if (player.isInjured()) {
			playerStrength = playerStrength / 2;
		}
		return playerStrength;
	}

}
