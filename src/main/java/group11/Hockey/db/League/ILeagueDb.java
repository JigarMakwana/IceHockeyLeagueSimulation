package group11.Hockey.db.League;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public interface ILeagueDb {
	public boolean checkLeagueNameExitsInDb(String leagueName);

//	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
//			String generalManger, String headcoachName, float skating, float shooting, float checking, float saving,
//			String playerName, String playerPosition, boolean captain, float playerSkating, float playerShooting,
//			float playerChecking, float playerSaving, float age);

	public boolean insertLeagueInDb(League league, String conferenceName, String divisionName, Team team, Coach coach,
			Player player);
}
