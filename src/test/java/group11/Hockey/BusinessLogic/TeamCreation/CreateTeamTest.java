package group11.Hockey.BusinessLogic.TeamCreation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.Validations;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;
/**
 * 
 * @author jatinpartaprana
 *
 */
public class CreateTeamTest {

	String leagueName = "DLU";
	String conferenceName = "Eastern Conference";
	String divisionName = "Atlantic";
	String teamName = "Team1";
	String generalManger = "General Manager 1";
	String headCoach = "Coach 1";
	LeagueModelMock leagueMock = new LeagueModelMock();
	ILeague leagueObj = leagueMock.getLeagueInfo();
	IDisplay display = DefaultHockeyFactory.makeDisplay();
	IValidations validation = new Validations(display);
	IConference conference = new Conference();
	IDivision division = new Division();

	@Test
	public void createTeamTest() {
		ICommandLineInput commandLineMock = mock(ICommandLineInput.class);
		when(commandLineMock.getValueFromUser()).thenReturn(conferenceName, divisionName, teamName, generalManger,
				headCoach, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20","21","22","23","24","25","26","27","28","29","30");

		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		CreateTeam createTeam = (CreateTeam) DefaultHockeyFactory.makeCreateTeam(leagueObj, commandLineMock, leagueDbMock, display);
		createTeam.renderTeam();
		ITeam newTeam = leagueObj.getConferences().get(0).getDivisions().get(0).getTeams().get(2);
		Assert.assertTrue(leagueObj.getConferences().get(0).getConferenceName().equalsIgnoreCase(conferenceName));
		Assert.assertTrue(leagueObj.getConferences().get(0).getDivisions().get(0).getDivisionName()
				.equalsIgnoreCase(divisionName));
		Assert.assertTrue(leagueObj.getConferences().get(0).getDivisions().get(0).getTeams().size() == 3);
		Assert.assertTrue(newTeam.getPlayers().size() == 30);
	}

}
