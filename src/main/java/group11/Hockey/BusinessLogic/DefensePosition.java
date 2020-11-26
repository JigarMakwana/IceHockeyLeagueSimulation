/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.Player;

public class DefensePosition implements IPosition {
	private Player player;
	private static Logger logger = LogManager.getLogger(DefensePosition.class);

	public DefensePosition(Player player) {
		super();
		this.player = player;
	}

	@Override
	public float claculateStrength() {
		logger.info("Entered calculateStrength()");
		float playerStrength = player.getSkating() + player.getChecking() + (player.getShooting() / 2);
		if (player.isInjured()) {
			logger.info("Player "+player.getPlayerName()+" is injured");
			playerStrength = playerStrength / 2;
		}
		return playerStrength;
	}

}
