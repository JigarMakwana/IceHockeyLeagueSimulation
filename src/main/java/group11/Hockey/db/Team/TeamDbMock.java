package group11.Hockey.db.Team;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.db.League.LeagueDbMock;

public class TeamDbMock implements ITeamDb {

	public TeamDbMock() {;
	}

	@Override
	public League loadLeagueWithTeamName(String teamName) {
		LeagueDbMock leagueDbMock = new LeagueDbMock();
		League league = leagueDbMock.populateLeagueObject();
		return league;
	}

}
