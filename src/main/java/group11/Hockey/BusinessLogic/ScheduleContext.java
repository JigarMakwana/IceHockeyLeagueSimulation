package group11.Hockey.BusinessLogic;

import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Team;

public class ScheduleContext extends StateMachineState {
	private IScheduleStrategy scheduleStrategy;
	private ILeague league;

	public ScheduleContext(IScheduleStrategy scheduleStrategy, ILeague league) {
		super();
		this.scheduleStrategy = scheduleStrategy;
		this.league = league;
	}

	@Override
	public StateMachineState startState() {
		HashMap<String, HashMap<Team, Team>> schedule = scheduleStrategy.getSchedule();
		league.setSchedule(schedule);
		return new TrainingPlayer(league);
	}

}
