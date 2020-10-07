package group11.Hockey.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.League.LeagueDbMock;
import junit.framework.Assert;

public class LeagueTest {
	
	Conference westernConference = new Conference("Westeren Conference", null);
	Conference easternConference = new Conference("Eastern Conference", null);
	List<Conference> conferenceList = new ArrayList<Conference>();
	
	public League populateLeagueObject() {
		List<Team> teamsList = new ArrayList<Team>();
		Team team = new Team("Vancouver Canucks", "John", "Peter", null);
		teamsList.add(team);

		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic Division", teamsList);
		divisionsList.add(atlanticDivision);
		List<Conference> conferenceList = new ArrayList<Conference>();
		Conference conference = new Conference("Westeren Conference", divisionsList);
		conferenceList.add(conference);
		League league = new League("DHL", conferenceList, null);
		return league;

	}
	

	@Test
	public void LeagueDeafultConstructorTest() {
		League league = new League();
		Assert.assertNull(league.getLeagueName());
		Assert.assertNull(league.getConferences());
		Assert.assertNull(league.getConferences());
	}

	@Test
	public void LeagueParameterisedConstructorTest() {
		League league = new League("DHL", Arrays.asList(new Conference("Westeren Conference", null)), null);
		Assert.assertEquals("DHL", league.getLeagueName());
		Assert.assertTrue(league.getConferences().size() == 1);
		Assert.assertNull(league.getFreeAgents());
	}
	
	@Test
	public void setLeagueNameTest() {
		League league = new League();
		league.setLeagueName("DHL");
		Assert.assertEquals("DHL", league.getLeagueName());
	}

	@Test
	public void getLeagueNameTest() {
		League league = new League("DHL", null, null);
		Assert.assertEquals("DHL", league.getLeagueName());
	}
	
	@Test
	public void setConferencesTest() {
		League league = new League();
		league.setConferences(Arrays.asList(westernConference, easternConference));
		Assert.assertTrue(league.getConferences().size() == 2);
		Assert.assertEquals("Westeren Conference", league.getConferences().get(0).getConferenceName());
		
	}

	@Test
	public void getConferencesTest() {
		
		conferenceList.add(westernConference);
		conferenceList.add(easternConference);

		League league = new League("DHL", conferenceList, null);

		Assert.assertEquals("Conference [conferenceName=" + westernConference.getConferenceName() + ", divisions="
				+ westernConference.getDivisions() + "]", league.getConferences().get(0).toString());
		Assert.assertEquals("Conference [conferenceName=" + easternConference.getConferenceName() + ", divisions="
				+ easternConference.getDivisions() + "]", league.getConferences().get(1).toString());

		Assert.assertTrue(league.getConferences().size() == 2);
	}
	@Test
	public void isLeagueNameValid() {
		League league = populateLeagueObject();
		ILeagueDb leagueDb = new LeagueDbMock();
		Assert.assertTrue(league.isLeagueNameValid(league.getLeagueName(), leagueDb));
		league.setLeagueName("NHL");
		Assert.assertFalse(league.isLeagueNameValid(league.getLeagueName(), leagueDb));
	}
	
	@Test
	public void insertLeagueObject() {
		League league = populateLeagueObject();
		ILeagueDb leagueDb = new LeagueDbMock();
		boolean leagueId = league.insertLeagueObject(league, leagueDb);
		Assert.assertTrue(leagueId);
	}

}
