package group11.Hockey.models;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AgingTest {

	private static Aging aging;

	@BeforeClass
	public static void init() {
		aging = new Aging(55, 30);
	}

	@Test
	public void ageintConstructorTest() {
		Aging aging = new Aging(55, 30);
		Assert.assertEquals(aging.getAverageRetirementAge(), 55);
		Assert.assertEquals(aging.getMaximumAge(), 30);
	}

	@Test
	public void getAverageRetirementAgeTest() {
		Assert.assertEquals(aging.getAverageRetirementAge(), 55);
	}
	
	@Test
	public void getMaximumAgeTest() {
		Assert.assertEquals(aging.getMaximumAge(), 30);
	}

}
