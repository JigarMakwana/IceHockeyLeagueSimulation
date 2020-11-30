package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.BusinessLogic.models.Roster.Roster;
import group11.Hockey.BusinessLogic.models.Roster.RosterSearch;
import group11.Hockey.BusinessLogic.Trophy.CalderMemorial;
import group11.Hockey.BusinessLogic.Trophy.EndOfRegularSeasonSubject;
import group11.Hockey.BusinessLogic.Trophy.EndOfStanleySubject;
import group11.Hockey.BusinessLogic.Trophy.JackAdams;
import group11.Hockey.BusinessLogic.Trophy.MauriceRichard;
import group11.Hockey.BusinessLogic.Trophy.Participation;
import group11.Hockey.BusinessLogic.Trophy.President;
import group11.Hockey.BusinessLogic.Trophy.RobHawkeyMemorial;
import group11.Hockey.BusinessLogic.Trophy.Trophy;
import group11.Hockey.BusinessLogic.Trophy.Veniza;
import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophySubject;

import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import group11.Hockey.BusinessLogic.Aging.AgePlayer;
import group11.Hockey.BusinessLogic.Drafting.DraftPlayer;
import group11.Hockey.BusinessLogic.Drafting.GeneratingPlayers;
import group11.Hockey.BusinessLogic.Drafting.IGeneratingPlayers;
import group11.Hockey.BusinessLogic.LeagueSimulation.Deadlines;
import group11.Hockey.BusinessLogic.LeagueSimulation.IDeadlines;
import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.ISchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleStrategy;
import group11.Hockey.BusinessLogic.LeagueSimulation.InitializeSeason;
import group11.Hockey.BusinessLogic.LeagueSimulation.Parse;
import group11.Hockey.BusinessLogic.LeagueSimulation.PlayoffSchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.Schedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.ScheduleContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.ActiveDefencePlayer;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.ActiveForwardPlayer;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.ActiveGoaliePlayer;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GameContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GameSimulation;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GeneratePlayOffShifts;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GenerateShifts;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GenerateShiftsTemplate;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.IGameContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.IGameSimulation;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.IGameStrategy;
import group11.Hockey.BusinessLogic.TeamCreation.CreateTeam;
import group11.Hockey.BusinessLogic.TeamCreation.LoadTeam;
import group11.Hockey.BusinessLogic.TeamCreation.PlayerChoice;
import group11.Hockey.BusinessLogic.Training.TrainingPlayer;
import group11.Hockey.InputOutput.CommandLineInput;
import group11.Hockey.InputOutput.Display;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;
import group11.Hockey.InputOutput.JsonParsing.JsonImport;
import group11.Hockey.db.Deserialize;
import group11.Hockey.db.IDeserialize;
import group11.Hockey.db.ISerialize;
import group11.Hockey.db.Serialize;
import group11.Hockey.db.Coach.ICoachDb;
import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.League.LeagueSerialisation;

public class DefaultHockeyFactory extends TeamFactory {

	public DefaultHockeyFactory() {
		super();
	}

	public static ITeam makeTeam() {
		return new Team();
	}

	public static ITeam makeTeam(String teamName, IGeneralManager generalManager, ICoach headCoach, List<IPlayer> players){
		return new Team(teamName, generalManager, headCoach, players);
	}

	public static League makeLeague() {
		return new League();
	}

	public static ILeague makeLeague(String leagueName, List<IConference> conferences, List<? extends IPlayer> freeAgents,
									 IGameplayConfig gamePlayConfig, List<ICoach> coaches, List<IGeneralManager> generalManagers){
		return new League(leagueName,  conferences,  freeAgents, gamePlayConfig,  coaches, generalManagers);
	}

	public static JsonImport getJsonImport(String fileName, ICommandLineInput commandLineInput, ILeagueDb leagueDb,
			IDisplay display) {
		return new JsonImport(fileName, commandLineInput, leagueDb, display);
	}

	public static IDivision makeDivision() {
		return new Division();
	}

