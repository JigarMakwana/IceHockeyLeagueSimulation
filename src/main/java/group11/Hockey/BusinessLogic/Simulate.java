/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.ICommandLineInput;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class Simulate extends StateMachineState {
	private ILeague league;
	private int seasons;
	private ILeagueDb leagueDb;
	private IDisplay display;
	private ICommandLineInput commandLineInput;
	private IValidations validation;
	private static Logger logger = LogManager.getLogger(Simulate.class);

	public Simulate(ILeague league, int seasons, ILeagueDb leagueDb, IDisplay display) {
		super();
		this.league = league;
		this.seasons = seasons;
		this.leagueDb = leagueDb;
		this.display = display;
	}

	public Simulate(ILeague league, int seasons, ILeagueDb leagueDb, IDisplay display, ICommandLineInput commandLineInput, IValidations validation) {
		super();
		this.display = display;
		this.commandLineInput = commandLineInput;
		this.validation = validation;
		this.league = league;
		this.seasons = seasons;
		this.leagueDb = leagueDb;
	}

	@Override
	public StateMachineState startState() {
		logger.info("Entered startState()");
		while (seasons > 0) {
			StateMachineState currentState = DefaultHockeyFactory.makeInitializeSeason(league, leagueDb, display, commandLineInput, validation);
			do {
				currentState = currentState.startState();
			} while (currentState.ShouldContinue());
			seasons--;
		}

		return DefaultHockeyFactory.makeFinalState();
	}

}
