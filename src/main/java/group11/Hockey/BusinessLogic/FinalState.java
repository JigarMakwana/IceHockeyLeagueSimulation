/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FinalState extends StateMachineState {

	private static FinalState finalStateInstance = null;
	private static Logger logger = LogManager.getLogger(FinalState.class);

	private FinalState() {

	}

	public static FinalState getInstance() {
		if (finalStateInstance == null) {
			finalStateInstance = new FinalState();
		}
		return finalStateInstance;
	}

	@Override
	public StateMachineState startState() {
		logger.warn("Stopping the state execution");
		return null;
	}

	public boolean ShouldContinue() {
		logger.info("Reached end of the Sate");
		return false;
	}

}
