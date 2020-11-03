package group11.Hockey.BusinessLogic.models;

import java.util.List;

public interface IDivision {

	public boolean isDivisionNameValid(String divisionName, List<Division> divisionList);
	public Division getDivisionFromDivisionName(String divisionName, List<Division> divisionList);
	public void addNewTeamInDivision(Team newTeam);
}
