package group11.Hockey.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.League.LeagueDbMock;
import org.junit.Assert;


public class LeagueTest {
	
	Conference westernConference = new Conference("Westeren Conference", null);
	Conference easternConference = new Conference("Eastern Conference", null);
	List<Conference> conferenceList = new ArrayList<Conference>();
	League league = new League();
	
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
		Player freeAgent = new Player("Player 1", "forward", Boolean.TRUE);
		League league = new League("DHL", conferenceList, Arrays.asList(freeAgent));
		return league;

	}
	

	@Test
	public void LeagueDeafultConstructorTest() {
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
	public void setFreeAgentsTest() {
		List<Player> freeAgentList = new ArrayList<Player>();
		Player freeAgent = new Player("Free Agent 1", "Forward", Boolean.FALSE);
		freeAgentList.add(freeAgent);
		league.setFreeAgents(freeAgentList);
		Assert.assertTrue(league.getFreeAgents().size() == 1);
	}
	
	@Test
	public void getFreeAgentsTest() {
		League league = populateLeagueObject();
		Assert.assertTrue(league.getFreeAgents().size() ==1);
		Assert.assertEquals("Player 1", league.getFreeAgents().get(0).getPlayerName());
	}
	
	@Test
	public void isLeagueNameValidTest() {
		League league = populateLeagueObject();
		ILeagueDb leagueDb = new LeagueDbMock();
		Assert.assertTrue(league.isLeagueNameValid(league.getLeagueName(), leagueDb));
		league.setLeagueName("NHL");
		Assert.assertFalse(league.isLeagueNameValid(league.getLeagueName(), leagueDb));
	}
	
	@Test
	public void insertLeagueObjectTest() {
		League league = populateLeagueObject();
		ILeagueDb leagueDb = new LeagueDbMock();
		boolean leagueId = league.insertLeagueObject(league, leagueDb);
		Assert.assertTrue(leagueId);
	}

}
