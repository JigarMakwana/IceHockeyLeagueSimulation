package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.Assert;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IUserInputCheck;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.UserInputCheck;
import group11.Hockey.BusinessLogic.Validations;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.Display;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

public class UserInputCheckTest {

	String conferenceName = "Eastern Conference";
	String divisionName = "Atlantic";
	String teamName = "Team1";
	String generalManger = "General Manager 1";
	String headCoach = "Coach 1";
	LeagueModelMock leagueMock = new LeagueModelMock();
	ILeague leagueObj = leagueMock.getLeagueInfo();
	ICommandLineInput userInputMode = mock(ICommandLineInput.class);
	IDisplay display = DefaultHockeyFactory.makeDisplay();
	IValidations validations = DefaultHockeyFactory.makeValidations(display);

	IUserInputCheck userInputCheck = new UserInputCheck(userInputMode, validations, display);

	@Test
	public void conferenceNameFromUserCheckTest() {
		when(userInputMode.getValueFromUser()).thenReturn(conferenceName);
		String conference = userInputCheck.conferenceNameFromUserCheck(leagueObj.getConferences());
		Assert.assertEquals(conferenceName, conference);
	}

	@Test
	public void divisonNameFromUserCheckTest() {
		when(userInputMode.getValueFromUser()).thenReturn(divisionName);
		String division = userInputCheck.divisonNameFromUserCheck(leagueObj.getConferences().get(0));
		Assert.assertEquals(divisionName, division);
	}

	@Test
	public void teamNameFromUserCheckTest() {
		when(userInputMode.getValueFromUser()).thenReturn(teamName);
		Team newTeam = new Team();
		userInputCheck.teamNameFromUserCheck(newTeam, leagueObj);
		Assert.assertEquals(newTeam.getTeamName(), teamName);
	}

	@Test
	public void generalManagerNameFromUserCheckTest() {
		when(userInputMode.getValueFromUser()).thenReturn(generalManger);
		Team newTeam = new Team();
		userInputCheck.generalManagerNameFromUserCheck(newTeam, leagueObj);
//		Assert.assertEquals(newTeam.getGeneralManager(), generalManger);
	}

	@Test
	public void headCoachNameFromUserCheckTest() {
		when(userInputMode.getValueFromUser()).thenReturn(headCoach);
		Team newTeam = new Team();
		userInputCheck.headCoachNameFromUserCheck(newTeam, leagueObj);
//		Assert.assertEquals(newTeam.getHeadCoach().getName(), headCoach);
	}

	@Test
	public void playerChoiceFromUserTest() {
		when(userInputMode.getValueFromUser()).thenReturn("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20");
		Team newTeam = new Team();
		userInputCheck.playerChoiceFromUser(newTeam, leagueObj);
		;
		Assert.assertTrue(newTeam.getPlayers().size() == 20);
	}
}
