/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.db.League.ILeagueDb;

public class ScheduleContext implements IScheduleContext{
	private IScheduleStrategy scheduleStrategy;
	private static Logger logger = LogManager.getLogger(ScheduleContext.class);

	public ScheduleContext(IScheduleStrategy scheduleStrategy) {
		super();
		this.scheduleStrategy = scheduleStrategy;
	}

	public StateMachineState executeStrategy(ILeague league, ILeagueDb leagueDb) {
		logger.debug("Entered executeStrategy()");
		return scheduleStrategy.getSchedule(league, leagueDb);
	}

}
