/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.models.Player;

public class GoaliePosition implements IPlayerStrengthStrategy {
	private Player player;

	public GoaliePosition(Player player) {
		super();
		this.player = player;
	}

	@Override
	public float claculateStrength() {
		float playerStrength = player.getSkating() + player.getSaving();
		if (player.isInjured()) {
			playerStrength = playerStrength / 2;
		}
		return playerStrength;
	}

}
