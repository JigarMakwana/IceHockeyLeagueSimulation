/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PlayerStrength {
	private static Logger logger = LogManager.getLogger(PlayerStrength.class);
	public float calculatePlayerStrength(IPosition position) {
		logger.info("Entered calculatePlayerStrength()");
		return position.claculateStrength();
	}
}
