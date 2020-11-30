package group11.Hockey.db.Player;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Player;

public interface IPlayerDb {

	public List<Player> loadFreeAgents(String leagueName);

	public boolean insertLeagueFreeAgents(String leagueName, IPlayer freeAgent);

	public boolean insertLeagueRetiredPlayers(String leagueName, IPlayer retiredPlayer);

	public boolean deleteLeaguePlayers(String leagueName);
}
