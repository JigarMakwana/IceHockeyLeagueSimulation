/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GameResolver implements IGameResolver {
	private float randomWinChance;
	private static Logger logger = LogManager.getLogger(GameResolver.class);

	public GameResolver(float randomWinChance) {
		super();
		this.randomWinChance = randomWinChance;
	}

	public float getRandomWinChance() {
		logger.info("Entered getRandomWinChance()");
		return randomWinChance;
	}

}
