package group11.Hockey;

import group11.Hockey.BusinessLogic.IValidations;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class App {

	private static Logger logger = LogManager.getLogger(App.class);
	public static void main(String[] args) {
		logger.info("Entered App.java");
		ILeagueDb leagueDb = DefaultHockeyFactory.makeLeagueSerialisation();
		ICommandLineInput commandLineInput = DefaultHockeyFactory.makeCommandLineInput();
		IDisplay display = DefaultHockeyFactory.makeDisplay();
		IValidations validations = DefaultHockeyFactory.makeValidations(display);
		display.showMessageOnConsole("Welcome to Hockey Simulation!");
		StateMachineState currentState = null;
		try {
			if (args.length > 0) {
				String jsonFile = args[0];
				logger.info("Json provided");
				currentState = DefaultHockeyFactory.getJsonImport(jsonFile, commandLineInput, leagueDb, display);

			} else {
				logger.info("No Json provided");
				currentState = DefaultHockeyFactory.makeLoadTeam(commandLineInput, leagueDb);
			}
		} catch (Exception e) {
			logger.warn("Exception caught : "+e);
			System.exit(0);
		}
		do {
			currentState = currentState.startState();
		}while(currentState.ShouldContinue());

	}
}
