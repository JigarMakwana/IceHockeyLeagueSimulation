package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class PlayerChoice  extends StateMachineState {

	ICommandLineInput commandLineInput;
	League league;
	IDisplay display;
	IValidations validation;
	ILeagueDb leagueDb;
	
	public PlayerChoice(League league, ICommandLineInput commandLineInput, IDisplay display, IValidations validation,
			ILeagueDb leagueDb){
		this.commandLineInput = commandLineInput;
		this.league = league;
		this.display = display;
		this.validation = validation;
		this.leagueDb = leagueDb;
	}

	@Override
	public StateMachineState startState() {
		boolean seasonsCheck = true;
		String numberOfSeasons = null;
		while (seasonsCheck) {
			display.showMessageOnConsole("Enter number of seasons to simulate:");
			numberOfSeasons = commandLineInput.getValueFromUser();
			seasonsCheck = validation.isNoOfSeasonsValueValid(numberOfSeasons);
		}
		int seasons = Integer.parseInt(numberOfSeasons);
		return DefaultHockeyFactory.makeSimulate(league, seasons, leagueDb);
	}

}
