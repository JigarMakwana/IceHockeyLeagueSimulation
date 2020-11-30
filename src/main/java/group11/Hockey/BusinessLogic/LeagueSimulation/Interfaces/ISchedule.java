// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces;

import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.ITeam;

public interface ISchedule {
	HashMap<String, HashMap<ITeam,ITeam>> getSeasonSchedule() ;
}
