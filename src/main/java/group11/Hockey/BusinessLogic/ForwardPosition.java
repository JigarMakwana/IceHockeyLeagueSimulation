/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.models.Player;

public class ForwardPosition implements IPosition {

	private Player player;

	public ForwardPosition(Player player) {
		super();
		this.player = player;
	}

	@Override
	public float claculateStrength() {
		float playerStrength = player.getSkating() + player.getShooting() + (player.getChecking() / 2);
		if (player.isInjured()) {
			playerStrength = playerStrength / 2;
		}
		return playerStrength;
	}

}
