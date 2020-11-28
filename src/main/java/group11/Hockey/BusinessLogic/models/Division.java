package group11.Hockey.BusinessLogic.models;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class contains the business logic for the division model.
 *
 * @author jatinpartaprana
 *
 */
public class Division implements IDivision {

	private String divisionName;
	private List<Team> teams = null;
	private static Logger logger = LogManager.getLogger(Division.class);

	public Division(String divisionName, List<? extends ITeam> teams) {
		super();
		this.divisionName = divisionName;
		this.teams = (List<Team>) teams;
	}

	public Division() {
	}

	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		logger.info("Entered getDivisionName()");
		return divisionName;
	}

	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		logger.info("Entered setDivisionName()");
		this.divisionName = divisionName;
	}

	/**
	 * @return the teams
	 */
	public List<Team> getTeams() {
		logger.info("Entered getTeams()");
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<Team> teams) {
		logger.info("Entered setTeams()");
		this.teams = teams;
	}


	@Override
	public String toString() {
		logger.info("Entered toString()");
		return "Division [divisionName=" + divisionName + ", teams=" + teams + "]";
	}

	public boolean isDivisionNameValid(String divisionName, List<Division> divisionList) {
		logger.info("Entered isDivisionNameValid()");
		boolean isDivisionNameValid = false;
		if(divisionList == null) {
			logger.warn("Division name is null");
			return isDivisionNameValid;
		}
		for(Division division: divisionList) {
			if(division.getDivisionName().equalsIgnoreCase(divisionName)) {
				logger.info("Divison name"+division.getDivisionName()+" is valid");
				isDivisionNameValid = true;
				break;
			}
		}
		return isDivisionNameValid;
	}

	public Division getDivisionFromDivisionName(String divisionName, List<Division> divisionList) {
		logger.info("Entered getDivisionFromDivisionName()");
		Division division = null;
		for(Division div: divisionList) {
			if(div.getDivisionName().equalsIgnoreCase(divisionName)) {
				logger.info(div.getDivisionName()+" is a valid division");
				division = div;
				break;
			}
		}
		return division;
	}


	public void addNewTeamInDivision(Team newTeam) {
		logger.info("Entered addNewTeamInDivision()");
		teams.add(newTeam);
	}
}
