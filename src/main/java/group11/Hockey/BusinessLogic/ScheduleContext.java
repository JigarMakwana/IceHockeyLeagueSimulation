package group11.Hockey.BusinessLogic;

import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.db.League.ILeagueDb;

public class ScheduleContext extends StateMachineState {
	private IScheduleStrategy scheduleStrategy;
	private ILeague league;
	private ILeagueDb leagueDb;

	public ScheduleContext(IScheduleStrategy scheduleStrategy, ILeague league, ILeagueDb leagueDb) {
		super();
		this.scheduleStrategy = scheduleStrategy;
		this.league = league;
		this.leagueDb = leagueDb;
	}

	@Override
	public StateMachineState startState() {
		HashMap<String, HashMap<Team, Team>> schedule = scheduleStrategy.getSchedule();
		league.setSchedule(schedule);
		return new TrainingPlayer(league, leagueDb);
	}

}
