package group11.Hockey.BusinessLogic.models;

import java.util.*;

import group11.Hockey.BusinessLogic.Enums.PlayerDraft;
import group11.Hockey.db.League.ILeagueDb;

/**
 * This class contains the business logic for the League model
 *
 * @author jatinpartaprana
 *
 */
public class League implements ILeague {
	private String leagueName;
	private List<Conference> conferences = new ArrayList<Conference>();
	private List<Player> freeAgents = new ArrayList<Player>();
	private IGameplayConfig gamePlayConfig = new GameplayConfig();
	private List<Coach> coaches = new ArrayList<Coach>();
	private List<GeneralManager> generalManagers;
	private List<Player> retiredPlayers = new ArrayList<Player>();
	private List<Team> qualifiedTeams = new ArrayList<Team>();
	private String startDate;
	private ITimeLine timeLine;
	private HashMap<String, HashMap<Team, Team>> schedule;
	private int goalsInSeason;
	private int penaltiesInSeason;
	private int savesInSeason;
	private int gamesInSeason;
	private List<Map<Team, Map<Team, List<Boolean>>>> draftTradeTracker;

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

	public League(String leagueName, List<Conference> conferences, List<? extends IPlayer> freeAgents,
			GameplayConfig gamePlayConfig, List<Coach> coaches, List<GeneralManager> generalManagers) {
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
	public List<Conference> getConferences() {
		return conferences;
	}

	/**
	 * @param conferences the conferences to set
	 */
	public void setConferences(List<Conference> conferences) {
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

	public void setGamePlayConfig(GameplayConfig gamePlayConfig) {
		this.gamePlayConfig = gamePlayConfig;
	}

	public List<Coach> getCoaches() {
		return coaches;
	}

	public void setCoaches(List<Coach> coaches) {
		this.coaches = coaches;
	}

	public List<GeneralManager> getGeneralManagers() {
		return generalManagers;
	}

	public void setGeneralManagers(List<GeneralManager> generalManagers) {
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
		try {
			leagueObjectInserted = leagueDb.insertLeagueInDb(league);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return leagueObjectInserted;

	}

	public ILeague loadLeague(ILeagueDb leagueDb) {
		ILeague league;
		league = leagueDb.loadLeague();
		return league;

	}

	public List<Map<Team, Map<Team, List<Boolean>>>> getDraftTradeTracker() {
		return draftTradeTracker;
	}

	public void setDraftTradeTracker(Team offeringTeam, Team requestedTeam, int draftRound) {
		if(null == draftTradeTracker){
			addToOuterMap(offeringTeam, addToInnerMap(requestedTeam, draftRound));
		} else {
			for (Map<Team, Map<Team, List<Boolean>>> map : draftTradeTracker) {
				for(Team key: map.keySet()){
					if (offeringTeam.equals(key)) {
						for (Map<Team, List<Boolean>> innerMap : map.values()) {
							for(Team key2: map.keySet()){
								if (requestedTeam.equals(key2)) {
									Map.Entry<Team, List<Boolean>> entry = innerMap.entrySet().iterator().next();
									List<Boolean> roundTracker = entry.getValue();
									roundTracker.set(draftRound,true);
									break;
								}
								addToInnerMap(requestedTeam, draftRound);
							}
						}
					}
				}
				addToOuterMap(offeringTeam, addToInnerMap(requestedTeam, draftRound));
			}
		}
	}

	private Map<Team, List<Boolean>> addToInnerMap(Team requestedTeam, int draftRound){
		List<Boolean> roundTracker = new ArrayList<>(Collections.nCopies(PlayerDraft.PLAYER_DRAFT_ROUNDS.getNumVal(), false));
		roundTracker.set(draftRound,true);
		Map<Team, List<Boolean>> tradeDetail = new HashMap<>();
		tradeDetail.put(requestedTeam,roundTracker);
		return tradeDetail;
	}
	private void addToOuterMap(Team offeringTeam, Map<Team, List<Boolean>> tradeDetail){
		Map<Team, Map<Team, List<Boolean>>> entry = new HashMap<>();
		entry.put(offeringTeam,tradeDetail);
		draftTradeTracker.add(entry);
	}
}
