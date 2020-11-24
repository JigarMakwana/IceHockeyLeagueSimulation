package group11.Hockey.BusinessLogic;

import java.util.List;

import org.json.simple.parser.JSONParser;

import group11.Hockey.BusinessLogic.GameSimulation.DefencePlayerActive;
import group11.Hockey.BusinessLogic.GameSimulation.ForwardPlayerActive;
import group11.Hockey.BusinessLogic.GameSimulation.GameContext;
import group11.Hockey.BusinessLogic.GameSimulation.GameStrategy;
import group11.Hockey.BusinessLogic.GameSimulation.GeneratePlayOffShifts;
import group11.Hockey.BusinessLogic.GameSimulation.GenerateShifts;
import group11.Hockey.BusinessLogic.GameSimulation.GenerateShiftsTemplate;
import group11.Hockey.BusinessLogic.GameSimulation.GoaliePlayerActive;
import group11.Hockey.BusinessLogic.GameSimulation.IGameContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleStrategy;
import group11.Hockey.BusinessLogic.LeagueSimulation.InitializeSeason;
import group11.Hockey.BusinessLogic.LeagueSimulation.Parse;
import group11.Hockey.BusinessLogic.LeagueSimulation.PlayoffSchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.ScheduleContext;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
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

	public static StateMachineState makeCreateTeam(League league, ICommandLineInput commandLineInput,
			ILeagueDb leagueDb) {
		IDisplay display = Display.getInstance();
		IValidations validation = makeValidations(display);
		return new CreateTeam(league, commandLineInput, display, validation, leagueDb);

	}

	public static StateMachineState makePlayerChoice(League league, ICommandLineInput commandLineInput,
			ILeagueDb leagueDb) {
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

	public static StateMachineState makeAdvanceTime(ILeague league, ILeagueDb leagueDb) {
		return new AdvanceTime(league, leagueDb);
	}

	public static StateMachineState makeTrainingPlayer(ILeague league, ILeagueDb leagueDb) {
		return new TrainingPlayer(league, leagueDb);
	}

	public static IScheduleContext makeScheduleContext(IScheduleStrategy scheduleStrategy) {
		return new ScheduleContext(scheduleStrategy);
	}

	public static IScheduleStrategy makePlayoffSchedule() {
		return new PlayoffSchedule();
	}

	public static IScheduleStrategy makePlayoffScheduleFinalRounds() {
		return new PlayoffScheduleFinalRounds();
	}

	public static IParse makeParse() {
		return new Parse();
	}

	public static IAdvance makeAdvance() {
		return new Advance();
	}

	public static GameStrategy makeDefencePlayerActive() {
		return new DefencePlayerActive();
	}

	public static GameStrategy makeForwardPlayerActive() {
		return new ForwardPlayerActive();
	}

	public static GameStrategy makeGoaliePlayerActive() {
		return new GoaliePlayerActive();
	}

	public static IGameContext makeGameContext(GameStrategy gameStrategy) {
		return new GameContext(gameStrategy);
	}

	public static GenerateShiftsTemplate makeGenerateShifts(List<Player> team) {
		return new GenerateShifts(team);
	}

	public static GenerateShiftsTemplate makeGeneratePlayOffShifts(List<Player> team) {
		return new GeneratePlayOffShifts(team);
	}

	public static StateMachineState makeAdvanceToNextSeason(ILeague league, ILeagueDb leagueDb) {
		return new AdvanceToNextSeason(league, leagueDb);
	}

}
