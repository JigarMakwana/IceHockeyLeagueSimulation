package group11.Hockey;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.db.League.ILeagueDb;

public class App {
	public static void main(String[] args) {
		System.out.println("Welcome to Hockey Simulation!");
		ILeagueDb leagueDb = DefaultHockeyFactory.makeLeagueSerialisation();
		ICommandLineInput commandLineInput = DefaultHockeyFactory.makeCommandLineInput();
		StateMachineState currentState = null;
		try {
			if (args.length > 0) {
				String jsonFile = args[0];

				currentState = DefaultHockeyFactory.getJsonImport(jsonFile, commandLineInput, leagueDb);

			} else {
				currentState = DefaultHockeyFactory.makeLoadTeam(commandLineInput, leagueDb);
			}
		} catch (Exception e) {
			System.exit(0);
		}
		do {
			currentState = currentState.startState();
		}while(currentState.ShouldContinue());
		
	}
}
