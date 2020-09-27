package group11.Hockey.db;

public interface ITeamDb {

	public boolean findTeamWithName(String teamName);

	public boolean findGeneralManagerWithName(String generalManagerName);

	public boolean findHeadCoachWithName(String headCoach);

}
