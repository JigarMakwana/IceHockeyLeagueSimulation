package group11.Hockey.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class LeagueTest {

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
	public void getLeagueNameTest() {
		League league = new League("DHL", null, null);
		Assert.assertEquals("DHL", league.getLeagueName());
	}

	@Test
	public void getConferencesTest() {
		List<Conference> conferenceList = new ArrayList<Conference>();
		Conference westernConference = new Conference("Westeren Conference", null);
		Conference easternConference = new Conference("Eastern Conference", null);
		conferenceList.add(westernConference);
		conferenceList.add(easternConference);

		League league = new League("DHL", conferenceList, null);

		Assert.assertEquals("Conference [conferenceName=" + westernConference.getConferenceName() + ", divisions="
				+ westernConference.getDivisions() + "]", league.getConferences().get(0).toString());
		Assert.assertEquals("Conference [conferenceName=" + easternConference.getConferenceName() + ", divisions="
				+ easternConference.getDivisions() + "]", league.getConferences().get(1).toString());

		Assert.assertTrue(league.getConferences().size() == 2);
	}

}
