package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;

import org.junit.Assert;

public class ConferenceTest {

	LeagueTest leagueTest = new LeagueTest();
	List<IConference> conferenceList = leagueTest.populateLeagueObject().getConferences();
	IConference conference = DefaultHockeyFactory.makeConference();

	@Test
	public void ConferenceConstructorTest() {
		IConference conference = DefaultHockeyFactory.makeConference();
		Assert.assertNull(conference.getDivisions());
		Assert.assertNull(conference.getConferenceName());
	}

	@Test
	public void setConferenceNameTest() {
		IConference conference = DefaultHockeyFactory.makeConference();
		conference.setConferenceName("Westeren Conference");
		Assert.assertEquals("Westeren Conference", conference.getConferenceName());
	}

	@Test
	public void getConferenceNameTest() {
		IConference conference = DefaultHockeyFactory.makeConference("Westeren Conference", null);
		Assert.assertEquals("Westeren Conference", conference.getConferenceName());
	}

	@Test
	public void setDivisionsTest() {
		IConference conference = DefaultHockeyFactory.makeConference();
		conference.setDivisions(Arrays.asList(new Division("Atlantic Division", null)));
		Assert.assertTrue(conference.getDivisions().size() == 1);
		Assert.assertEquals("Atlantic Division", conference.getDivisions().get(0).getDivisionName());
	}

	@Test
	public void getDivisionsTest() {
		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic Division", null);
		Division centralDivision = new Division("Central Division", null);
		divisionsList.add(atlanticDivision);
		divisionsList.add(centralDivision);

		IConference conference = DefaultHockeyFactory.makeConference("Westeren Conference", divisionsList);
		Assert.assertEquals("Division [divisionName=" + atlanticDivision.getDivisionName() + ", teams="
				+ atlanticDivision.getTeams() + "]", conference.getDivisions().get(0).toString());

		Assert.assertEquals("Division [divisionName=" + centralDivision.getDivisionName() + ", teams="
				+ centralDivision.getTeams() + "]", conference.getDivisions().get(1).toString());
		Assert.assertTrue(conference.getDivisions().size() == 2);
	}

	@Test
	public void isConferenceNameValidTest() {
		Assert.assertTrue(conference.isConferenceNameValid("Westeren Conference", conferenceList));
		Assert.assertFalse(conference.isConferenceNameValid("Eastern Conference", conferenceList));

	}

	@Test
	public void getConferenceFromConferenceNameTest() {
		String conferenceName = "Westeren Conference";
		Assert.assertTrue(conference.getConferencefromConferenceName("Westeren Conference", conferenceList)
				.getConferenceName().equalsIgnoreCase(conferenceName));
		Assert.assertNull(conference.getConferencefromConferenceName("Eastern Conference", conferenceList));
	}

}
