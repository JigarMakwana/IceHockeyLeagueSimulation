package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.League.ILeagueDb;

/**
 * This class contains the business logic for the League model
 * 
 * @author jatinpartaprana
 *
 */
public class League {
	private String leagueName;
	private List<Conference> conferences = null;
	private List<Player> freeAgents = new ArrayList<Player>();
	private GameplayConfig gamePlayConfig = null;
	private List<Coach> coaches;
	private List<GeneralManager> generalManagers;
	private List<Player> retiredPlayers;
	private String lastSimulatedDate;
	private List<Team> qualifiedTeams=new ArrayList<Team>();

	public List<Team> getQualifiedTeams() {
		return qualifiedTeams;
	}

	public void setQualifiedTeams(List<Team> qualifiedTeams) {
		this.qualifiedTeams = qualifiedTeams;
	}

	public League(String leagueName, List<Conference> conferences, List<Player> freeAgents,
			GameplayConfig gamePlayConfig, List<Coach> coaches, List<GeneralManager> generalManagers) {
		super();
		this.leagueName = leagueName;
		this.conferences = conferences;
		this.freeAgents = freeAgents;
		this.gamePlayConfig = gamePlayConfig;
		this.coaches = coaches;
		this.generalManagers = generalManagers;
	}

	public League() {
		super();
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
	public List<Player> getFreeAgents() {
		if(isFreeAgentsNotNull()) {
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

	public GameplayConfig getGamePlayConfig() {
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
	
	private boolean isFreeAgentsNotNull() {
		return freeAgents != null;
	}

	public boolean insertLeagueObject(League league, ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb,
			IPlayerDb playerDb, ICoachDb coachDb, IManagerDb managerDb) {
		boolean leagueObjectInserted = false;
		boolean freeAgentInsertionCheck = false;

		Player playerObj = new Player(leagueName, playerDb);
		playerObj.deleteLeaguePlayers();
		
		List<Conference> conferenceList = league.getConferences();
		for (Conference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			if (divisionList == null || divisionList.size() == 0) {
				leagueObjectInserted = leagueDb.insertLeagueInDb(league.getLeagueName(), conference.getConferenceName(),
						null, null, null, null, 0, 0, 0, 0, null, null, false, 0, 0, 0, 0, 0);
			} else {
				for (Division divison : divisionList) {
					List<Team> teamList = divison.getTeams();
					if (teamList == null || teamList.size() == 0) {
						leagueObjectInserted = leagueDb.insertLeagueInDb(league.getLeagueName(),
								conference.getConferenceName(), divison.getDivisionName(), null, null, null, 0, 0, 0, 0,
								null, null, false, 0, 0, 0, 0, 0);
					} else {
						for (Team team : teamList) {
							List<Player> playerList = team.getPlayers();
							Coach coach = team.getHeadCoach();
							if (playerList == null || playerList.size() == 0) {

								leagueObjectInserted = leagueDb.insertLeagueInDb(league.getLeagueName(),
										conference.getConferenceName(), divison.getDivisionName(), team.getTeamName(),
										team.getGeneralManager(), coach.getName(), coach.getSkating(),
										coach.getShooting(), coach.getChecking(), coach.getSaving(), null, null, false,
										0, 0, 0, 0, 0);
							} else {
								for (Player player : playerList) {
									leagueObjectInserted = leagueDb.insertLeagueInDb(league.getLeagueName(),
											conference.getConferenceName(), divison.getDivisionName(),
											team.getTeamName(), team.getGeneralManager(), coach.getName(),
											coach.getSkating(), coach.getShooting(), coach.getChecking(),
											coach.getSaving(), player.getPlayerName(), player.getPosition(),
											player.getCaptain(), player.getSkating(), player.getShooting(),
											player.getChecking(), player.getSaving(), player.getAge());
								}
							}
						}
					}
				}
			}
		}

		// insert gameplayConfig
		GameplayConfig gameplayConfig = new GameplayConfig(league.getGamePlayConfig().getAging(),
				league.getGamePlayConfig().getGameResolver(), league.getGamePlayConfig().getInjuries(),
				league.getGamePlayConfig().getTraining(), league.getGamePlayConfig().getTrading(), gameplayConfigDb,
				leagueName);

		// freeAgents and Retired players
		
		playerObj.insertLeagueFreeAgents(league.getFreeAgents());
		playerObj.insertLeagueRetiredPlayers(league.getRetiredPlayers());
		
		// coaches
		Coach coach = new Coach(leagueName, coachDb);
		coach.insertCoaches(league.getCoaches());

		// generalManagers
		GeneralManager generalManager = new GeneralManager(leagueName, managerDb);
		generalManager.insertManager(league.getGeneralManagers());

		
		
		if (leagueObjectInserted && freeAgentInsertionCheck) {
			return true;
		} else
			return false;

	}

	public boolean isLeagueNameValid(String leagueName, ILeagueDb leagueDb) {
		return leagueDb.checkLeagueNameExitsInDb(leagueName);
	}
}
