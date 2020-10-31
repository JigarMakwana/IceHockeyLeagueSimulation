package group11.Hockey.models;

import static org.junit.Assert.*;

import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Trading;
import junit.framework.Assert;

import org.junit.BeforeClass;

public class TradingTest {

	private static Trading trading;

	@BeforeClass
	public static void init() {
		trading = new Trading(1, 2, 3, 4);
	}

	@Test
	public void getLossPointTest() {
		Assert.assertEquals(trading.getLossPoint(),1);
	}

	@Test
	public void getRandomTradeOfferChanceTest() {
		Assert.assertEquals(trading.getRandomTradeOfferChance(),  (float)2);
	}

	@Test
	public void getMaxPlayersPerTradeTest() {
		Assert.assertEquals(trading.getMaxPlayersPerTrade(), 3);
	}

	@Test
	public void getRandomAcceptanceChanceTest() {
		Assert.assertEquals(trading.getRandomAcceptanceChance(),  (float)4);
	}

}
