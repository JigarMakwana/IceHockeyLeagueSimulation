/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import org.junit.Test;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import junit.framework.Assert;

public class SimulateTest {

	@Test
	public void startStateTest() {
		ILeague league = new LeagueModelMock().getLeagueInfo();

		StateMachineState currentState = DefaultHockeyFactory.makeSimulate(league, 0, null, null, null, null);
		Assert.assertNotNull(currentState);
		Assert.assertTrue(currentState.ShouldContinue());

	}

}
