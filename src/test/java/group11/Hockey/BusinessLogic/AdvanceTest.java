package group11.Hockey.BusinessLogic;

import org.junit.Assert;
import org.junit.Test;

public class AdvanceTest {

	@Test
	public void getAdvanceTimeTest() {
		IAdvance advance = new Advance();
		String advancedTime = advance.getAdvanceTime("00:00:00", 3);
		Assert.assertEquals("03:00:00", advancedTime);
	}

	@Test
	public void getAdvanceDateTest() {
		IAdvance advance = new Advance();
		String advancedDate = advance.getAdvanceDate("02/10/2020", 10);
		Assert.assertEquals("12/10/2020", advancedDate);
	}

}
