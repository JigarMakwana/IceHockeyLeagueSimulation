package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.CreateTeam;
import group11.Hockey.BusinessLogic.ICreateTeam;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.models.LeagueModelMock;

public class CreateTeamTest {

	String leagueName = "DLU";
	String conferenceName = "Eastern Conference";
	String divisionName = "Atlantic";
	String teamName = "Team1";
	String generalManger = "General Manager 1";
	String headCoach = "Coach 1";
	LeagueModelMock leagueMock = new LeagueModelMock();
	League leagueObj = leagueMock.getLeagueInfo();

	@Test
	public void createTeamTest() {
		ICommandLineInput userInputMode = mock(ICommandLineInput.class);
		when(userInputMode.getValueFromUser()).thenReturn(conferenceName, divisionName, teamName, generalManger,
				headCoach, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20");

		ICreateTeam createTeam = new CreateTeam(leagueObj, userInputMode);
		;
		createTeam.createTeamMethod();
		Team newTeam = leagueObj.getConferences().get(0).getDivisions().get(0).getTeams().get(2);
		Assert.assertTrue(leagueObj.getConferences().get(0).getConferenceName().equalsIgnoreCase(conferenceName));
		Assert.assertTrue(leagueObj.getConferences().get(0).getDivisions().get(0).getDivisionName()
				.equalsIgnoreCase(divisionName));
		Assert.assertTrue(leagueObj.getConferences().get(0).getDivisions().get(0).getTeams().size() == 3);
		Assert.assertTrue(newTeam.getPlayers().size() == 20);
	}

//	@Test
//	public void conferenceNameFromUserCheckTest() {
//		ICommandLineInput userInputMode = mock(ICommandLineInput.class);
//		when(userInputMode.getValueFromUser()).thenReturn(conferenceName);
//		CreateTeam createTeam = new CreateTeam(leagueObj, userInputMode);
//		createTeam.conferenceNameFromUserCheck(leagueObj.getConferences());
//		Assert.assertTrue(leagueObj.getConferences().get(0).getConferenceName().equalsIgnoreCase(conferenceName));
//	}
//	
//	@Test
//	public void divisonNameFromUserCheckTest() {
//		ICommandLineInput userInputMode = mock(ICommandLineInput.class);
//		when(userInputMode.getValueFromUser()).thenReturn(divisionName);
//		CreateTeam createTeam = new CreateTeam(leagueObj, userInputMode);
//		createTeam.divisonNameFromUserCheck(leagueObj.getConferences().get(0));
//		
//	}

}
