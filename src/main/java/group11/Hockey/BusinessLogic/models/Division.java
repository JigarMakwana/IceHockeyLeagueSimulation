package group11.Hockey.BusinessLogic.models;

import java.util.List;

/**
 * This class contains the business logic for the division model.
 *
 *
 */
public class Division implements IDivision {

	private String divisionName;
	private List<ITeam> teams = null;

	public Division(String divisionName, List<? extends ITeam> teams) {
		super();
		this.divisionName = divisionName;
		this.teams = (List<ITeam>) teams;
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
	public List<ITeam> getTeams() {
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<ITeam> teams) {
		this.teams = teams;
	}


	@Override
	public String toString() {
		return "Division [divisionName=" + divisionName + ", teams=" + teams + "]";
	}

	public boolean isDivisionNameValid(String divisionName, List<Division> divisionList) {
		boolean isDivisionNameValid = false;
		if(divisionList == null) {
			return isDivisionNameValid;
		}
		for(Division division: divisionList) {
			if(division.getDivisionName().equalsIgnoreCase(divisionName)) {
				isDivisionNameValid = true;
				break;
			}
		}
		return isDivisionNameValid;
	}

	public Division getDivisionFromDivisionName(String divisionName, List<Division> divisionList) {
		Division division = null;
		for(Division div: divisionList) {
			if(div.getDivisionName().equalsIgnoreCase(divisionName)) {
				division = div;
				break;
			}
		}
		return division;
	}


	public void addNewTeamInDivision(ITeam newTeam) {
		teams.add(newTeam);
	}
}
