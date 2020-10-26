package group11.Hockey.models;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

import org.junit.BeforeClass;

public class TrainingTest {
	private static Training training;

	@BeforeClass
	public static void init() {
		training = new Training(1);
	}
	
	@Test
	public void getDaysUntilStatIncreaseCheckTest() {
		Assert.assertEquals(training.getDaysUntilStatIncreaseCheck(),1);
	}

}
