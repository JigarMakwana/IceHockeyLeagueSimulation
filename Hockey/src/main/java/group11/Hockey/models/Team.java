package group11.Hockey.models;

import java.lang.reflect.Method;
import java.util.List;

import group11.Hockey.db.ITeamDb;

/**
 * This class contain all the business logic related to team model
 * 
 * @author jatinpartaprana
 *
 */
public class Team {

	private String teamName;
	private String generalManager;
	private String headCoach;
	private List<Player> players = null;
	private ITeamDb dao;
	

	public Team(String teamName, String generalManager, String headCoach, List<Player> players) {
		super();
		this.teamName = teamName;
		this.generalManager = generalManager;
		this.headCoach = headCoach;
		this.players = players;
	}

	public Team() {
		
	}

	
	

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the generalManager
	 */
	public String getGeneralManager() {
		return generalManager;
	}

	/**
	 * @param generalManager the generalManager to set
	 */
	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}

	/**
	 * @return the headCoach
	 */
	public String getHeadCoach() {
		return headCoach;
	}

	/**
	 * @param headCoach the headCoach to set
	 */
	public void setHeadCoach(String headCoach) {
		this.headCoach = headCoach;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean validateTeamMethod(League league, String methodName, String value) {
		boolean isTeamDetailsValid = true;
		List<Conference> cconferenceList = league.getConferences();
		for (Conference conference : cconferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {

					Method m;
					try {
						m = team.getClass().getMethod(methodName);
						String fetchedValue = (String) m.invoke(team);
						if (fetchedValue.equalsIgnoreCase(value)) {
							isTeamDetailsValid = false;
							return isTeamDetailsValid;
						}
					} catch (Exception e) {
						System.out.println("Exception occured while validating the new team values with imported JSON");
					}
				}
			}
		}

		return isTeamDetailsValid;

	}

	public boolean isTeamNameValid(String teamName, League league) {
		boolean isTeamNameValid = true;
		isTeamNameValid = validateTeamMethod(league, "getTeamName", teamName);

		// check in db if team name exits or not
		

		return isTeamNameValid;
	}

//	public boolean validateTeamDetails(String teamName, String managerName, String headCoach, League league) {
//		
//		return false;
//		
//	}

	public boolean isTeamManagerNameValid(String managerName, League league) {
		boolean isTeamMangerNameValid = true;
		//getGeneralManager
		isTeamMangerNameValid = validateTeamMethod(league, "getGeneralManager", managerName);
		// check in db if team name exits or not
		// teamDbImpl where object we will get that from constructor and make a new
		// constructor or check
		return isTeamMangerNameValid;
	}

	public boolean isHeadCoachNameValid(String headCoach, League league) {
		boolean isHeadCoachNameValid = true;
		isHeadCoachNameValid = validateTeamMethod(league, "getHeadCoach", headCoach);
		return isHeadCoachNameValid;
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", generalManager=" + generalManager + ", headCoach=" + headCoach
				+ ", players=" + players + "]";
	}

}
