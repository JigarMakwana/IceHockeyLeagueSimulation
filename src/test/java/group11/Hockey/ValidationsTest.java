package group11.Hockey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.Validations;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.models.LeagueTest;

public class ValidationsTest {
	League league;
	IValidations validations = new Validations();

	@Before
	public void loadLeagueObject() {
		LeagueTest leagueTest = new LeagueTest();
		league = leagueTest.populateLeagueObject();
	}

	@Test
	public void isConferenceNameValidTest() {
		String conferenceName = "Westeren Conference";
		boolean conferenceCheck = validations.isConferenceNameValid(conferenceName, league.getConferences());
		Assert.assertFalse(conferenceCheck);
		conferenceName = "Robin Conference";
		conferenceCheck = validations.isConferenceNameValid(conferenceName, league.getConferences());
		Assert.assertTrue(conferenceCheck);
	}
	
	@Test
	public void isDivisionValidTest() {
		//Atlantic Division
		String divisionName = "Atlantic Division";
		Conference conference = league.getConferences().get(0);
		boolean divisionCheck = validations.isDivisionValid(divisionName, conference);
		Assert.assertFalse(divisionCheck);
		divisionName = "Division";
		divisionCheck = validations.isDivisionValid(divisionName, conference);
		Assert.assertTrue(divisionCheck);
	}
	
	@Test
	public void isTeamNameValidTest() {
		String teamName = "Vancouver Canucks";
		boolean teamNameCheck = validations.isTeamNameValid(teamName, league);
		Assert.assertTrue(teamNameCheck);
		teamName = "Toronto Maples";
		teamNameCheck = validations.isTeamNameValid(teamName, league);
		Assert.assertFalse(teamNameCheck);
	}
	
	@Test
	public void generalManagerNameCheckTest() {
		String managerName = "Kevin";
		boolean managerCheck = validations.generalManagerNameCheck(managerName, league);
		Assert.assertFalse(managerCheck);
		managerName = "Hart";
		managerCheck = validations.generalManagerNameCheck(managerName, league);
		Assert.assertTrue(managerCheck);
	}
	
	@Test
	public void headCoachNameCheckTest() {
		String headCoach = "C1";
		boolean coachCheck = validations.headCoachNameCheck(headCoach, league);
		Assert.assertFalse(coachCheck);
		headCoach = "C2";
		coachCheck = validations.headCoachNameCheck(headCoach, league);
		Assert.assertTrue(coachCheck);
	}
	
	@Test
	public void playerCheckTest() {
		boolean playerCheck = false;
		playerCheck = validations.playerCheck("21", league, null, null,
				null);
		Assert.assertTrue(playerCheck);
		List<Player> forwardDefensePlayerList = new ArrayList<Player>();
		List<Player> gloalisList = new ArrayList<Player>();
		forwardDefensePlayerList.add(league.getFreeAgents().get(0));
		gloalisList.add(league.getFreeAgents().get(1));
		playerCheck = validations.playerCheck("1", league, Arrays.asList(1), forwardDefensePlayerList, gloalisList);
		
		Assert.assertTrue(playerCheck);
	}

}
