package group11.Hockey.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.Trading.AITrading;
import group11.Hockey.BusinessLogic.AgePlayer;
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
	public String StartSimulatingSeason(String date) {

		String startDate = date;
		Date dateTime, stanleyStartDateTime, stanleyEndDateTime, regularSeasonEndDateTime, tradeDeadLine, firstRoundEnd,
				secondRoundEnd, semiFinalsEnd, finalsEnd;
		String stanleyDate, firstRound = null, secondRound = null, semiFinalRound = null;
		int startYear, endYear;

		IParse parse = new Parse();
		startYear = parse.stringToYear(date);
		dateTime = parse.stringToDate(date);
		IDeadlines deadline = new Deadlines();
		stanleyStartDateTime = deadline.getStanleyPlayoffBeginDate(date);
		stanleyDate = parse.dateToString(stanleyStartDateTime);
		stanleyEndDateTime = deadline.getStanleyPlayoffDeadline(date);
		regularSeasonEndDateTime = deadline.getRegularSeasonDeadline(date);
		tradeDeadLine = deadline.getTradeDeadline(date);

		IAdvance advance = new Advance();
		try {
			firstRound = advance.getAdvanceDate(stanleyDate, 19);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			secondRound = advance.getAdvanceDate(firstRound, 10);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			semiFinalRound = advance.getAdvanceDate(secondRound, 5);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		endYear = parse.stringToYear(stanleyDate);
		firstRoundEnd = parse.stringToDate(firstRound);
		secondRoundEnd = parse.stringToDate(secondRound);
		semiFinalsEnd = parse.stringToDate(semiFinalRound);
		finalsEnd = stanleyEndDateTime;

		IPrintToConsole console = new PrintToConsole();
		String message = "********** Simulation started **********";
		console.print(message);

		// on or before stanley playoff end date
		HashMap<String, HashMap<Team, Team>> playoffSchedule = new HashMap<>();
		IPlayoffSchedule playoff = new PlayoffSchedule(league);
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		Team winner;
		while (dateTime.compareTo(stanleyEndDateTime) <= 0) {
			try {
				date = advance.getAdvanceDate(date, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dateTime = parse.stringToDate(date);

			int daysDifference = (int) ((parse.stringToDate(date).getTime() - parse.stringToDate(startDate).getTime())/ (24 * 60 * 60 * 1000));
			GameplayConfig gameplayConfig  = league.getGamePlayConfig();
			int trainingDays = gameplayConfig.getTraining().getDaysUntilStatIncreaseCheck();
			if(daysDifference > trainingDays) {
				TrainingPlayer trainingPlayer = new TrainingPlayer();
				trainingPlayer.trainPlayer(league);
			}

			// on or before regular season end date
			if (dateTime.compareTo(regularSeasonEndDateTime) <= 0) {
				ICheckAndSimulateTodaySchedule simulateToday = new CheckAndSimulateTodaySchedule(schedule, league);
				simulateToday.CheckAndSimulateToday(date);
			}

			// on the last day regular season
			if (dateTime.equals(regularSeasonEndDateTime)) {
				message = "********** Regular season ended **********";
				console.print(message);
				message = "\n********** Generating Playoff schedule **********";
				console.print(message);
				playoffSchedule = playoff.generatePlayoffScheduleRound1(stanleyDate);
			}

			// on and after stanley playoff begin date
			if (dateTime.compareTo(stanleyStartDateTime) >= 0) {

				// on or before 1st playoff end date
				if (dateTime.compareTo(firstRoundEnd) <= 0) {
					ICheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, league);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}

				// on the last day 1st playoff round
				if (dateTime.equals(firstRoundEnd)) {
					playoffSchedule = playoff.generatePlayoffScheduleRemainingRounds(date);
				}

				// on or before 2nd playoff end date
				if ((dateTime.compareTo(secondRoundEnd) <= 0) && (dateTime.compareTo(firstRoundEnd) >= 0)) {
					ICheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, league);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}

				// on the last day 2nd playoff round
				if (dateTime.equals(secondRoundEnd)) {
					playoffSchedule = playoff.generatePlayoffScheduleRemainingRounds(date);
				}

				// on or before semi finals end date
				if ((dateTime.compareTo(semiFinalsEnd) <= 0) && (dateTime.compareTo(secondRoundEnd) >= 0)) {
					ICheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, league);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}

				// on the last day semi finals playoff round
				if (dateTime.equals(semiFinalsEnd)) {
					playoffSchedule = playoff.generatePlayoffScheduleRemainingRounds(date);

				}

				// on or before finals end date
				if ((dateTime.compareTo(finalsEnd) <= 0) && (dateTime.compareTo(semiFinalsEnd) >= 0)) {
					ICheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, league);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}

			}

			// on or before trade deadline
			if (dateTime.compareTo(tradeDeadLine) <= 0) {
				AITrading aiTrading = new AITrading(league);
				aiTrading.generateTradeOffers();
			}

			AgePlayer ageplayer = new AgePlayer();
			ageplayer.increaseAge(league, 1);

			// on the last day of stanley playoff
			if ((dateTime.equals(stanleyEndDateTime)) || (qualifiedTeams.size() == 1)) {
				int year = parse.stringToYear(date);
				String advanced = "29/09/" + Integer.toString(year);
				Date advancedDate = parse.stringToDate(advanced);
				int daysBetween = (int) ((advancedDate.getTime() - dateTime.getTime()) / (24 * 60 * 60 * 1000));
				winner = qualifiedTeams.get(0);
				qualifiedTeams.remove(winner);
				message = "\n********** Winner team of the season(" + startYear + "/" + endYear + ") is "
						+ winner.getTeamName() + " **********";
				console.print(message);
				date = advance.getAdvanceDate(date, daysBetween);
				league.setStartDate(date);

				ageplayer.increaseAge(league, daysBetween);

				league.insertLeagueObject(league, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
				break;
			}

			// league.insertLeagueObject(league, leagueDb, gameplayConfigDb, playerDb,
			// coachDb, managerDb);

		}
		return date;
	}

}
