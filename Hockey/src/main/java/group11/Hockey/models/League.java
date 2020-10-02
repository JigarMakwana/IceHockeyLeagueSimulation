package group11.Hockey.models;

import java.util.List;

import group11.Hockey.db.Conference.IConferenceDb;
import group11.Hockey.db.Division.IDivision;
import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.Team.ITeamDb;

/**
 * This class contains the business logic for the League model
 * 
 * @author jatinpartaprana
 *
 */
public class League {
	private String leagueName;
	private List<Conference> conferences = null;
	private List<FreeAgent> freeAgents = null;

	public League(String leagueName, List<Conference> conferences, List<FreeAgent> freeAgents) {
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
	public List<FreeAgent> getFreeAgents() {
		return freeAgents;
	}

	/**
	 * @param freeAgents the freeAgents to set
	 */
	public void setFreeAgents(List<FreeAgent> freeAgents) {
		this.freeAgents = freeAgents;
	}

	public int insertLeagueObject(League league, ILeagueDb leagueDb, IConferenceDb conferenceDb, IDivision divisionDb,
			ITeamDb teamDb) {
		int leagueId = leagueDb.insertLeagueInDb(league.getLeagueName());
		List<Conference> conferenceList = league.getConferences();
		for (Conference conference : conferenceList) {
			int conferenceId = conferenceDb.insertConferenceInDb(conference.getConferenceName(), leagueId);
			List<Division> divisionList = conference.getDivisions();
			for (Division divison : divisionList) {
				int divisionId = divisionDb.insertDivisionInDb(divison.getDivisionName(), conferenceId);
				List<Team> teamList = divison.getTeams();
				for (Team team : teamList) {
					teamDb.insertTeamInDb(team.getTeamName(), team.getGeneralManager(), team.getHeadCoach(),
							divisionId);
				}
			}
		}

		return leagueId;

	}

	public boolean isLeagueNameValid(String leagueName, ILeagueDb leagueDb) {
		return leagueDb.checkLeagueNameExitsInDb(leagueName);

	}
}
