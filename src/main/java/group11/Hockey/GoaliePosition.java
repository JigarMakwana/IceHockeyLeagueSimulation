package group11.Hockey;

import group11.Hockey.models.Player;

public class GoaliePosition implements IPosition {
	private Player player;

	public GoaliePosition(Player player) {
		super();
		this.player = player;
	}

	@Override
	public float claculateStrength() {
		float strength = player.getSkating() + player.getSaving();
		float playerStrength = player.isInjured() ? strength / 2 : strength;
		return playerStrength;
	}

}
