package group11.Hockey;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.CreateTeam;
import group11.Hockey.BusinessLogic.ICreateTeam;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.models.LeagueModelMock;

public class CreateTeamTest {

	@Test
	public void createTeamTest() {
		String leagueName = "DLU";
		String conferenceName = "Eastern Conference";
		String divisionName = "Atlantic";
		String teamName = "Team1";
		String generalManger = "Manager1";
		String headCoach = "HeadCoach1";
		
		LeagueModelMock leagueMock = new LeagueModelMock();
		League leagueObj = leagueMock.getLeagueInfo();

		ICommandLineInput userInputMode = mock(ICommandLineInput.class);
		when(userInputMode.getValueFromUser()).thenReturn(conferenceName, divisionName, teamName, generalManger, headCoach, "1", "2", "3");

		List<Team> mockTeam = mock(List.class);
		when(mockTeam.size()).thenReturn(2);
		
		ICreateTeam createTeam = new CreateTeam(leagueObj,userInputMode);;
		//createTeam.createTeamMethod();
		//Assert.assertTrue(leagueObj.getConferences().get(0).getDivisions().get(0).getTeams().size() == 3);
//		when(leagueDbMock.insertLeagueInDb(leagueName, conferenceName, divisionName, teamName, generalManger,
//			headCoach, null, null, null)).thenReturn(true);
//		CreateTeam createTeam = new CreateTeam(userInputMode, leagueObj, leagueDbMock, null, null, null, null);
//		League modifiedLeagueObj = createTeam.getTeam();
//
//		List<Conference> confList = modifiedLeagueObj.getConferences();
//		List<Division> divList = confList.get(0).getDivisions();
//		List<Team> teamList = divList.get(0).getTeams();
//
//		Assert.assertEquals(mockTeam.size() + 1, teamList.size());

	}

}
