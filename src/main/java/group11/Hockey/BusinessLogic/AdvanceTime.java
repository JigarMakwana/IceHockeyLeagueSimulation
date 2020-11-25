/*
 * Author: RajKumar B00849566
 */

package group11.Hockey.BusinessLogic;

import java.util.Date;

import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.IScheduleContext;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.db.League.ILeagueDb;

public class AdvanceTime extends StateMachineState {
	private ILeague league;
	private ILeagueDb leagueDb;

	public AdvanceTime(ILeague league, ILeagueDb leagueDb) {
		super();
		this.league = league;
		this.leagueDb = leagueDb;
	}

	@Override
	public StateMachineState startState() {
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

			IScheduleContext scheduleContext = DefaultHockeyFactory
					.makeScheduleContext(DefaultHockeyFactory.makePlayoffScheduleFinalRounds());
			return scheduleContext.executeStrategy(league, leagueDb);
		} else {
			return DefaultHockeyFactory.makeTrainingPlayer(league, leagueDb);
		}
	}

}
