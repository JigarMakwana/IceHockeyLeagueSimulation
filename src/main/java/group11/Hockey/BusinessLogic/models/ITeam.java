package group11.Hockey.BusinessLogic.models;

public interface ITeam {
	public String getTeamName();

	public String getGeneralManager();

	public Coach getHeadCoach();
	
	public int getPoints();
}
