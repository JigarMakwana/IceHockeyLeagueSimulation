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

	@SuppressWarnings("unchecked")
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

	public boolean isDivisionNameValid(String divisionName, List<IDivision> divisionList) {
		boolean isDivisionNameValid = false;
		if(divisionList == null) {
			return isDivisionNameValid;
		}
		for(IDivision division: divisionList) {
			if(((Division) division).getDivisionName().equalsIgnoreCase(divisionName)) {
				isDivisionNameValid = true;
				break;
			}
		}
		return isDivisionNameValid;
	}

	public Division getDivisionFromDivisionName(String divisionName, List<IDivision> divisionList) {
		IDivision division = null;
		for(IDivision div: divisionList) {
			if(((Division) div).getDivisionName().equalsIgnoreCase(divisionName)) {
				division = div;
				break;
			}
		}
		return (Division) division;
	}


	public void addNewTeamInDivision(ITeam newTeam) {
		teams.add(newTeam);
	}
}
