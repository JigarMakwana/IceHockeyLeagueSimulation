package group11.Hockey.BusinessLogic.models;

import java.util.Dictionary;
import java.util.Hashtable;

public class Trading implements ITrading{
	private int lossPoint;
	private float randomTradeOfferChance;
	private int maxPlayersPerTrade;
	private float randomAcceptanceChance;
	private Dictionary gmTable = new Hashtable();

	public Trading(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance) {
		super();
		this.lossPoint = lossPoint;
		this.randomTradeOfferChance = randomTradeOfferChance;
		this.maxPlayersPerTrade = maxPlayersPerTrade;
		this.randomAcceptanceChance = randomAcceptanceChance;
	}

	public Trading(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance,
				   Dictionary gmTable) {
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

	public Dictionary getGmTable() {
		return gmTable;
	}
}
