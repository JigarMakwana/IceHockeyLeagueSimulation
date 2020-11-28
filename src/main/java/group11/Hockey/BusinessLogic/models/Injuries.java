/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Injuries implements IInjuries{
	private float randomInjuryChance;
	private int injuryDaysLow;
	private int injuryDaysHigh;
	private static Logger logger = LogManager.getLogger(Injuries.class);

	public Injuries(float randomInjuryChance, int injuryDaysLow, int injuryDaysHigh) {
		super();
		this.randomInjuryChance = randomInjuryChance;
		this.injuryDaysLow = injuryDaysLow;
		this.injuryDaysHigh = injuryDaysHigh;
	}

	public float getRandomInjuryChance() {
		logger.info("Entered getRandomInjuryChance()");
		return randomInjuryChance;
	}

	public int getInjuryDaysLow() {
		logger.info("Entered getInjuryDaysLow()");
		return injuryDaysLow;
	}

	public int getInjuryDaysHigh() {
		logger.info("Entered getInjuryDaysHigh()");
		return injuryDaysHigh;
	}

}
