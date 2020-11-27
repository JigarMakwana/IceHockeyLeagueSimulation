/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class Simulate extends StateMachineState {
	private ILeague league;
	private int seasons;
	private ILeagueDb leagueDb;
	IDisplay display;

	public Simulate(ILeague league, int seasons, ILeagueDb leagueDb, IDisplay display) {
		super();
		this.league = league;
		this.seasons = seasons;
		this.leagueDb = leagueDb;
		this.display = display;
	}

	@Override
	public StateMachineState startState() {
		while (seasons > 0) {
			StateMachineState currentState = DefaultHockeyFactory.makeInitializeSeason(league, leagueDb, display);
			do {
				currentState = currentState.startState();
			} while (currentState.ShouldContinue());
			seasons--;
		}

		return DefaultHockeyFactory.makeFinalState();
	}

}
