/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import java.util.HashMap;
import java.util.List;

import group11.Hockey.db.League.ILeagueDb;

public interface ILeague {
	public String getLeagueName();

	public String getStartDate();

	public List<? extends IPlayer> getFreeAgents();

	public List<IConference> getConferences();

	public List<Player> getRetiredPlayers();

	public void setRetiredPlayers(List<Player> players);

	public IGameplayConfig getGamePlayConfig();

	public void setStartDate(String date);

	public List<Team> getQualifiedTeams();

	public boolean insertLeagueObject(ILeague league, ILeagueDb leagueDb);

	public List<ICoach> getCoaches();

	public List<IGeneralManager> getGeneralManagers();

	public ITimeLine getTimeLine();

	public void setTimeLine(ITimeLine timeLine);

	public HashMap<String, HashMap<Team, Team>> getSchedule();

	public void setSchedule(HashMap<String, HashMap<Team, Team>> schedule);

	public void setQualifiedTeams(List<Team> qualifiedTeams);

	public int getGoalsInSeason();

	public void setGoalsInSeason(int goalsInSeason);

	public int getPenaltiesInSeason();

	public void setPenaltiesInSeason(int penaltiesInSeason);

	public int getSavesInSeason();

	public void setSavesInSeason(int savesInSeason);

	public int getGamesInSeason();

	public void setGamesInSeason(int gamesInSeason);

	public void setFreeAgents(List<Player> player);

	public void setCoaches(List<ICoach> coach);

	public void setGeneralManagers(List<IGeneralManager> generalManagers);

	public void setConferences(List<IConference> confList);

	public void setGamePlayConfig(IGameplayConfig gameConfig);
}
