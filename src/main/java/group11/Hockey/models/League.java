package group11.Hockey.models;

import java.util.List;

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
	private List<Player> freeAgents = null;

	public League(String leagueName, List<Conference> conferences, List<Player> freeAgents) {
		super();
		this.leagueName = leagueName;
		this.conferences = conferences;
		this.freeAgents = freeAgents;
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
		return freeAgents;
	}

	/**
	 * @param freeAgents the freeAgents to set
	 */
	public void setFreeAgents(List<Player> freeAgents) {
		this.freeAgents = freeAgents;
	}

	public boolean insertLeagueObject(League league, ILeagueDb leagueDb) {
		boolean leagueObjectInserted = false;
		boolean freeAgentInsertionCheck = false;

		List<Conference> conferenceList = league.getConferences();
		for (Conference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			if (divisionList == null || divisionList.size() == 0) {
				leagueObjectInserted = leagueDb.insertLeagueInDb(league.getLeagueName(), conference.getConferenceName(),
						null, null, null, null, null, null, null);
			} else {
				for (Division divison : divisionList) {
					List<Team> teamList = divison.getTeams();
					if (teamList == null || teamList.size() == 0) {
						leagueObjectInserted = leagueDb.insertLeagueInDb(league.getLeagueName(),
								conference.getConferenceName(), divison.getDivisionName(), null, null, null, null, null,
								null);
					} else {
						for (Team team : teamList) {
							List<Player> playerList = team.getPlayers();
							if (playerList == null || playerList.size() == 0) {
								leagueObjectInserted = leagueDb.insertLeagueInDb(league.getLeagueName(),
										conference.getConferenceName(), divison.getDivisionName(), team.getTeamName(),
										team.getGeneralManager(), team.getHeadCoach(), null, null, null);
							} else {
								for (Player player : playerList) {
									leagueObjectInserted = leagueDb.insertLeagueInDb(league.getLeagueName(),
											conference.getConferenceName(), divison.getDivisionName(),
											team.getTeamName(), team.getGeneralManager(), team.getHeadCoach(),
											player.getPlayerName(), player.getPosition(), player.getCaptain());
								}
							}
						}
					}
				}
			}
		}
		
		List<Player> listOfFreeAgents = league.getFreeAgents();
		if(listOfFreeAgents == null || listOfFreeAgents.size() == 0) {
			freeAgentInsertionCheck = true;
		}else {
		for(Player freeAgent: listOfFreeAgents) {
			freeAgentInsertionCheck = leagueDb.insertLeagueFreeAgents(leagueName, freeAgent.getPlayerName(), freeAgent.getPosition(), freeAgent.getCaptain());
		}
		}
		
		if(leagueObjectInserted && freeAgentInsertionCheck) {
			return true;
		}
		else
		return false;

	}

	public boolean isLeagueNameValid(String leagueName, ILeagueDb leagueDb) {
		return leagueDb.checkLeagueNameExitsInDb(leagueName);

	}
}
