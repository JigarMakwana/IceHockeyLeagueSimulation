package group11.Hockey.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.AITrading;
import group11.Hockey.BusinessLogic.AdvanceToNextSeason;
import group11.Hockey.BusinessLogic.AgePlayer;
import group11.Hockey.BusinessLogic.IState;
import group11.Hockey.BusinessLogic.TrainingPlayer;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;
import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.League.ILeagueDb;

public class SimulateSeason implements ISimulateSeason {

	private HashMap<String, HashMap<Team, Team>> schedule;
	private League league;
	private ILeagueDb leagueDb;
	private IGameplayConfigDb gameplayConfigDb;
	private IPlayerDb playerDb;
	private ICoachDb coachDb;
	private IManagerDb managerDb;

	public SimulateSeason(HashMap<String, HashMap<Team, Team>> regularSchedule, League league, ILeagueDb leagueDb,
			IGameplayConfigDb gameplayConfigDb, IPlayerDb playerDb, ICoachDb coachDb, IManagerDb managerDb) {
		this.schedule = regularSchedule;
		this.league = league;
		this.leagueDb = leagueDb;
		this.gameplayConfigDb = gameplayConfigDb;
		this.playerDb = playerDb;
		this.coachDb = coachDb;
		this.managerDb = managerDb;
	}

	@Override
	public boolean StartSimulatingSeason(String startDate, String advanceDate) {

		// String startDate = date;
		Date dateTime, stanleyStartDateTime, stanleyEndDateTime, tradeDeadLine;

		String stanleyDate;
		int startYear, endYear;

		IParse parse = new Parse();
		startYear = parse.stringToYear(startDate);
		dateTime = parse.stringToDate(startDate);
		IDeadlines deadline = new Deadlines();
		stanleyStartDateTime = deadline.getStanleyPlayoffBeginDate(startDate);
		stanleyDate = parse.dateToString(stanleyStartDateTime);
		stanleyEndDateTime = deadline.getStanleyPlayoffDeadline(startDate);
		tradeDeadLine = deadline.getTradeDeadline(startDate);

		endYear = parse.stringToYear(stanleyDate);

		IPrintToConsole console = new PrintToConsole();
		String message = "********** Simulation started **********";
		console.print(message);

		// on or before stanley playoff end date
		List<Team> qualifiedTeams = league.getQualifiedTeams();

		dateTime = parse.stringToDate(advanceDate);

		int daysDifference = (int) ((parse.stringToDate(advanceDate).getTime()
				- parse.stringToDate(startDate).getTime()) / (24 * 60 * 60 * 1000));
		GameplayConfig gameplayConfig = league.getGamePlayConfig();
		int trainingDays = gameplayConfig.getTraining().getDaysUntilStatIncreaseCheck();
		if (daysDifference > trainingDays) {
			TrainingPlayer trainingPlayer = new TrainingPlayer();
			trainingPlayer.trainPlayer(league);
		}

		ICheckAndSimulateTodaySchedule simulateToday = new CheckAndSimulateTodaySchedule(schedule, league);
		simulateToday.CheckAndSimulateToday(advanceDate);

		// on or before trade deadline
		if (dateTime.compareTo(tradeDeadLine) <= 0) {
			AITrading aiTrading = new AITrading(league);
			aiTrading.generateTradeOffers();
		}

		IState currentState = new AgePlayer(league, 1);
		currentState.startState();

		// on the last day of stanley playoff
		if ((dateTime.equals(stanleyEndDateTime)) || (qualifiedTeams.size() == 1)) {

			IState currentState1 = new AdvanceToNextSeason(advanceDate, qualifiedTeams, dateTime, startYear, endYear,
					league);
			currentState1.startState();

			league.insertLeagueObject(league, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
			// break;
			return true;
		}
		return false;
	}

}
