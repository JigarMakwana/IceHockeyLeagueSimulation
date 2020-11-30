/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class StateMachineState {

	private static Logger logger = LogManager.getLogger(StateMachineState.class);

	public abstract StateMachineState startState();

	public boolean ShouldContinue() {
		logger.info("Continue to next State");
		return true;
	}
}
