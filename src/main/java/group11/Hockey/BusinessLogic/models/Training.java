/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Training implements ITraining{
	private int daysUntilStatIncreaseCheck;
	private static Logger logger = LogManager.getLogger(Training.class);

	public Training(int daysUntilStatIncreaseCheck) {
		super();
		this.daysUntilStatIncreaseCheck = daysUntilStatIncreaseCheck;
	}

	public int getDaysUntilStatIncreaseCheck() {
		logger.info("Entered getDaysUntilStatIncreaseCheck()");
		return daysUntilStatIncreaseCheck;
	}

}
