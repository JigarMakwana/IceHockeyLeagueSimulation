// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class InitializeSeason extends StateMachineState {

	private ILeague league;
	private ILeagueDb leagueDb;
	private IDisplay display;
	private ICommandLineInput commandLineInput;
	private IValidations validation;
	private static Logger logger = LogManager.getLogger(InitializeSeason.class);

	public InitializeSeason(ILeague league, ILeagueDb leagueDb, IDisplay display, ICommandLineInput commandLineInput, IValidations validation
	) {
		this.league = league;
		this.leagueDb = leagueDb;
		this.display= display;
		this.commandLineInput = commandLineInput;
		this.validation = validation;
	}

	@Override
	public StateMachineState startState() {
		logger.debug("Entered startState()");
		ITimeLine timeLine =DefaultHockeyFactory.makeTimeLine();
		IParse parse = DefaultHockeyFactory.makeParse();
		IAdvance advance = DefaultHockeyFactory.makeAdvance();

		String message;		
		String startDate = league.getStartDate();
		String lastSimulatedDate = league.getStartDate();
		int year;
		
		if ((lastSimulatedDate == null) || (lastSimulatedDate.isEmpty())) {
			year = Calendar.getInstance().get(Calendar.YEAR);
			startDate = "29/09/" + Integer.toString(year);
		} else {
			year = parse.stringToYear(lastSimulatedDate);
			startDate = lastSimulatedDate;
		}
		timeLine.setStartDate(startDate);
		timeLine.setLastSimulatedDate(lastSimulatedDate);

		league.setStartDate(startDate);
		message = "Start date : " + startDate;
		logger.info(message);
		league.setStartDate(startDate);
		ISchedule regularSeasonSchedule = DefaultHockeyFactory.makeSchedule(league);
		HashMap<String, HashMap<ITeam, ITeam>> schedule = null;

		IDeadlines deadline = DefaultHockeyFactory.makeDeadlines();
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

		return DefaultHockeyFactory.makeAdvanceTime(league, leagueDb, display, commandLineInput, validation);
	}

}
