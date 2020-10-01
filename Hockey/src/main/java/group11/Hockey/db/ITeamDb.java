package group11.Hockey.db;

import group11.Hockey.models.Team;

public interface ITeamDb {

	public Team findTeamDetails(String teamName, String generalMangerName, String headCoachName, String leagueName);


}
