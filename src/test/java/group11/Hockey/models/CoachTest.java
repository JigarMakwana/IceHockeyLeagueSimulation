package group11.Hockey.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.db.ICoachDb;

public class CoachTest {

	private static Coach coach;

	@BeforeClass
	public static void init() {
		coach = new Coach((float) 1.1, (float) 1.2, (float) 1.3, (float) 1.4, "head coach");
	}

	@Test
	public void getNameTest() {
		Assert.assertEquals(coach.getName(), "head coach");
	}

	@Test
	public void setNameTest() {
		coach.setName("head coach");
		Assert.assertEquals(coach.getName(), "head coach");
	}
	
	@Test
	public void getSkatingTest() {
		Assert.assertEquals(coach.getSkating(), (float) 1.1, 1.1);
	}

	@Test
	public void getShootingTest() {
		Assert.assertEquals(coach.getShooting(), (float) 1.2, 1.2);
	}

	@Test
	public void getCheckingTest() {
		Assert.assertEquals(coach.getChecking(), (float) 1.3, 1.3);
	}

	@Test
	public void getSavingTest() {
		Assert.assertEquals(coach.getSaving(), (float) 1.4, 1.4);
	}

	@Test
	public void insertCoachesTest() {

		List<Coach> coachesList = new ArrayList<Coach>();
		coachesList.add(coach);

		ICoachDb coachDb = mock(ICoachDb.class);
		when(coachDb.insertCoaches("leaguename", "head coach", (float) 1.1, (float) 1.2, (float) 1.3, (float) 1.4))
				.thenReturn(true);

		Coach coach2 = new Coach("leaguename", coachDb);

		boolean flag = coach2.insertCoaches(coachesList);
		Assert.assertTrue(flag);

	}

	

}
