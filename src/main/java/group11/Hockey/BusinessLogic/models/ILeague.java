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

	public List<Conference> getConferences();

	public List<Player> getRetiredPlayers();

	public void setRetiredPlayers(List<Player> players);

	public IGameplayConfig getGamePlayConfig();

	public void setStartDate(String date);

	public List<Team> getQualifiedTeams();

	public boolean insertLeagueObject(ILeague league, ILeagueDb leagueDb);

	public List<Coach> getCoaches();

	public List<GeneralManager> getGeneralManagers();

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

	List<Map<Team, Map<Team, List<Boolean>>>> getDraftTradeTracker();

	void setDraftTradeTracker(Team offeringTeam, Team requestedTeam, int draftRound);
}
