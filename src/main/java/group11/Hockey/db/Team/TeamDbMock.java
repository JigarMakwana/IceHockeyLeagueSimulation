package group11.Hockey.db.Team;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.db.League.LeagueDbMock;
import group11.Hockey.models.League;

public class TeamDbMock implements ITeamDb {

	public TeamDbMock() {;
	}

	@Override
	public List<League> loadTeamFromTeamName(String teamName) {
		LeagueDbMock leagueDbMock = new LeagueDbMock();
		League league = leagueDbMock.populateLeagueObject();
		List<League> leagueList = new ArrayList<League>();
		leagueList.add(league);
		return leagueList;
	}

}
