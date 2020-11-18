package group11.Hockey.BusinessLogic;

import org.json.simple.parser.JSONParser;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.CommandLineInput;
import group11.Hockey.InputOutput.Display;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.InputOutput.JsonParsing.JsonImport;
import group11.Hockey.db.Deserialize;
import group11.Hockey.db.IDeserialize;
import group11.Hockey.db.ISerialize;
import group11.Hockey.db.Serialize;
import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.League.LeagueSerialisation;
import group11.Hockey.models.InitializeSeason;

public class DefaultHockeyFactory extends TeamFactory {
	

	public DefaultHockeyFactory() {
		super();
	}

	public static Team makeTeam() {
		return new Team();
	}

	public static League makeLeague() {
		return new League();
	}

	public static JsonImport getJsonImport(String fileName, ICommandLineInput commandLineInput, ILeagueDb leagueDb) {
		return new JsonImport(fileName, commandLineInput, leagueDb);
	}

	public static IDivision makeDivision() {
		return new Division();
	}

	public static IConference makeConference() {
		return new Conference();
	}

	public static ICommandLineInput makeCommandLineInput() {
		return CommandLineInput.getInstance();
	}

	public static IValidations makeValidations(IDisplay display) {
		return new Validations(display);
	}

	public static IDisplay makeDisplay() {
		
		return Display.getInstance();
	}

	public static IUserInputCheck makeUserInputCheck(ICommandLineInput commandLineInput, IValidations validation,
			IDisplay display) {
		return new UserInputCheck(commandLineInput, validation, display);
	}
	
	public static StateMachineState makeCreateTeam(League league, ICommandLineInput commandLineInput, ILeagueDb leagueDb) {
		IDisplay display = Display.getInstance();
		IValidations validation = makeValidations(display);
		return new CreateTeam(league, commandLineInput, display, validation, leagueDb);
		
	}
	
	public static StateMachineState makePlayerChoice(League league, ICommandLineInput commandLineInput, ILeagueDb leagueDb) {
		IDisplay display = Display.getInstance();
		IValidations validation = Validations.getInstance();
		return new PlayerChoice(league, commandLineInput, display, validation, leagueDb);
	}
	
	public static StateMachineState makeSimulate(League league, int seasons, ILeagueDb leagueDb) {
		return new Simulate(league, seasons, leagueDb);
	}
	
	public static JSONParser makeJSONParser() {
		return new JSONParser();
	}
	
	public static Exception makeExceptionCall(String message) {
		return new Exception(message);
	}
	
	public static ISerialize makeSerializeLeague() {
		return Serialize.getInstance();
	}
	
	public static IDeserialize makeDeserializeLeague() {
		return Deserialize.getInstance();
	}
	
	public static ILeagueDb makeLeagueSerialisation() {
		return LeagueSerialisation.getInstance();
	}
	
	public static StateMachineState makeLoadTeam(ICommandLineInput userInputMode, ILeagueDb leagueDb) {
		IDisplay display = Display.getInstance();
		IValidations validation = Validations.getInstance();
		return new LoadTeam(userInputMode, display, validation, leagueDb);

	}

	public static StateMachineState makeInitializeSeason(ILeague league, ILeagueDb leagueDb) {
		return new InitializeSeason(league, leagueDb);
	}

	public static StateMachineState makeFinalState() {
		return FinalState.getInstance();
	}
}
