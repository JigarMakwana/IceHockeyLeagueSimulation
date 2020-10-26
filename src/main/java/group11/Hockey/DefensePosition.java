package group11.Hockey;

import group11.Hockey.models.Player;

public class DefensePosition implements IPosition {
	private Player player;

	public DefensePosition(Player player) {
		super();
		this.player = player;
	}

	@Override
	public float claculateStrength() {
		float strength = player.getSkating() + player.getChecking() + (player.getShooting() / 2);
		return player.isInjured() ? strength / 2 : strength;
	}

}
