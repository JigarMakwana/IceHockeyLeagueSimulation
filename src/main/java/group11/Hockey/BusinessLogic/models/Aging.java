/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Aging implements IAging{
	private int averageRetirementAge;
	private int maximumAge;
	private float statDecayChance;
	private static Logger logger = LogManager.getLogger(Aging.class);

	public Aging(int averageRetirementAge, int maximumAge) {
		super();
		this.averageRetirementAge = averageRetirementAge;
		this.maximumAge = maximumAge;
	}

	public int getAverageRetirementAge() {
		logger.info("Entered getAverageRetirementAge()");
		return averageRetirementAge;
	}

	public int getMaximumAge() {
		logger.info("Entered getMaximumAge()");
		return maximumAge;
	}
	
	public float getStatDecayChance() {
		logger.info("Entered getStatDecayChance()");
		return statDecayChance;
	}

	public void setStatDecayChance(float statDecayChance) {
		logger.info("Entered setStatDecayChance()");
		this.statDecayChance = statDecayChance;
	}


}
