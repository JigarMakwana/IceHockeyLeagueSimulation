package group11.Hockey.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class DivisionTest {
	
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
		division.setTeams(Arrays.asList(new Team("Vancouver Canucks", "John", "Peter", null)));
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
		Team team1 = new Team("Vancouver Canucks", "John", "Peter", null);
		Team team2 = new Team("Maple Leafs", "John", "Peter", null);
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
	}

	@Test
	public void getTeamsListSizeTest() {
		List<Team> teamsList = new ArrayList<Team>();
		Team team1 = new Team("Vancouver Canucks", "John", "Peter", null);
		Team team2 = new Team("Maple Leafs", "John", "Peter", null);
		teamsList.add(team1);
		teamsList.add(team2);

		Division division = new Division("Atlantic Division", teamsList);
		Assert.assertTrue(division.getTeams().size() == 2);
	}
}
