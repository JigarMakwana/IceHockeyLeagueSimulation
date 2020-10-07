package group11.Hockey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.models.League;
import group11.Hockey.models.LeagueMock;
import group11.Hockey.models.LeagueTest;
import group11.Hockey.models.Team;

public class LoadTeamTest {

	@Test
	public void getTeamWithOneLeagueTest() {
		String teamName="Boston";
		List<League> leagueList = new ArrayList<League>();
		LeagueMock leagueMock = new LeagueMock();
		League league = leagueMock.getLeagueInfo();
		leagueList.add(league);
		
		IUserInputMode userInputMode = mock(IUserInputMode.class);
		when(userInputMode.getName()).thenReturn(teamName);
		
		
		ITeamDb teamDbMock = mock(ITeamDb.class);
		when(teamDbMock.loadTeamFromTeamName(teamName)).thenReturn(leagueList);
		
		
		LoadTeam loadTeam = new LoadTeam(userInputMode,teamDbMock);
		try {
			Team team = loadTeam.getTeam();
			Assert.assertEquals(team.getTeamName(), teamName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getTeamWithTwoLeaguesTest() {
		String teamName="Boston";
		List<League> leagueList = new ArrayList<League>();
		LeagueMock leagueMock = new LeagueMock();
		League league = leagueMock.getLeagueInfo();
		leagueList.add(league);
		leagueList.add(league);
		
		IUserInputMode userInputMode = mock(IUserInputMode.class);
		when(userInputMode.getInt()).thenReturn(1);
		when(userInputMode.getName()).thenReturn(teamName);
		
		ITeamDb teamDbMock = mock(ITeamDb.class);
		when(teamDbMock.loadTeamFromTeamName(teamName)).thenReturn(leagueList);
		
		
		LoadTeam loadTeam = new LoadTeam(userInputMode,teamDbMock);
		try {
			Team team = loadTeam.getTeam();
			Assert.assertEquals(team.getTeamName(), teamName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
