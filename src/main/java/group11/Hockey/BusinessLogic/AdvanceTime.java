/*
 * Author: RajKumar B00849566
 */

package group11.Hockey.BusinessLogic;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleContext;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class AdvanceTime extends StateMachineState {
	private ILeague league;
	private ILeagueDb leagueDb;
	 IDisplay display;

	public AdvanceTime(ILeague league, ILeagueDb leagueDb,  IDisplay display) {
		super();
		this.league = league;
		this.leagueDb = leagueDb;
		this.display = display;
	}

	@Override
	public StateMachineState startState() {
		Logger logger = LogManager.getLogger(AdvanceTime.class);
		logger.info("Entered AdvanceTime.java");
		ITimeLine timeLine = league.getTimeLine();
		IParse parse = DefaultHockeyFactory.makeParse();
		IAdvance advance = DefaultHockeyFactory.makeAdvance();

		String currentDate = timeLine.getCurrentDate();
		Date regularSeasonEndDateTime = timeLine.getRegularSeasonEndDateTime();
		Date firstRoundEnd = timeLine.getFirstRoundEnd();
		Date secondRoundEnd = timeLine.getSecondRoundEnd();
		Date semiFinalsEnd = timeLine.getSemiFinalsEnd();

		currentDate = advance.getAdvanceDate(currentDate, 1);
		timeLine.setCurrentDate(currentDate);

		if (parse.stringToDate(currentDate).equals(regularSeasonEndDateTime)) {
			logger.info("Date is regular season end date");
			String message = "********** Regular season ended **********";
			System.out.println(message);
			message = "\n********** Generating Playoff schedule **********";
			System.out.println(message);
			IScheduleContext scheduleContext = DefaultHockeyFactory
					.makeScheduleContext(DefaultHockeyFactory.makePlayoffSchedule());
			return scheduleContext.executeStrategy(league, leagueDb);
		} else if (parse.stringToDate(currentDate).equals(firstRoundEnd)
				|| parse.stringToDate(currentDate).equals(secondRoundEnd)
				|| parse.stringToDate(currentDate).equals(semiFinalsEnd)) {
			logger.info("Date is not regular season end date but some date in stanley playoffs");
			IScheduleContext scheduleContext = DefaultHockeyFactory
					.makeScheduleContext(DefaultHockeyFactory.makePlayoffScheduleFinalRounds(display));
			return scheduleContext.executeStrategy(league, leagueDb);
		} else {
			logger.info("Date is neither regular season end date nor stanley playoffs date");
			return DefaultHockeyFactory.makeTrainingPlayer(league, leagueDb, display);
		}
	}

}
