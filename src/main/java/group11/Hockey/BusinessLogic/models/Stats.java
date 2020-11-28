/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Stats {

	private float skating;
	private float shooting;
	private float checking;
	private float saving;
	private static Logger logger = LogManager.getLogger(Stats.class);

	public Stats(float skating, float shooting, float checking, float saving) {
		super();
		this.skating = skating;
		this.shooting = shooting;
		this.checking = checking;
		this.saving = saving;
	}

	public Stats() {
	}

	public float getSkating() {
		logger.info("Entered getSkating()");
		return skating;
	}

	public float getShooting() {
		logger.info("Entered getShooting()");
		return shooting;
	}

	public float getChecking() {
		logger.info("Entered getChecking()");
		return checking;
	}

	public float getSaving() {
		logger.info("Entered getSaving()");
		return saving;
	}

	public void setSkating(float skating) {
		logger.info("Entered setSkating()");
		this.skating = skating;
	}

	public void setShooting(float shooting) {
		logger.info("Entered setShooting()");
		this.shooting = shooting;
	}

	public void setChecking(float checking) {
		logger.info("Entered setChecking()");
		this.checking = checking;
	}

	public void setSaving(float saving) {
		logger.info("Entered setSaving()");
		this.saving = saving;
	}

}
