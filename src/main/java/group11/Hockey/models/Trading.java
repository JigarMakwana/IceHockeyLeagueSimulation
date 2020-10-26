package group11.Hockey.models;

public class Trading {
	private int lossPoint;
	private float randomTradeOfferChance;
	private int maxPlayersPerTrade;
	private float randomAcceptanceChance;

	public Trading(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance) {
		super();
		this.lossPoint = lossPoint;
		this.randomTradeOfferChance = randomTradeOfferChance;
		this.maxPlayersPerTrade = maxPlayersPerTrade;
		this.randomAcceptanceChance = randomAcceptanceChance;
	}

	public int getLossPoint() {
		return lossPoint;
	}

	public float getRandomTradeOfferChance() {
		return randomTradeOfferChance;
	}

	public int getMaxPlayersPerTrade() {
		return maxPlayersPerTrade;
	}

	public float getRandomAcceptanceChance() {
		return randomAcceptanceChance;
	}

}
