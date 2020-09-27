package group11.Hockey.models;

import java.util.List;

/**
 * This class contains the business logic for the division model.
 * 
 * @author jatinpartaprana
 *
 */
public class Division {

	private String divisionName;
	private List<Team> teams = null;

	public Division(String divisionName, List<Team> teams) {
		super();
		this.divisionName = divisionName;
		this.teams = teams;
	}

	public Division() {
	}

	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}

	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	/**
	 * @return the teams
	 */
	public List<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	

	@Override
	public String toString() {
		return "Division [divisionName=" + divisionName + ", teams=" + teams + "]";
	}

	public boolean isDivisionNameValid(String divisionName, List<Division> divisionList) {
		boolean isDivisionNameValid = false;
		for(Division division: divisionList) {
			if(division.getDivisionName().equalsIgnoreCase(divisionName)) {
				isDivisionNameValid = true;
				break;
			}
		}
		return isDivisionNameValid;
	}
	
	public Division getConferencefromConferenceName(String divisionName, List<Division> divisionList) {
		Division division = null;
		for(Division div: divisionList) {
			if(div.getDivisionName().equalsIgnoreCase(divisionName)) {
				division = div;
				break;
			}
		}
		return division;
	}
	
	
//	public boolean isTeamNameValid(String teamName) {
//		boolean isTeamNameValid = true;
//		for(Team team: teams) {
//			if(team.getTeamName().equalsIgnoreCase(teamName)) {
//				isTeamNameValid = false;
//				break;
//			}
//				
//		}
//		// check in db if team name exits or not
//		return isTeamNameValid;
//	}
//
//	public boolean isTeamManagerNameValid(String managerName) {
//		boolean isTeamMangerNameValid = true;
//		for(Team team: teams) {
//			if(team.getTeamName().equalsIgnoreCase(teamName)) {
//				isTeamNameValid = false;
//				break;
//			}
//				
//		}
//		// check in db if team name exits or not
//		return false;
//	}
//
//	public boolean isHeadCoachNameValid(String headCoach) {
//		// cheeck any coach name in existing json exits or not
//		// check in db if team name exits or not
//		return false;
//	}

	public void addNewTeamInDivision(Team newTeam) {
		teams.add(newTeam);
	}
}
