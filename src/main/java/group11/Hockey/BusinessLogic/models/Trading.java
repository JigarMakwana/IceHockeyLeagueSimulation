package group11.Hockey.BusinessLogic.models;

public class Trading implements ITrading{
	private int lossPoint;
	private float randomTradeOfferChance;
	private int maxPlayersPerTrade;
	private float randomAcceptanceChance;
	private IgmTable gmTable;

	public Trading(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance,
				   IgmTable gmTable) {
		super();
		this.lossPoint = lossPoint;
		this.randomTradeOfferChance = randomTradeOfferChance;
		this.maxPlayersPerTrade = maxPlayersPerTrade;
		this.randomAcceptanceChance = randomAcceptanceChance;
		this.gmTable = gmTable;
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

	public IgmTable getGmTable() {
		return gmTable;
	}
}
