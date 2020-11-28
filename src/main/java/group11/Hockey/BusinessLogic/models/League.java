package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.db.League.ILeagueDb;

/**
 * This class contains the business logic for the League model
 *
 * @author jatinpartaprana
 *
 */
public class League implements ILeague {
	private String leagueName;
	private List<IConference> conferences = new ArrayList<>();
	private List<Player> freeAgents = new ArrayList<Player>();
	private IGameplayConfig gamePlayConfig = new GameplayConfig();
	private List<ICoach> coaches = new ArrayList<>();
	private List<IGeneralManager> generalManagers;
	private List<Player> retiredPlayers = new ArrayList<Player>();
	private List<Team> qualifiedTeams = new ArrayList<Team>();
	private String startDate;
	private ITimeLine timeLine;
	private HashMap<String, HashMap<Team, Team>> schedule;
	private int goalsInSeason;
	private int penaltiesInSeason;
	private int savesInSeason;
	private int gamesInSeason;

	public int getGoalsInSeason() {
		return goalsInSeason;
	}

	public void setGoalsInSeason(int goalsInSeason) {
		this.goalsInSeason = goalsInSeason;
	}

	public int getPenaltiesInSeason() {
		return penaltiesInSeason;
	}

	public void setPenaltiesInSeason(int penaltiesInSeason) {
		this.penaltiesInSeason = penaltiesInSeason;
	}

	public int getSavesInSeason() {
		return savesInSeason;
	}

	public void setSavesInSeason(int savesInSeason) {
		this.savesInSeason = savesInSeason;
	}

	public int getGamesInSeason() {
		return gamesInSeason;
	}

	public void setGamesInSeason(int gamesInSeason) {
		this.gamesInSeason = gamesInSeason;
	}

	public League(String leagueName, List<IConference> conferences, List<? extends IPlayer> freeAgents,
			IGameplayConfig gamePlayConfig, List<ICoach> coaches, List<IGeneralManager> generalManagers) {
		super();
		this.leagueName = leagueName;
		this.conferences = conferences;
		this.freeAgents = (List<Player>) freeAgents;
		this.gamePlayConfig = gamePlayConfig;
		this.coaches = coaches;
		this.generalManagers = generalManagers;
	}

	public League() {
		super();
	}

	public ITimeLine getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(ITimeLine timeLine) {
		this.timeLine = timeLine;
	}

	public HashMap<String, HashMap<Team, Team>> getSchedule() {
		return schedule;
	}

	public void setSchedule(HashMap<String, HashMap<Team, Team>> schedule) {
		this.schedule = schedule;
	}

	public List<Team> getQualifiedTeams() {
		return qualifiedTeams;
	}

	public void setQualifiedTeams(List<Team> qualifiedTeams) {
		this.qualifiedTeams = qualifiedTeams;
	}

	/**
	 * @return the leagueName
	 */
	public String getLeagueName() {
		return leagueName;
	}

	/**
	 * @param leagueName the leagueName to set
	 */
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	/**
	 * @return the conferences
	 */
	public List<IConference> getConferences() {
		return conferences;
	}

	/**
	 * @param conferences the conferences to set
	 */
	public void setConferences(List<IConference> conferences) {
		this.conferences = conferences;
	}

	/**
	 * @return the freeAgents
	 */
	public List<? extends IPlayer> getFreeAgents() {
		if (isFreeAgentsNotNull()) {
			Collections.sort(freeAgents);
		}

		return freeAgents;
	}

	/**
	 * @param freeAgents the freeAgents to set
	 */
	public void setFreeAgents(List<Player> freeAgents) {
		this.freeAgents = freeAgents;
	}

	public IGameplayConfig getGamePlayConfig() {
		return gamePlayConfig;
	}

	public void setGamePlayConfig(IGameplayConfig gamePlayConfig) {
		this.gamePlayConfig = gamePlayConfig;
	}

	public List<ICoach> getCoaches() {
		return coaches;
	}

	public void setCoaches(List<ICoach> coaches) {
		this.coaches = coaches;
	}

	public List<IGeneralManager> getGeneralManagers() {
		return generalManagers;
	}

	public void setGeneralManagers(List<IGeneralManager> generalManagers) {
		this.generalManagers = generalManagers;
	}

	public List<Player> getRetiredPlayers() {
		return retiredPlayers;
	}

	public void setRetiredPlayers(List<Player> retiredPlayers) {
		this.retiredPlayers = retiredPlayers;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	private boolean isFreeAgentsNotNull() {
		return freeAgents != null;
	}

	public boolean insertLeagueObject(ILeague league, ILeagueDb leagueDb) {
		boolean leagueObjectInserted = false;
		leagueObjectInserted = leagueDb.insertLeagueInDb(league);
		return leagueObjectInserted;

	}

	public ILeague loadLeague(ILeagueDb leagueDb) {
		ILeague league;
		league = leagueDb.loadLeague();
		return league;

	}
}
