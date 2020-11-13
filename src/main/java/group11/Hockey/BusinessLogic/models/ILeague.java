package group11.Hockey.BusinessLogic.models;

import java.util.HashMap;
import java.util.List;

import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.League.ILeagueDb;

public interface ILeague {
	public String getLeagueName();

	public String getStartDate();

	public List<Player> getFreeAgents();

	public List<Conference> getConferences();

	public List<Player> getRetiredPlayers();

	public void setRetiredPlayers(List<Player> players);

	public GameplayConfig getGamePlayConfig();

	public void setStartDate(String date);

	public List<Team> getQualifiedTeams();

	public boolean insertLeagueObject(ILeague league, ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb,
			IPlayerDb playerDb, ICoachDb coachDb, IManagerDb managerDb);

	public List<Coach> getCoaches();

	public List<GeneralManager> getGeneralManagers();

	public ITimeLine getTimeLine();

	public void setTimeLine(ITimeLine timeLine);

	public HashMap<String, HashMap<Team, Team>> getSchedule();

	public void setSchedule(HashMap<String, HashMap<Team, Team>> schedule);

	public void setQualifiedTeams(List<Team> qualifiedTeams);
}
