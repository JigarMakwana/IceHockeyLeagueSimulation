package group11.Hockey.db.Team;

import group11.Hockey.BusinessLogic.models.League;

public interface ITeamDb {
	
	public League loadLeagueWithTeamName(String teamName);

}
