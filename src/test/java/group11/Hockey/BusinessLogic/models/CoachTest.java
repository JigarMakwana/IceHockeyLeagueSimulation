/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.db.ICoachDb;

public class CoachTest {

	private static ICoach coach;

	@BeforeClass
	public static void init() {
		coach = DefaultHockeyFactory.makeCoach(Constants.stat1, Constants.stat2, Constants.stat3, Constants.stat4,
				Constants.headCoach);
	}

	@Test
	public void getNameTest() {
		Assert.assertEquals(coach.getName(), Constants.headCoach);
	}

	@Test
	public void setNameTest() {
		coach.setName(Constants.headCoach);
		Assert.assertEquals(coach.getName(), Constants.headCoach);
	}

	@Test
	public void getSkatingTest() {
		Assert.assertEquals(coach.getSkating(), (float) Constants.stat1, 1.1);
	}

	@Test
	public void getShootingTest() {
		Assert.assertEquals(coach.getShooting(), Constants.stat2, 1.2);
	}

	@Test
	public void getCheckingTest() {
		Assert.assertEquals(coach.getChecking(), Constants.stat3, 1.3);
	}

	@Test
	public void getSavingTest() {
		Assert.assertEquals(coach.getSaving(), Constants.stat4, 1.4);
	}

	@Test
	public void insertCoachesTest() {

		List<Coach> coachesList = new ArrayList<Coach>();
		coachesList.add((Coach) coach);

		ICoachDb coachDb = mock(ICoachDb.class);
		when(coachDb.insertCoaches(Constants.leagueName, Constants.headCoach, (float) Constants.stat1, Constants.stat2,
				Constants.stat3, Constants.stat4)).thenReturn(true);

		ICoach coach2 = DefaultHockeyFactory.makeCoach(Constants.leagueName, coachDb);

		boolean flag = ((Coach) coach2).insertCoaches(coachesList);
		Assert.assertTrue(flag);

	}

}
