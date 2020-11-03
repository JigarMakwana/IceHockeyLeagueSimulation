package group11.Hockey.db.Player;

import java.util.List;

import group11.Hockey.BusinessLogic.models.Player;

public interface IPlayerDb {

	public List<Player> loadFreeAgents(String leagueName);
}
