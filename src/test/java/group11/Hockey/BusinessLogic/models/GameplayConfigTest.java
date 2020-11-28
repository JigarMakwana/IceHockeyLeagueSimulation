package group11.Hockey.BusinessLogic.models;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.db.IGameplayConfigDb;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameplayConfigTest {

	private static IGameplayConfig gameplayConfig;
	private static IAging aging;
	private static IGameResolver gameResolver;
	private static IInjuries injuries;
	private static ITraining training;
	private static ITrading trading;

	@BeforeClass
	public static void init() {
		IGameplayConfigDb gameplayConfigDb = mock(IGameplayConfigDb.class);
		aging = DefaultHockeyFactory.makeAging(Constants.age1, Constants.age2);
		gameResolver = DefaultHockeyFactory.makeGameResolver(0);
		injuries = DefaultHockeyFactory.makeInjuries(0, 0, 0);
		training = DefaultHockeyFactory.makeTraining(0);
		IgmTable gmTbale = DefaultHockeyFactory.makeGMTable(-0.1f, 0.1f, 0.0f);
		trading = DefaultHockeyFactory.makeTradingConfig(0, 0, 0, 0, gmTbale);
		when(gameplayConfigDb.insertGameplayConfig(aging, gameResolver, injuries, training, trading, "league"))
				.thenReturn(true);

		gameplayConfig = DefaultHockeyFactory.makeGameplayConfig(aging, gameResolver, injuries, training, trading);
	}

	@Test
	public void getAgingTest() {
		Assert.assertEquals(gameplayConfig.getAging(), aging);
	}

	@Test
	public void getGameResolver() {
		Assert.assertEquals(gameplayConfig.getGameResolver(), gameResolver);
	}

	@Test
	public void getInjuries() {
		Assert.assertEquals(gameplayConfig.getInjuries(), injuries);
	}

	@Test
	public void getTraining() {
		Assert.assertEquals(gameplayConfig.getTraining(), training);
	}

	@Test
	public void getTrading() {
		Assert.assertEquals(gameplayConfig.getTrading(), trading);
	}

}