	public static IDivision makeDivision(String division, List<ITeam> teamList) {
		return new Division(division, teamList);
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

	public static StateMachineState makeCreateTeam(ILeague league, ICommandLineInput commandLineInput,
			ILeagueDb leagueDb, IDisplay display) {
		IValidations validation = makeValidations(display);
		return new CreateTeam(league, commandLineInput, display, validation, leagueDb);

	}

	public static StateMachineState makePlayerChoice(ILeague league, ICommandLineInput commandLineInput,
			ILeagueDb leagueDb, IDisplay display) {
		IValidations validation = Validations.getInstance();
		return new PlayerChoice(league, commandLineInput, display, validation, leagueDb);
	}

	public static StateMachineState makeSimulate(ILeague league, int seasons, ILeagueDb leagueDb, IDisplay display, ICommandLineInput commandLineInput, IValidations validation) {
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

	public static IPrintToConsole makePrintToConsole() {
		return new PrintToConsole();
	}

	public static IGameStrategy makeDefencePlayerActive() {
		return new ActiveDefencePlayer();
	}

	public static IGameStrategy makeForwardPlayerActive() {
		return new ActiveForwardPlayer();
	}

	public static IGameStrategy makeGoaliePlayerActive() {
		return new ActiveGoaliePlayer();
	}

	public static IGameContext makeGameContext(IGameStrategy gameStrategy) {
		return new GameContext(gameStrategy);
	}

	public static GenerateShiftsTemplate makeGenerateShifts(List<IPlayer> team) {
		return new GenerateShifts(team);
	}

	public static GenerateShiftsTemplate makeGeneratePlayOffShifts(List<IPlayer> team) {
		return new GeneratePlayOffShifts(team);
	}

	public static StateMachineState makeAdvanceToNextSeason(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		return new AdvanceToNextSeason(league, leagueDb, display);
	}

	public static IRoster makeRoster(String teamName, List<IPlayer> playerList){
		return new Roster(teamName, playerList);
	}

	public static IRosterSearch makeRosterSearch() {
		return new RosterSearch();
	}

	public static GameplayConfig makeGameplayConfig(IAging aging, IInjuries injuries, ITraining training,
			ITrading trading) {
		return new GameplayConfig(aging, injuries, training, trading);
	}

	public static IgmTable makeGMTable(float shrewd, float gambler, float normal) {
		return new gmTable(shrewd, gambler, normal);
	}

	public static Trading makeTradingConfig(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade,
			float randomAcceptanceChance, IgmTable gmTable) {
		return new Trading(lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance, gmTable);
	}

	public static StateMachineState makeAgePlayer(ILeague league, int days, ILeagueDb leagueDb, IDisplay display) {
		return new AgePlayer(league, days, leagueDb, display);
	}

	public static IPlayerStrengthStrategy makeDefensePosition(IPlayer player) {
		return new DefensePosition(player);
	}

	public static IPlayerStrengthStrategy makeForwarsPosition(IPlayer player) {
		return new ForwardPosition(player);
	}

	public static IPlayerStrengthStrategy makeGoaliePosition(IPlayer player) {
		return new GoaliePosition(player);
	}

	public static IPlayer makePlayer(float skating, float shooting, float checking, float saving, String playerName,
			String position, boolean captain, boolean isFreeAgent, float age) {
		return new Player(skating, shooting, checking, saving, playerName, position, captain, isFreeAgent, age);
	}

	public static IPlayer makePlayer(float skating, float shooting, float checking, float saving, String playerName,
									 String position, boolean captain, boolean isFreeAgent, float age, boolean isActive) {
		return new Player(skating, shooting, checking, saving, playerName, position, captain, isFreeAgent, age, isActive);
	}

	public static IInjurySystem makeInjurySystem(ILeague league) {
		return new InjurySystem(league);
	}

	public static IPlayerStrengthContext makePlayerStrengthContext(IPlayerStrengthStrategy currentContext) {
		return new PlayerStrengthContext(currentContext);
	}

	public static IGameSimulation makeGameSimulation(ILeague league, ITeam team1, ITeam team2) {
		return new GameSimulation(league, team1, team2);
	}

	public static IAging makeAging(int averageRetirementAge, int maximumAge) {
		return new Aging(averageRetirementAge, maximumAge);
	}

	public static ICoach makeCoach(float skating, float shooting, float checking, float saving, String name) {
		return new Coach(skating, shooting, checking, saving, name);
	}

	public static ICoach makeCoach(String name, ICoachDb coachDb) {
		return new Coach(name, coachDb);
	}

	public static IConference makeConference(String name, List<IDivision> divisions) {
		return new Conference(name, divisions);
	}

	public static IGameResolver makeGameResolver(float randomWinChance) {
		return new GameResolver(randomWinChance);
	}

	public static IInjuries makeInjuries(float randomInjuryChance, int injuryDaysLow, int injuryDaysHigh) {
		return new Injuries(randomInjuryChance, injuryDaysLow, injuryDaysHigh);
	}

	public static ITraining makeTraining(int daysUntilStatIncreaseCheck) {
		return new Training(daysUntilStatIncreaseCheck);
	}

	public static IGeneralManager makeGeneralManager(String name, String personality) {
		return new GeneralManager(name, personality);
	}

	public static IPlayer makePlayer() {
		return new Player();
	}

	public static StateMachineState makeDraftPlayer(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		return new DraftPlayer(league, leagueDb, display);

	}

	public static StateMachineState makeTrophy(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		return new Trophy(league, leagueDb, display);
	}

	public static ITrophySubject makeEndOfRegularSeasonSubject(ILeague league) {
		return new EndOfRegularSeasonSubject(league);
	}

	public static ITrophySubject makeEndOfStanleySubject(ILeague league) {
		return new EndOfStanleySubject(league);
	}

	public static ITrophyObserver makePresident(ILeague league) {
		return new President(league);
	}

	public static ITrophyObserver makeCalderMemorial(ILeague league) {
		return new CalderMemorial(league);
	}

	public static ITrophyObserver makeVeniza(ILeague league) {
		return new Veniza(league);
	}

	public static ITrophyObserver makeJackAdams(ILeague league) {
		return new JackAdams(league);
	}

	public static ITrophyObserver makeMauriceRichard(ILeague league) {
		return new MauriceRichard(league);
	}

	public static ITrophyObserver makeRobHawkeyMemorial(ILeague league) {
		return new RobHawkeyMemorial(league);
	}

	public static ITrophyObserver makeParticipation(ILeague league) {
		return new Participation(league);
	}

	public static IGeneratingPlayers makeGeneratePlayer() {
		return new GeneratingPlayers();
	}

	public static Gson makeGson() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

	public static JSONParser makeGsonJsonParser() {
		return new JSONParser();
	}

	public static ITimeLine makeTimeLine() {
		return new TimeLine();
	}

	public static ISchedule makeSchedule(ILeague league) {		
		return new Schedule(league);
	}

	public static IDeadlines makeDeadlines() {
		return new Deadlines();
	}
}
