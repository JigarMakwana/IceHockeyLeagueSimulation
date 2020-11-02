package group11.Hockey.BusinessLogic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.models.GameplayConfig;
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
		IParse parse = new Parse();
		IDeadlines deadline = new Deadlines();
		IAdvance advance = new Advance();
		IPrintToConsole console = new PrintToConsole();
		HashMap<String, HashMap<Team, Team>> playoffSchedule = new HashMap<>();
		IPlayoffSchedule playoff = new PlayoffSchedule(league);
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		Team winner;
		String startDate = date;
		String stanleyDate;
		String firstRound = null;
		String secondRound = null;
		String semiFinalRound = null;
		Date dateTime;
		Date stanleyStartDateTime;
		Date stanleyEndDateTime;
		Date regularSeasonEndDateTime;
		Date tradeDeadLine;
		Date firstRoundEnd;
		Date secondRoundEnd;
		Date semiFinalsEnd;
		Date finalsEnd;
		int startYear;
		int endYear;
		startYear = parse.stringToYear(date);
		dateTime = parse.stringToDate(date);
		stanleyStartDateTime = deadline.getStanleyPlayoffBeginDate(date);
		stanleyDate = parse.dateToString(stanleyStartDateTime);
		stanleyEndDateTime = deadline.getStanleyPlayoffDeadline(date);
		regularSeasonEndDateTime = deadline.getRegularSeasonDeadline(date);
		tradeDeadLine = deadline.getTradeDeadline(date);
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
		String message = "********** Simulation started **********";
		console.print(message);
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
			if (dateTime.compareTo(regularSeasonEndDateTime) <= 0) {
				ICheckAndSimulateTodaySchedule simulateToday = new CheckAndSimulateTodaySchedule(schedule, league);
				simulateToday.CheckAndSimulateToday(date);
			}
			if (dateTime.equals(regularSeasonEndDateTime)) {
				message = "********** Regular season ended **********";
				console.print(message);
				message = "\n********** Generating Playoff schedule **********";
				console.print(message);
				playoffSchedule = playoff.generatePlayoffScheduleRound1(stanleyDate);
			}
			if (dateTime.compareTo(stanleyStartDateTime) >= 0) {
				if (dateTime.compareTo(firstRoundEnd) <= 0) {
					ICheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, league);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}
				if (dateTime.equals(firstRoundEnd)) {
					playoffSchedule = playoff.generatePlayoffScheduleRemainingRounds(date);
				}
				if ((dateTime.compareTo(secondRoundEnd) <= 0) && (dateTime.compareTo(firstRoundEnd) >= 0)) {
					ICheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, league);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}
				if (dateTime.equals(secondRoundEnd)) {
					playoffSchedule = playoff.generatePlayoffScheduleRemainingRounds(date);
				}
				if ((dateTime.compareTo(semiFinalsEnd) <= 0) && (dateTime.compareTo(secondRoundEnd) >= 0)) {
					ICheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, league);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}
				if (dateTime.equals(semiFinalsEnd)) {
					playoffSchedule = playoff.generatePlayoffScheduleRemainingRounds(date);

				}
				if ((dateTime.compareTo(finalsEnd) <= 0) && (dateTime.compareTo(semiFinalsEnd) >= 0)) {
					ICheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, league);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}
			}
			if (dateTime.compareTo(tradeDeadLine) <= 0) {
				AITrading aiTrading = new AITrading(league);
				//aiTrading.generateTradeOffers();
			}
			AgePlayer ageplayer = new AgePlayer();
			ageplayer.increaseAge(league, 1);
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
		}
		return date;
	}
}
