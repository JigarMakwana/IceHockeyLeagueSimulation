package group11.Hockey.BusinessLogic;

import java.util.Date;

import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.models.IParse;
import group11.Hockey.models.Parse;
import group11.Hockey.models.PlayoffSchedule;

public class AdvanceTime extends StateMachineState {
	private ILeague league;

	public AdvanceTime(ILeague league) {
		super();
		this.league = league;
	}

	@Override
	public StateMachineState startState() {
		ITimeLine timeLine = league.getTimeLine();
		IParse parse = new Parse();
		IAdvance advance = new Advance();

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
			IScheduleStrategy scheduleStrategy = new PlayoffSchedule(league);
			return new ScheduleContext(scheduleStrategy, league);
		} else if (parse.stringToDate(currentDate).equals(firstRoundEnd)
				|| parse.stringToDate(currentDate).equals(secondRoundEnd)
				|| parse.stringToDate(currentDate).equals(semiFinalsEnd)) {

			IScheduleStrategy scheduleStrategy = new PlayoffScheduleFinalRounds(league);
			return new ScheduleContext(scheduleStrategy, league);
		} else {
			return new TrainingPlayer(league);
		}
	}

}
