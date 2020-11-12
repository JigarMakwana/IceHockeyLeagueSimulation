package group11.Hockey.models;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.Advance;
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

public class InitializeSeason implements IInitializeSeason {

	private ILeagueDb leagueDb;
	private IGameplayConfigDb gameplayConfigDb;
	private IPlayerDb playerDb;
	private ICoachDb coachDb;
	private IManagerDb managerDb;
	private League league;

	public InitializeSeason(League league, ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb, IPlayerDb playerDb,
			ICoachDb coachDb, IManagerDb managerDb) {
		super();
		this.league = league;
		this.leagueDb = leagueDb;
		this.gameplayConfigDb = gameplayConfigDb;
		this.playerDb = playerDb;
		this.coachDb = coachDb;
		this.managerDb = managerDb;
	}

	@Override
	public String startSeasons(int seasonCount) {
		String startDate, lastSimulatedDate = league.getStartDate();
		int year;
		if ((lastSimulatedDate == null) || (lastSimulatedDate.isEmpty())) {
			year = Calendar.getInstance().get(Calendar.YEAR);
			startDate = "29/09/" + Integer.toString(year);
		} else {
			IParse parse = new Parse();
			year = parse.stringToYear(lastSimulatedDate);
			startDate = lastSimulatedDate;
		}

		int count = seasonCount;
		String seasonEndDate = null, message;
		IPrintToConsole console = new PrintToConsole();
		IAdvance advance = new Advance();
		while (count > 0) {
			// startDate = advance.getAdvanceDate(startDate, 1);
			league.setStartDate(startDate);
			message = "Start date : " + startDate;
			console.print(message);
			league.setStartDate(startDate);

			ISchedule regularSeasonSchedule = new Schedule(league);
			HashMap<String, HashMap<Team, Team>> schedule = null;

			IParse parse = new Parse();
			IDeadlines deadline = new Deadlines();

			String advanceDate = startDate;

			schedule = regularSeasonSchedule.getSeasonSchedule(startDate);

			Date stanleyEndDateTime = deadline.getStanleyPlayoffDeadline(startDate);
			Date regularSeasonEndDateTime = deadline.getRegularSeasonDeadline(startDate);
			Date stanleyStartDateTime = deadline.getStanleyPlayoffBeginDate(startDate);
			String stanleyDate = parse.dateToString(stanleyStartDateTime);
			IPlayoffSchedule playoff = new PlayoffSchedule(league);
			String firstRound = advance.getAdvanceDate(stanleyDate, 19);
			String secondRound = advance.getAdvanceDate(firstRound, 10);
			String semiFinalRound = advance.getAdvanceDate(secondRound, 5);
			Date firstRoundEnd = parse.stringToDate(firstRound);
			Date secondRoundEnd = parse.stringToDate(secondRound);
			Date semiFinalsEnd = parse.stringToDate(semiFinalRound);

			while (parse.stringToDate(advanceDate).compareTo(stanleyEndDateTime) <= 0) {
				advanceDate = advance.getAdvanceDate(advanceDate, 1);

				if (parse.stringToDate(advanceDate).equals(regularSeasonEndDateTime)) {
					message = "********** Regular season ended **********";
					console.print(message);
					message = "\n********** Generating Playoff schedule **********";
					console.print(message);
					schedule = playoff.generatePlayoffScheduleRound1(stanleyDate);
				}

				if (parse.stringToDate(advanceDate).equals(firstRoundEnd)
						|| parse.stringToDate(advanceDate).equals(secondRoundEnd)
						|| parse.stringToDate(advanceDate).equals(semiFinalsEnd)) {
					schedule = playoff.generatePlayoffScheduleRemainingRounds(advanceDate);
				}

				ISimulateSeason simulateSeason = new SimulateSeason(schedule, league, leagueDb, gameplayConfigDb,
						playerDb, coachDb, managerDb);
				boolean isSeasonEnd = simulateSeason.StartSimulatingSeason(startDate, advanceDate);
				if (isSeasonEnd) {
					break;
				}
			}
			startDate = advanceDate;
			year++;
			count--;
		}
		return seasonEndDate;
	}

}
