package group11.Hockey.db.Team;

import java.util.List;

import group11.Hockey.models.League;

public interface ITeamDb {
	
	public int insertTeamInDb(String teamName, String generalMangerName, String coachName, int divisionId);
	public List<League> loadTeamFromTeamName(String teamName);

}
