/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import group11.Hockey.db.League.ILeagueDb;

public interface ILeague {
	public String getLeagueName();

	public String getStartDate();

	public List<? extends IPlayer> getFreeAgents();

	public List<IConference> getConferences();

	public List<IPlayer> getRetiredPlayers();

	public void setRetiredPlayers(List<IPlayer> players);

	public IGameplayConfig getGamePlayConfig();

	public void setStartDate(String date);

	public List<ITeam> getQualifiedTeams();

	public boolean insertLeagueObject(ILeague league, ILeagueDb leagueDb);

	public List<ICoach> getCoaches();

	public List<IGeneralManager> getGeneralManagers();

	public ITimeLine getTimeLine();

	public void setTimeLine(ITimeLine timeLine);

	public HashMap<String, HashMap<ITeam, ITeam>> getSchedule();

	public void setSchedule(HashMap<String, HashMap<ITeam, ITeam>> schedule);

	public void setQualifiedTeams(List<ITeam> qualifiedTeams);

	public int getGoalsInSeason();

	public void setGoalsInSeason(int goalsInSeason);

	public int getPenaltiesInSeason();

	public void setPenaltiesInSeason(int penaltiesInSeason);

	public int getSavesInSeason();

	public void setSavesInSeason(int savesInSeason);

	public int getGamesInSeason();

	public void setGamesInSeason(int gamesInSeason);

	List<Map<ITeam, Map<ITeam, List<Boolean>>>> getDraftTradeTracker();

	void setDraftTradeTracker(ITeam offeringTeam, ITeam requestedTeam, int draftRound);

	public void setFreeAgents(List<Player> player);

	public void setCoaches(List<ICoach> coach);

	public void setGeneralManagers(List<IGeneralManager> generalManagers);

	public void setConferences(List<IConference> confList);

	public void setGamePlayConfig(IGameplayConfig gameConfig);
}
