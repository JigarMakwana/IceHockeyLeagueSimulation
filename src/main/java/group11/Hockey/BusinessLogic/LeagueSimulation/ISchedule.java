package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.ITeam;

public interface ISchedule {
	HashMap<String, HashMap<ITeam,ITeam>> getSeasonSchedule() ;
}
