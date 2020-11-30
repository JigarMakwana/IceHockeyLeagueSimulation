/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces;

import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.db.League.ILeagueDb;

public interface IScheduleContext {
	public StateMachineState executeStrategy(ILeague league, ILeagueDb leagueDb);
}
