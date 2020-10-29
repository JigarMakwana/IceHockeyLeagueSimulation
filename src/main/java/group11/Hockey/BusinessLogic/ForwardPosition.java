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
		float strength = player.getSkating() + player.getShooting() + (player.getChecking() / 2);
		float playerStrength = player.isInjured() ? strength / 2 : strength;
		return playerStrength;
	}

}
