package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.BusinessLogic.models.Roster.RosterSearch;
import group11.Hockey.BusinessLogic.Enums.RosterSize;
import org.json.simple.parser.JSONParser;

import group11.Hockey.BusinessLogic.models.Roster.Roster;
import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleStrategy;
import group11.Hockey.BusinessLogic.LeagueSimulation.InitializeSeason;
import group11.Hockey.BusinessLogic.LeagueSimulation.Parse;
import group11.Hockey.BusinessLogic.LeagueSimulation.PlayoffSchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.ScheduleContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.DefencePlayerActive;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.ForwardPlayerActive;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GameContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GameStrategy;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GeneratePlayOffShifts;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GenerateShifts;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GenerateShiftsTemplate;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GoaliePlayerActive;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.IGameContext;
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

	public static JsonImport getJsonImport(String fileName, ICommandLineInput commandLineInput, ILeagueDb leagueDb, IDisplay display) {
		return new JsonImport(fileName, commandLineInput, leagueDb, display);
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
			ILeagueDb leagueDb, IDisplay display) {
		IValidations validation = makeValidations(display);
		return new CreateTeam(league, commandLineInput, display, validation, leagueDb);

	}

	public static StateMachineState makePlayerChoice(League league, ICommandLineInput commandLineInput,
			ILeagueDb leagueDb, IDisplay display) {

		IValidations validation = Validations.getInstance();
		return new PlayerChoice(league, commandLineInput, display, validation, leagueDb);
	}

	public static StateMachineState makeSimulate(League league, int seasons, ILeagueDb leagueDb, IDisplay display, ICommandLineInput commandLineInput, IValidations validation) {
		return new Simulate(league, seasons, leagueDb, display, commandLineInput, validation);
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

	public static StateMachineState makeInitializeSeason(ILeague league, ILeagueDb leagueDb, IDisplay display, ICommandLineInput commandLineInput, IValidations validation) {
		return new InitializeSeason(league, leagueDb, display, commandLineInput, validation);
	}

	public static StateMachineState makeFinalState() {
		return FinalState.getInstance();
	}

	public static StateMachineState makeAdvanceTime(ILeague league, ILeagueDb leagueDb, IDisplay display, ICommandLineInput commandLineInput, IValidations validation
	) {
		return new AdvanceTime(league, leagueDb, display, commandLineInput, validation);
	}

	public static StateMachineState makeTrainingPlayer(ILeague league, ILeagueDb leagueDb, IDisplay display, ICommandLineInput commandLineInput, IValidations validation
	) {
		return new TrainingPlayer(league, leagueDb, display, commandLineInput, validation);
	}

	public static IScheduleContext makeScheduleContext(IScheduleStrategy scheduleStrategy) {
		return new ScheduleContext(scheduleStrategy);
	}

	public static IScheduleStrategy makePlayoffSchedule() {
		return new PlayoffSchedule();
	}

	public static IScheduleStrategy makePlayoffScheduleFinalRounds(IDisplay display, ICommandLineInput commandLineInput, IValidations validation) {
		return new PlayoffScheduleFinalRounds(display, commandLineInput, validation);
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

	public static StateMachineState makeAdvanceToNextSeason(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		return new AdvanceToNextSeason(league, leagueDb, display);
	}

	public static IRoster makeRoster(String teamName, List<Player> playerList){
		return new Roster(teamName, playerList);
	}

	public static IRosterSearch makeRosterSearch(){
		return new RosterSearch();
	}

	public static GameplayConfig makeGameplayConfig(Aging aging, GameResolver gameResolver, Injuries injuries,
													Training training, Trading trading){
		return new GameplayConfig(aging, gameResolver, injuries, training, trading);
	}

	public static IgmTable makeGMTable(float shrewd, float gambler, float normal){
		return new gmTable(shrewd, gambler, normal);
	}

	public static Trading makeTradingConfig(int lossPoint, float randomTradeOfferChance,
											int maxPlayersPerTrade, float randomAcceptanceChance,
											IgmTable gmTable){
		return new Trading(lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance, gmTable);
	}
}
