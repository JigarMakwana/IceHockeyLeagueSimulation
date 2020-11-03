package group11.Hockey.models;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.GameResolver;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.Trading;
import group11.Hockey.BusinessLogic.models.Training;
import group11.Hockey.db.IGameplayConfigDb;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameplayConfigTest {

	private static GameplayConfig gameplayConfig;
	private static Aging aging;
	private static GameResolver gameResolver;
	private static Injuries injuries;
	private static Training training;
	private static Trading trading;

	@BeforeClass
	public static void init() {
		IGameplayConfigDb gameplayConfigDb = mock(IGameplayConfigDb.class);
		aging = new Aging(30, 55);
		gameResolver = new GameResolver(0);
		injuries = new Injuries(0, 0, 0);
		training = new Training(0);
		trading = new Trading(0, 0, 0, 0);
		when(gameplayConfigDb.insertGameplayConfig(aging, gameResolver, injuries, training, trading, "league")).thenReturn(true);

		gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, trading, gameplayConfigDb,
				"league");
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
