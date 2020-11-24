/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation;

import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.db.League.ILeagueDb;

public class ScheduleContext implements IScheduleContext{
	private IScheduleStrategy scheduleStrategy;

	public ScheduleContext(IScheduleStrategy scheduleStrategy) {
		super();
		this.scheduleStrategy = scheduleStrategy;
	}

	public StateMachineState executeStrategy(ILeague league, ILeagueDb leagueDb) {
		return scheduleStrategy.getSchedule(league, leagueDb);
	}

}
