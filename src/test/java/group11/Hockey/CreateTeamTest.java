package group11.Hockey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.LeagueMock;
import group11.Hockey.models.Team;

public class CreateTeamTest {

	@Test
	public void createTeamTest() {
		LeagueMock leagueMock = new LeagueMock();
		League leagueObj = leagueMock.getLeagueInfo();
		
		IUserInputMode userInputMode = mock(IUserInputMode.class);
		when(userInputMode.getInt()).thenReturn(1);
		when(userInputMode.getName()).thenReturn("Eastern Conference", "Atlantic","Team1","Manager1","HeadCoach1");
		
		List<Team> mockTeam = mock(List.class);
		when(mockTeam.size()).thenReturn(2);
		
		CreateTeam createTeam = new CreateTeam(userInputMode, leagueObj);
		League modifiedLeagueObj = createTeam.getTeam();
		
		
		List<Conference> confList = modifiedLeagueObj.getConferences();
		List<Division> divList = confList.get(0).getDivisions();
		List<Team> teamList = divList.get(0).getTeams();
		
		Assert.assertEquals(mockTeam.size()+1, teamList.size());
		
	}

}
