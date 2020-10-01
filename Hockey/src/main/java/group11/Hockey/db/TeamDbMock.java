package group11.Hockey.db;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.models.Team;

public class TeamDbMock implements ITeamDb {

	List<Team> teamList = new ArrayList<Team>();

	public TeamDbMock() {
		Team team1 = new Team("Boston", "Mister Fred", "Mary Smith", null);
		Team team2 = new Team("Dallas Stars", "John", "Sean", null);
		teamList.add(team1);
		teamList.add(team2);
	}

	public boolean findTeamWithName(String teamName) {
		boolean isTeamPresent = false;
		for (Team team : teamList) {
			if (team.getTeamName().equalsIgnoreCase(teamName)) {
				isTeamPresent = true;
				break;
			}
		}
		return isTeamPresent;
	}

	public boolean findGeneralManagerWithName(String generalManagerName) {
		boolean isGeneralManagerFound = false;
		for (Team team : teamList) {
			if (team.getGeneralManager().equalsIgnoreCase(generalManagerName)) {
				isGeneralManagerFound = true;
				break;
			}
		}
		return isGeneralManagerFound;
	}

	public boolean findHeadCoachWithName(String headCoach) {
		boolean isHeadCoachPresent = false;
		for (Team team : teamList) {
			if (team.getHeadCoach().equalsIgnoreCase(headCoach)) {
				isHeadCoachPresent = true;
				break;
			}
		}
		return isHeadCoachPresent;
	}

	@Override
	public Team findTeamDetails(String teamName, String generalMangerName, String headCoachName, String leagueName) {
		// TODO Auto-generated method stub
		return null;
	}

}
