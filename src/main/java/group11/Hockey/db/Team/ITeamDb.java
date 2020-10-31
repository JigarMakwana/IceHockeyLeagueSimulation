package group11.Hockey.db.Team;

import java.util.List;

import group11.Hockey.BusinessLogic.models.League;

public interface ITeamDb {
	
	public List<League> loadTeamFromTeamName(String teamName);

}
