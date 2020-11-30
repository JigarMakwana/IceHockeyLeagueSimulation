/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;

import org.junit.Assert;
import org.junit.BeforeClass;

public class InjuriesTest {

	private static IInjuries injuries;

	@BeforeClass
	public static void init() {
		injuries = DefaultHockeyFactory.makeInjuries((float) 1.1, 1, 100);
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
