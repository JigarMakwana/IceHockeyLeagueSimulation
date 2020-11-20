package group11.Hockey.BusinessLogic;

import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.Team;

public interface IScheduleStrategy {
	public HashMap<String, HashMap<Team, Team>> getSchedule();
}
