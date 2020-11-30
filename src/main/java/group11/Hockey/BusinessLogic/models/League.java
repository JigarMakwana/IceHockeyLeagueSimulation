package group11.Hockey.BusinessLogic.models;

import java.util.*;

import group11.Hockey.BusinessLogic.Enums.PlayerDraft;
import group11.Hockey.BusinessLogic.models.Roster.Roster;
import group11.Hockey.db.League.ILeagueDb;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class contains the business logic for the League model
 *
 *
 */
public class League implements ILeague {
	private String leagueName;
	private List<IConference> conferences = new ArrayList<>();
	private List<Player> freeAgents = new ArrayList<>();
	private IGameplayConfig gamePlayConfig = new GameplayConfig();
	private List<ICoach> coaches = new ArrayList<>();
	private List<IGeneralManager> generalManagers;
	private List<IPlayer> retiredPlayers = new ArrayList<>();
	private List<ITeam> qualifiedTeams = new ArrayList<>();
	private List<ITeam> presidentTeams = new ArrayList<>();
	private List<IPlayer> calderPlayers = new ArrayList<>();
	private List<IPlayer> venizaPlayers = new ArrayList<>();
	private List<ICoach> jackAdamsCoaches = new ArrayList<>();
	private List<IPlayer> mauriceRichardPlayers = new ArrayList<>();
	private List<IPlayer> robHawkeyPlayers = new ArrayList<>();
	private List<ITeam> participationTeams = new ArrayList<>();
	private String startDate;
	private ITimeLine timeLine;
	private HashMap<String, HashMap<ITeam, ITeam>> schedule;
	private int goalsInSeason;
	private int penaltiesInSeason;
	private int savesInSeason;
	private int gamesInSeason;
	private List<Map<ITeam, Map<ITeam, List<Boolean>>>> draftTradeTracker;
	private static Logger logger = LogManager.getLogger(League.class);

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
		this.draftTradeTracker = new ArrayList<>();
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

	public HashMap<String, HashMap<ITeam, ITeam>> getSchedule() {
		return schedule;
	}

	public void setSchedule(HashMap<String, HashMap<ITeam, ITeam>> schedule) {
		this.schedule = schedule;
	}

	public List<ITeam> getQualifiedTeams() {
		return qualifiedTeams;
	}

	public void setQualifiedTeams(List<ITeam> qualifiedTeams) {
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

	public List<IPlayer> getRetiredPlayers() {
		return retiredPlayers;
	}

	public void setRetiredPlayers(List<IPlayer> retiredPlayers) {
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
		return freeAgents.size() > 0;
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

	/**
	 * @author  Jigar Makwana B00842568
	 */
	public List<Map<ITeam, Map<ITeam, List<Boolean>>>> getDraftTradeTracker() {
		return this.draftTradeTracker;
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	private Map<ITeam, List<Boolean>> addToInnerMap(ITeam requestedTeam, int draftRound){
		logger.debug("Entered addToInnerMap()");
		List<Boolean> roundTracker = new ArrayList<>(Collections.nCopies(PlayerDraft.PLAYER_DRAFT_ROUNDS.getNumVal(), false));
		roundTracker.set(draftRound,true);
		Map<ITeam, List<Boolean>> tradeDetail = new HashMap<>();
		tradeDetail.put(requestedTeam,roundTracker);
		return tradeDetail;
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	private void addToOuterMap(ITeam offeringTeam, Map<ITeam, List<Boolean>> tradeDetail){
		logger.debug("Entered addToOuterMap()");
		Map<ITeam, Map<ITeam, List<Boolean>>> entry = new HashMap<>();
		entry.put(offeringTeam,tradeDetail);
		draftTradeTracker.add(entry);
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	public void setDraftTradeTracker(ITeam offeringTeam, ITeam requestedTeam, int draftRound) {
		logger.debug("Entered setDraftTradeTracker()");
		if(null == draftTradeTracker){
			addToOuterMap(offeringTeam, addToInnerMap(requestedTeam, draftRound));
		} else {
			for (Map<ITeam, Map<ITeam, List<Boolean>>> map : draftTradeTracker) {
				for(ITeam key: map.keySet()){
					if (offeringTeam.equals(key)) {
						for (Map<ITeam, List<Boolean>> innerMap : map.values()) {
							for(ITeam key2: map.keySet()){
								if (requestedTeam.equals(key2)) {
									Map.Entry<ITeam, List<Boolean>> entry = innerMap.entrySet().iterator().next();
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

	public List<ITeam> getPresidentTeams() {
		return presidentTeams;
	}

	public void setPresidentTeams(List<ITeam> presidentTeams) {
		this.presidentTeams = presidentTeams;
	}

	public List<IPlayer> getCalderPlayers() {
		return calderPlayers;
	}

	public void setCalderPlayers(List<IPlayer> calderPlayers) {
		this.calderPlayers = calderPlayers;
	}

	public List<IPlayer> getVenizaPlayers() {
		return venizaPlayers;
	}

	public void setVenizaTeams(List<IPlayer> venizaPlayers) {
		this.venizaPlayers = venizaPlayers;
	}

	public List<ICoach> getJackAdamsCoaches() {
		return jackAdamsCoaches;
	}

	public void setJackAdamsCoaches(List<ICoach> jackAdamsCoaches) {
		this.jackAdamsCoaches = jackAdamsCoaches;
	}

	public List<IPlayer> getMauriceRichardPlayers() {
		return mauriceRichardPlayers;
	}

	public void setMauriceRichardPlayers(List<IPlayer> mauriceRichardPlayers) {
		this.mauriceRichardPlayers = mauriceRichardPlayers;
	}

	public List<IPlayer> getRobHawkeyPlayers() {
		return robHawkeyPlayers;
	}

	public void setRobHawkeyPlayers(List<IPlayer> robHawkeyPlayers) {
		this.robHawkeyPlayers = robHawkeyPlayers;
	}

	public List<ITeam> getParticipationTeams() {
		return participationTeams;
	}

	public void setParticipationTeams(List<ITeam> participationTeams) {
		this.participationTeams = participationTeams;
	}

}
