package group11.Hockey.BusinessLogic.models;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;

public class AgingTest {

	private static IAging aging;

	@BeforeClass
	public static void init() {
		aging = DefaultHockeyFactory.makeAging(Constants.age2, Constants.age1);
	}

	@Test
	public void ageintConstructorTest() {
		IAging aging = DefaultHockeyFactory.makeAging(Constants.age2, Constants.age1);
		Assert.assertEquals(aging.getAverageRetirementAge(), Constants.age2);
		Assert.assertEquals(aging.getMaximumAge(), Constants.age1);
	}

	@Test
	public void getAverageRetirementAgeTest() {
		Assert.assertEquals(aging.getAverageRetirementAge(), Constants.age2);
	}

	@Test
	public void getMaximumAgeTest() {
		Assert.assertEquals(aging.getMaximumAge(), Constants.age1);
	}

}
