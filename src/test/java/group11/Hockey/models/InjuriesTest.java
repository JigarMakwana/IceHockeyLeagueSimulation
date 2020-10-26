package group11.Hockey.models;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.BeforeClass;

public class InjuriesTest {

	private static Injuries injuries;

	@BeforeClass
	public static void init() {
		injuries = new Injuries((float) 1.1, 1, 100);
	}

	@Test
	public void getRandomInjuryChanceTest() {
		Assert.assertEquals(injuries.getRandomInjuryChance(), 1.1, 1.1);
	}

	@Test
	public void getInjuryDaysLowTest() {
		Assert.assertEquals(injuries.getInjuryDaysLow(), 1);
	}

	@Test
	public void getInjuryDaysHighTest() {
		Assert.assertEquals(injuries.getInjuryDaysHigh(), 100);
	}

}
