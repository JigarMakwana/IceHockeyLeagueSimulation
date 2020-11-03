package group11.Hockey.BusinessLogic.models;

public class GameResolver implements IGameResolver {
	private float randomWinChance;

	public GameResolver(float randomWinChance) {
		super();
		this.randomWinChance = randomWinChance;
	}

	public float getRandomWinChance() {
		return randomWinChance;
	}

}
