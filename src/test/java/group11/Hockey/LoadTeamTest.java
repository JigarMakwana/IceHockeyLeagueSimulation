package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.models.LeagueModelMock;

public class LoadTeamTest {

	@Test
	public void getTeamWithOneLeagueTest() throws Exception {
		String teamName = "Boston";
		List<League> leagueList = new ArrayList<League>();
		LeagueModelMock leagueMock = new LeagueModelMock();
		League league = leagueMock.getLeagueInfo();
		leagueList.add(league);

		ICommandLineInput userInputMode = mock(ICommandLineInput.class);
		//when(userInputMode.getName()).thenReturn(teamName);

		ITeamDb teamDbMock = mock(ITeamDb.class);
		when(teamDbMock.loadTeamFromTeamName(teamName)).thenReturn(leagueList);

//		LoadTeam loadTeam = new LoadTeam(userInputMode, teamDbMock);
//
//		Team team = loadTeam.getTeam();
//		Assert.assertEquals(team.getTeamName(), teamName);
	}

	@Test
	public void getTeamWithTwoLeaguesTest() throws Exception {
		String teamName = "Boston";
		List<League> leagueList = new ArrayList<League>();
		LeagueModelMock leagueMock = new LeagueModelMock();
		League league = leagueMock.getLeagueInfo();
		leagueList.add(league);
		leagueList.add(league);

		ICommandLineInput userInputMode = mock(ICommandLineInput.class);
		when(userInputMode.getInt()).thenReturn(1);
//		when(userInputMode.getName()).thenReturn(teamName);
//
//		ITeamDb teamDbMock = mock(ITeamDb.class);
//		when(teamDbMock.loadTeamFromTeamName(teamName)).thenReturn(leagueList);
//
//		LoadTeam loadTeam = new LoadTeam(userInputMode, teamDbMock);
//
//		Team team = loadTeam.getTeam();
//		Assert.assertEquals(team.getTeamName(), teamName);
	}

}
