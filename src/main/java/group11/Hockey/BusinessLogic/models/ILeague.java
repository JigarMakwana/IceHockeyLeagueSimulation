package group11.Hockey.BusinessLogic.models;

import java.util.List;

public interface ILeague {
	public String getLeagueName();

	public String getStartDate();

	public List<Player> getFreeAgents();

	public List<Conference> getConferences();

	public List<Player> getRetiredPlayers();

	public void setRetiredPlayers(List<Player> players);

	public GameplayConfig getGamePlayConfig();

	public void setStartDate(String date);
}
