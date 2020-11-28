package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
	private static Logger logger = LogManager.getLogger(League.class);

	public int getGoalsInSeason() {
		logger.info("Entered getGoalsInSeason()");
		return goalsInSeason;
	}

	public void setGoalsInSeason(int goalsInSeason) {
		logger.info("Entered setGoalsInSeason()");
		this.goalsInSeason = goalsInSeason;
	}

	public int getPenaltiesInSeason() {
		logger.info("Entered getPenaltiesInSeason()");
		return penaltiesInSeason;
	}

	public void setPenaltiesInSeason(int penaltiesInSeason) {
		logger.info("Entered setPenaltiesInSeason()");
		this.penaltiesInSeason = penaltiesInSeason;
	}

	public int getSavesInSeason() {
		logger.info("Entered getSavesInSeason()");
		return savesInSeason;
	}

	public void setSavesInSeason(int savesInSeason) {
		logger.info("Entered setSavesInSeason()");
		this.savesInSeason = savesInSeason;
	}

	public int getGamesInSeason() {
		logger.info("Entered getGamesInSeason()");
		return gamesInSeason;
	}

	public void setGamesInSeason(int gamesInSeason) {
		logger.info("Entered setGamesInSeason()");
		this.gamesInSeason = gamesInSeason;
	}

	public League(String leagueName, List<IConference> conferences, List<? extends IPlayer> freeAgents,
			GameplayConfig gamePlayConfig, List<ICoach> coaches, List<IGeneralManager> generalManagers) {
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
		logger.info("Entered getTimeLine()");
		return timeLine;
	}

	public void setTimeLine(ITimeLine timeLine) {
		logger.info("Entered setTimeLine()");
		this.timeLine = timeLine;
	}

	public HashMap<String, HashMap<Team, Team>> getSchedule() {
		logger.info("Entered getSchedule()");
		return schedule;
	}

	public void setSchedule(HashMap<String, HashMap<Team, Team>> schedule) {
		logger.info("Entered setSchedule()");
		this.schedule = schedule;
	}

	public List<Team> getQualifiedTeams() {
		logger.info("Entered getQualifiedTeams()");
		return qualifiedTeams;
	}

	public void setQualifiedTeams(List<Team> qualifiedTeams) {
		logger.info("Entered setQualifiedTeams()");
		this.qualifiedTeams = qualifiedTeams;
	}

	/**
	 * @return the leagueName
	 */
	public String getLeagueName() {
		logger.info("Entered getLeagueName()");
		return leagueName;
	}

	/**
	 * @param leagueName the leagueName to set
	 */
	public void setLeagueName(String leagueName) {
		logger.info("Entered setLeagueName()");
		this.leagueName = leagueName;
	}

	/**
	 * @return the conferences
	 */
	public List<IConference> getConferences() {
		logger.info("Entered getConferences()");
		return conferences;
	}

	/**
	 * @param conferences the conferences to set
	 */
	public void setConferences(List<IConference> conferences) {
		logger.info("Entered setConferences()");
		this.conferences = conferences;
	}

	/**
	 * @return the freeAgents
	 */
	public List<? extends IPlayer> getFreeAgents() {
		logger.info("Entered getFreeAgents()");
		if (isFreeAgentsNotNull()) {
			Collections.sort(freeAgents);
		}

		return freeAgents;
	}

	/**
	 * @param freeAgents the freeAgents to set
	 */
	public void setFreeAgents(List<Player> freeAgents) {
		logger.info("Entered setFreeAgents()");
		this.freeAgents = freeAgents;
	}

	public IGameplayConfig getGamePlayConfig() {
		logger.info("Entered getGamePlayConfig()");
		return gamePlayConfig;
	}

	public void setGamePlayConfig(IGameplayConfig gamePlayConfig) {
		logger.info("Entered setGamePlayConfig()");
		this.gamePlayConfig = gamePlayConfig;
	}

	public List<ICoach> getCoaches() {
		logger.info("Entered getCoaches()");
		return coaches;
	}

	public void setCoaches(List<ICoach> coaches) {
		logger.info("Entered setCoaches()");
		this.coaches = coaches;
	}

	public List<IGeneralManager> getGeneralManagers() {
		logger.info("Entered getGeneralManagers()");
		return generalManagers;
	}

	public void setGeneralManagers(List<IGeneralManager> generalManagers) {
		logger.info("Entered setGeneralManagers()");
		this.generalManagers = generalManagers;
	}

	public List<Player> getRetiredPlayers() {
		logger.info("Entered getRetiredPlayers()");
		return retiredPlayers;
	}

	public void setRetiredPlayers(List<Player> retiredPlayers) {
		logger.info("Entered setRetiredPlayers()");
		this.retiredPlayers = retiredPlayers;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		logger.info("Entered getStartDate()");
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		logger.info("Entered setStartDate()");
		this.startDate = startDate;
	}

	private boolean isFreeAgentsNotNull() {
		logger.info("Entered isFreeAgentsNotNull()");
		return freeAgents != null;
	}

	public boolean insertLeagueObject(ILeague league, ILeagueDb leagueDb) {
		logger.info("Entered insertLeagueObject()");
		boolean leagueObjectInserted = false;
		try {
			leagueObjectInserted = leagueDb.insertLeagueInDb(league);
		} catch (Exception e) {
			logger.warn("Exception caught : "+e);
		}
		return leagueObjectInserted;

	}

	public ILeague loadLeague(ILeagueDb leagueDb) {
		logger.info("Entered loadLeague()");
		ILeague league;
		league = leagueDb.loadLeague();
		return league;

	}
}
