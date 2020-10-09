package group11.Hockey.db.Team;

import java.util.List;

import group11.Hockey.models.League;

public interface ITeamDb {
	
	public List<League> loadTeamFromTeamName(String teamName);

}
