package group11.Hockey.models;

public class GameResolver {
	private float randomWinChance;

	public GameResolver(float randomWinChance) {
		super();
		this.randomWinChance = randomWinChance;
	}

	public float getRandomWinChance() {
		return randomWinChance;
	}

}
