package group11.Hockey;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.db.League.ILeagueDb;

public class App {

	private static Logger logger = LogManager.getLogger(App.class);
	public static void main(String[] args) {
		logger.info("Entered App.java");
		System.out.println("Welcome to Hockey Simulation!");
		ILeagueDb leagueDb = DefaultHockeyFactory.makeLeagueSerialisation();
		ICommandLineInput commandLineInput = DefaultHockeyFactory.makeCommandLineInput();
		StateMachineState currentState = null;
		try {
			if (args.length > 0) {
				String jsonFile = args[0];
				logger.info("Json provided");
				currentState = DefaultHockeyFactory.getJsonImport(jsonFile, commandLineInput, leagueDb);

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
