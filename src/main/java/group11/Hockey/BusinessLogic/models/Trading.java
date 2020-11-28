/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Trading implements ITrading{
	private int lossPoint;
	private float randomTradeOfferChance;
	private int maxPlayersPerTrade;
	private float randomAcceptanceChance;
	private IgmTable gmTable;
	private static Logger logger = LogManager.getLogger(Trading.class);

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
		logger.info("Entered getLossPoint()");
		return lossPoint;
	}

	public float getRandomTradeOfferChance() {
		logger.info("Entered getRandomTradeOfferChance()");
		return randomTradeOfferChance;
	}

	public int getMaxPlayersPerTrade() {
		logger.info("Entered getMaxPlayersPerTrade()");
		return maxPlayersPerTrade;
	}

	public float getRandomAcceptanceChance() {
		logger.info("Entered getRandomAcceptanceChance()");
		return randomAcceptanceChance;
	}

	public IgmTable getGmTable() {
		logger.info("Entered getGmTable()");
		return gmTable;
	}
}
