package group11.Hockey.db.League;

import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public interface ILeagueDb {
	public boolean checkLeagueNameExitsInDb(String leagueName);

//	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
//			String generalManger, String headcoachName, float skating, float shooting, float checking, float saving,
//			String playerName, String playerPosition, boolean captain, float playerSkating, float playerShooting,
//			float playerChecking, float playerSaving, float age);

	public boolean insertLeagueInDb(ILeague league, String conferenceName, String divisionName, ITeam team, ICoach coach,
			IPlayer player);
}
