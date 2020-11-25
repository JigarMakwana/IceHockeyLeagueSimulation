package group11.Hockey.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;

import org.junit.Assert;


public class DivisionTest {
	Division division = new Division();

	@Test
	public void DivisionConstructorTest() {
		Division division = new Division();
		Assert.assertNull(division.getTeams());
		Assert.assertNull(division.getDivisionName());
	}

	@Test
	public void DivisionParameterizedConstructorTest() {
		Division division = new Division("Atlantic Division", Arrays.asList(new Team()));
		Assert.assertEquals("Atlantic Division", division.getDivisionName());
		Assert.assertTrue(division.getTeams().size()==1);
	}

	@Test
	public void setDivisionNameTest() {
		Division division = new Division();
		division.setDivisionName("Atlantic Division");
		Assert.assertEquals("Atlantic Division", division.getDivisionName());
	}

	@Test
	public void setTeamsTest() {
		Division division = new Division();
		division.setTeams(Arrays.asList(new Team("Vancouver Canucks", null, null, null)));
		Assert.assertTrue(division.getTeams().size() == 1);
		Assert.assertEquals("Vancouver Canucks", division.getTeams().get(0).getTeamName());
	}

	@Test
	public void getDivisionNameTest() {
		Division division = new Division("Atlantic Division", null);
		Assert.assertEquals("Atlantic Division", division.getDivisionName());
	}

	@Test
	public void getTeamsTest() {
		List<Team> teamsList = new ArrayList<Team>();
		Team team1 = new Team("Vancouver Canucks", null, null, null);
		Team team2 = new Team("Maple Leafs", null, null, null);
		teamsList.add(team1);
		teamsList.add(team2);

		Division division = new Division("Atlantic Division", teamsList);

		Assert.assertEquals(
				"Team [teamName=" + team1.getTeamName() + ", generalManager=" + team1.getGeneralManager()
						+ ", headCoach=" + team1.getHeadCoach() + ", players=" + null + "]",
				division.getTeams().get(0).toString());
		Assert.assertEquals(
				"Team [teamName=" + team2.getTeamName() + ", generalManager=" + team2.getGeneralManager()
						+ ", headCoach=" + team2.getHeadCoach() + ", players=" + null + "]",
				division.getTeams().get(1).toString());
		Assert.assertTrue(division.getTeams().size() == 2);
	}

	@Test
	public void isDivisionNameValidTest() {
		LeagueTest leagueTest = new LeagueTest();
		League leagueObject = leagueTest.populateLeagueObject();
		String divisionName = "Eastern Zone";
		boolean divisionNameValid;
		divisionNameValid = division.isDivisionNameValid(divisionName, leagueObject.getConferences().get(0).getDivisions());
		Assert.assertFalse(divisionNameValid);
		divisionName = "Atlantic Division";
		divisionNameValid = division.isDivisionNameValid(divisionName, leagueObject.getConferences().get(0).getDivisions());
		Assert.assertTrue(divisionNameValid);
	}

	@Test
	public void getDivisionFromDivisionNameTest() {
		LeagueTest leagueTest = new LeagueTest();
		League leagueObject = leagueTest.populateLeagueObject();
		Division divisionFromName;
		divisionFromName = division.getDivisionFromDivisionName("Eastern Zone", leagueObject.getConferences().get(0).getDivisions());
		Assert.assertNull(divisionFromName);
		divisionFromName = division.getDivisionFromDivisionName("Atlantic Division", leagueObject.getConferences().get(0).getDivisions());
		Assert.assertNotNull(divisionFromName);
		Assert.assertEquals("Atlantic Division", leagueObject.getConferences().get(0).getDivisions().get(0).getDivisionName());
	}

	@Test
	public void addNewTeamInDivisionTest() {
		LeagueTest leagueTest = new LeagueTest();
		League leagueObject = leagueTest.populateLeagueObject();
		Team team = new Team("Dalhousie Tigers", null, null, null);
		Division divisionFromLeagueObject = leagueObject.getConferences().get(0).getDivisions().get(0);
		divisionFromLeagueObject.addNewTeamInDivision(team);
		Assert.assertTrue(leagueObject.getConferences().get(0).getDivisions().get(0).getTeams().get(1).getTeamName().equalsIgnoreCase("Dalhousie Tigers"));
	}

}
