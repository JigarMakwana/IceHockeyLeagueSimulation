package group11.Hockey.models;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import group11.Hockey.BusinessLogic.AdvanceTime;
import group11.Hockey.BusinessLogic.IState;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.TimeLine;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;
import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.League.ILeagueDb;

public class InitializeSeason extends StateMachineState {

	private ILeagueDb leagueDb;
	private IGameplayConfigDb gameplayConfigDb;
	private IPlayerDb playerDb;
	private ICoachDb coachDb;
	private IManagerDb managerDb;
	private ILeague league;
	private int seasonCount;

	public InitializeSeason(League league, ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb, IPlayerDb playerDb,
			ICoachDb coachDb, IManagerDb managerDb, int seasonCount) {
		super();
		this.league = league;
		this.leagueDb = leagueDb;
		this.gameplayConfigDb = gameplayConfigDb;
		this.playerDb = playerDb;
		this.coachDb = coachDb;
		this.managerDb = managerDb;
		this.seasonCount = seasonCount;
	}

	public InitializeSeason(ILeague league) {
		this.league = league;
	}

	@Override
	public StateMachineState startState() {
		ITimeLine timeLine = new TimeLine();

		String startDate = league.getStartDate();
		String lastSimulatedDate = league.getStartDate();

		int year;
		if ((lastSimulatedDate == null) || (lastSimulatedDate.isEmpty())) {
			year = Calendar.getInstance().get(Calendar.YEAR);
			startDate = "29/09/" + Integer.toString(year);
		} else {
			IParse parse = new Parse();
			year = parse.stringToYear(lastSimulatedDate);
			startDate = lastSimulatedDate;
		}
		timeLine.setStartDate(startDate);
		timeLine.setLastSimulatedDate(lastSimulatedDate);

		String message;
		IPrintToConsole console = new PrintToConsole();
		IAdvance advance = new Advance();

		league.setStartDate(startDate);
		message = "Start date : " + startDate;
		console.print(message);
		league.setStartDate(startDate);

		ISchedule regularSeasonSchedule = new Schedule(league);
		HashMap<String, HashMap<Team, Team>> schedule = null;

		IParse parse = new Parse();
		IDeadlines deadline = new Deadlines();

		String currentDate = startDate;

		Date stanleyEndDateTime = deadline.getStanleyPlayoffDeadline(startDate);
		Date regularSeasonEndDateTime = deadline.getRegularSeasonDeadline(startDate);
		Date stanleyStartDateTime = deadline.getStanleyPlayoffBeginDate(startDate);
		Date tradeDeadLine = deadline.getTradeDeadline(startDate);
		String stanleyDate = parse.dateToString(stanleyStartDateTime);
		String firstRound = advance.getAdvanceDate(stanleyDate, 19);
		String secondRound = advance.getAdvanceDate(firstRound, 10);
		String semiFinalRound = advance.getAdvanceDate(secondRound, 5);
		Date firstRoundEnd = parse.stringToDate(firstRound);
		Date secondRoundEnd = parse.stringToDate(secondRound);
		Date semiFinalsEnd = parse.stringToDate(semiFinalRound);

		timeLine.setStanleyEndDateTime(stanleyEndDateTime);
		timeLine.setRegularSeasonEndDateTime(regularSeasonEndDateTime);
		timeLine.setStanleyDate(stanleyDate);
		timeLine.setFirstRoundEnd(firstRoundEnd);
		timeLine.setSecondRoundEnd(secondRoundEnd);
		timeLine.setSemiFinalsEnd(semiFinalsEnd);
		timeLine.setCurrentDate(currentDate);
		timeLine.setTradeDeadLine(tradeDeadLine);

		league.setTimeLine(timeLine);
		schedule = regularSeasonSchedule.getSeasonSchedule();
		league.setSchedule(schedule);

		return new AdvanceTime(league);
	}

}
