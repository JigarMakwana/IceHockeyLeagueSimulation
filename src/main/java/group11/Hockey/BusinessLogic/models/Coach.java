/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.db.ICoachDb;

public class Coach extends Stats implements ICoach{
	private String name;
	private String leagueName;
	private ICoachDb coachDb;
	private static Logger logger = LogManager.getLogger(Coach.class);

	public Coach(float skating, float shooting, float checking, float saving, String name) {
		super(skating, shooting, checking, saving);
		this.name = name;
	}

	public Coach(String leagueName, ICoachDb coachDb) {
		this.leagueName = leagueName;
		this.coachDb = coachDb;
	}

	public Coach() {
		super();
	}

	public String getName() {
		logger.info("Entered getName()");
		return name;
	}

	public void setName(String name) {
		logger.info("Entered setName()");
		this.name = name;
	}

	public boolean insertCoaches(List<Coach> coachesList) {
		logger.info("Entered insertCoaches()");
		boolean insertedToCoach = false;
		if (coachesList == null || coachesList.size() == 0) {
			insertedToCoach = true;
		} else {
			for (Coach coach : coachesList) {
				insertedToCoach = coachDb.insertCoaches(leagueName, coach.getName(), coach.getSkating(),
						coach.getShooting(), coach.getChecking(), coach.getSaving());
			}
		}
		return insertedToCoach;
	}

}
