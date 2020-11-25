package group11.Hockey.models;

import group11.Hockey.BusinessLogic.models.IgmTable;
import group11.Hockey.BusinessLogic.models.gmTable;
import org.junit.Test;
import group11.Hockey.BusinessLogic.models.Trading;
import org.junit.Assert;
import org.junit.BeforeClass;

public class TradingTest {

	private static Trading trading;

	@BeforeClass
	public static void init() {
		IgmTable gmTbale = new gmTable(-0.1f, 0.1f, 0.0f);
		trading = new Trading(1, 2, 3, 4, gmTbale);
	}

	@Test
	public void getLossPointTest() {
		Assert.assertEquals(trading.getLossPoint(),1);
	}

	@Test
	public void getRandomTradeOfferChanceTest() {
		Assert.assertEquals(trading.getRandomTradeOfferChance(),  2,0.0);
	}

	@Test
	public void getMaxPlayersPerTradeTest() {
		Assert.assertEquals(trading.getMaxPlayersPerTrade(), 3);
	}

	@Test
	public void getRandomAcceptanceChanceTest() {
		Assert.assertEquals(trading.getRandomAcceptanceChance(),  4,0.0);
	}

}
