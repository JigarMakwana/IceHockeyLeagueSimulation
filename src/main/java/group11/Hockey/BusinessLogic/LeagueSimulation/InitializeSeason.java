package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import group11.Hockey.BusinessLogic.AdvanceTime;
import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.TimeLine;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;
import group11.Hockey.db.League.ILeagueDb;

public class InitializeSeason extends StateMachineState {

	private ILeague league;
	private ILeagueDb leagueDb;

	public InitializeSeason(ILeague league, ILeagueDb leagueDb) {
		this.league = league;
		this.leagueDb = leagueDb;
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

		return DefaultHockeyFactory.makeAdvanceTime(league, leagueDb);
	}

}
