package group11.Hockey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.LeagueMock;
import group11.Hockey.models.Team;

public class CreateTeamTest {

	@Test
	public void createTeamTest() {
		String leagueName = "DLU";
		String conferenceName = "Eastern Conference";
		String divisionName = "Atlantic";
		String teamName = "Team1";
		String generalManger = "Manager1";
		String headCoach = "HeadCoach1";
		LeagueMock leagueMock = new LeagueMock();
		League leagueObj = leagueMock.getLeagueInfo();

		IUserInputMode userInputMode = mock(IUserInputMode.class);
		when(userInputMode.getInt()).thenReturn(1);
		when(userInputMode.getName()).thenReturn(conferenceName, divisionName, teamName, generalManger, headCoach);

		List<Team> mockTeam = mock(List.class);
		when(mockTeam.size()).thenReturn(2);

		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		when(leagueDbMock.insertLeagueInDb(leagueName, conferenceName, divisionName, teamName, generalManger,
				headCoach)).thenReturn(true);
		CreateTeam createTeam = new CreateTeam(userInputMode, leagueObj, leagueDbMock);
		League modifiedLeagueObj = createTeam.getTeam();

		List<Conference> confList = modifiedLeagueObj.getConferences();
		List<Division> divList = confList.get(0).getDivisions();
		List<Team> teamList = divList.get(0).getTeams();

		Assert.assertEquals(mockTeam.size() + 1, teamList.size());

	}

}
