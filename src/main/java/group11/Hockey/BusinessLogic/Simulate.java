/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.db.League.ILeagueDb;

public class Simulate extends StateMachineState {
	private ILeague league;
	private int seasons;
	private ILeagueDb leagueDb;
	private static Logger logger = LogManager.getLogger(Simulate.class);

	public Simulate(ILeague league, int seasons, ILeagueDb leagueDb) {
		super();
		this.league = league;
		this.seasons = seasons;
		this.leagueDb = leagueDb;
	}

	@Override
	public StateMachineState startState() {
		logger.info("Entered startState()");
		while (seasons > 0) {
			StateMachineState currentState = DefaultHockeyFactory.makeInitializeSeason(league, leagueDb);
			do {
				currentState = currentState.startState();
			} while (currentState.ShouldContinue());
			seasons--;
		}

		return DefaultHockeyFactory.makeFinalState();
	}

}
