package group11.Hockey.BusinessLogic;

import java.util.List;

import org.json.simple.parser.JSONParser;

import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleStrategy;
import group11.Hockey.BusinessLogic.LeagueSimulation.InitializeSeason;
import group11.Hockey.BusinessLogic.LeagueSimulation.Parse;
import group11.Hockey.BusinessLogic.LeagueSimulation.PlayoffSchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.ScheduleContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.ActiveDefencePlayer;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.ActiveForwardPlayer;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GameContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GameSimulation;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GeneratePlayOffShifts;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GenerateShifts;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GenerateShiftsTemplate;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.ActiveGoaliePlayer;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.IGameContext;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.IGameSimulation;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.IGameStrategy;
import group11.Hockey.BusinessLogic.Trading.TradeCharter;
import group11.Hockey.BusinessLogic.Trading.TradeDraft;
import group11.Hockey.BusinessLogic.Trading.TradeGenerator;
import group11.Hockey.BusinessLogic.Trading.TradeInitializer;
import group11.Hockey.BusinessLogic.Trading.TradeResolver;
import group11.Hockey.BusinessLogic.Trading.TradeSettler;
import group11.Hockey.BusinessLogic.Trading.TradingConfig;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeDraft;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeInitializer;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeResolver;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeSettler;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.GameResolver;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.GeneralManager;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.IAging;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.IGameResolver;
import group11.Hockey.BusinessLogic.models.IGeneralManager;
import group11.Hockey.BusinessLogic.models.IInjuries;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITrading;
import group11.Hockey.BusinessLogic.models.ITraining;
import group11.Hockey.BusinessLogic.models.IgmTable;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Trading;
import group11.Hockey.BusinessLogic.models.Training;
import group11.Hockey.BusinessLogic.models.gmTable;
import group11.Hockey.BusinessLogic.models.Roster.Roster;
import group11.Hockey.BusinessLogic.models.Roster.RosterSearch;
import group11.Hockey.BusinessLogic.models.Roster.RosterSize;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.CommandLineInput;
import group11.Hockey.InputOutput.Display;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.InputOutput.JsonParsing.JsonImport;
import group11.Hockey.db.Deserialize;
import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IDeserialize;
import group11.Hockey.db.ISerialize;
import group11.Hockey.db.Serialize;
import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.League.LeagueSerialisation;

public class DefaultHockeyFactory extends TeamFactory {

	public DefaultHockeyFactory() {
		super();
	}

	public static ITeam makeTeam() {
		return new Team();
	}

	public static League makeLeague() {
		return new League();
	}

	public static JsonImport getJsonImport(String fileName, ICommandLineInput commandLineInput, ILeagueDb leagueDb,
			IDisplay display) {
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

	public static StateMachineState makeSimulate(League league, int seasons, ILeagueDb leagueDb, IDisplay display) {
		return new Simulate(league, seasons, leagueDb, display);
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

	public static StateMachineState makeInitializeSeason(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		return new InitializeSeason(league, leagueDb, display);
	}

	public static StateMachineState makeFinalState() {
		return FinalState.getInstance();
	}

	public static StateMachineState makeAdvanceTime(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		return new AdvanceTime(league, leagueDb, display);
	}

	public static StateMachineState makeTrainingPlayer(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		return new TrainingPlayer(league, leagueDb, display);
	}

	public static IScheduleContext makeScheduleContext(IScheduleStrategy scheduleStrategy) {
		return new ScheduleContext(scheduleStrategy);
	}

	public static IScheduleStrategy makePlayoffSchedule() {
		return new PlayoffSchedule();
	}

	public static IScheduleStrategy makePlayoffScheduleFinalRounds(IDisplay display) {
		return new PlayoffScheduleFinalRounds(display);
	}

	public static IParse makeParse() {
		return new Parse();
	}

	public static IAdvance makeAdvance() {
		return new Advance();
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

	public static IConstantSupplier makeConstantSupplier() {
		int activeRosterSize = RosterSize.ACTIVE_ROSTER_SIZE.getNumVal();
		int inActiveRosterSize = RosterSize.INACTIVE_ROSTER_SIZE.getNumVal();
		int forwardSize = RosterSize.FORWARD_SIZE.getNumVal();
		int defenseSize = RosterSize.DEFENSE_SIE.getNumVal();
		int goaliSize = RosterSize.GOALIE_SIZE.getNumVal();
		return new ConstantSupplier(activeRosterSize, inActiveRosterSize, forwardSize, defenseSize, goaliSize);
	}

	public static IRoster makeRoster(String teamName, List<IPlayer> playerList) {
		IConstantSupplier rosterSize = makeConstantSupplier();
		return new Roster(teamName, playerList, rosterSize);
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

	public static IRandomNoGenerator makeRandomNumberGenerator() {
		return new RandomNumberGenerator();
	}

	public static ITradeInitializer makeTradeInitializer(ILeague leagueObj) {
		return new TradeInitializer(leagueObj);
	}

	public static ITradeGenerator makeTradeGenerator(ITeam team, ITradingConfig tradingConfig, IDisplay display) {
		return new TradeGenerator(team, tradingConfig, display);
	}

	public static ITradeResolver makeTradeResolver(ITradeCharter tradeCharter, ITradingConfig tradingConfig,
			ICommandLineInput commandLineInput, IValidations validation, IDisplay display) {
		return new TradeResolver(tradeCharter, tradingConfig, commandLineInput, validation, display);
	}

	public static ITradeSettler makeTradeSettler(ITeam team, List<IPlayer> freeAgentList,
			ICommandLineInput commandLineInput, IValidations validation, IDisplay display,
			IConstantSupplier constantSupplier) {
		return new TradeSettler(team, freeAgentList, commandLineInput, validation, display, constantSupplier);
	}

	public static ITradeCharter makeTradeCharter(ITeam offeringTeam, List<IPlayer> offeredPlayerList, ITeam requestedteam,
			List<IPlayer> requestedPlayerList, int roundIdx) {
		return new TradeCharter(offeringTeam, offeredPlayerList, requestedteam, requestedPlayerList, roundIdx);

	}

	public static ITradingConfig makeConfigTrading(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade,
			float randomAcceptanceChance, IgmTable gmTable) {
		return new TradingConfig(lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance,
				gmTable);
	}

	public static ITradeDraft makeTradeDraft(ITeam offeringTeam, ITradingConfig tradingConfig, IDisplay display) {
		return new TradeDraft(offeringTeam, tradingConfig, display);
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

	public static IInjurySystem makeInjurySystem(ILeague league) {
		return new InjurySystem(league);
	}

	public static IPlayerStrengthContext makePlayerStrengthContext(IPlayerStrengthStrategy currentContext) {
		return new PlayerStrengthContext(currentContext);
	}

	public static StateMachineState makeSimulate(ILeague league, int seasons, ILeagueDb leagueDb, IDisplay display) {
		return new Simulate(league, seasons, leagueDb, display);
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

	public static IConference makeConference(String name, List<Division> divisions) {
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

	public static StateMachineState makeDraftPlayer(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		return new DraftPlayer(league, leagueDb, display);

	}
}
