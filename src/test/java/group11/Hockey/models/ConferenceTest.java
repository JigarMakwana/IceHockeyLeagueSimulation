package group11.Hockey.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;

import org.junit.Assert;

public class ConferenceTest {
	
	LeagueTest leagueTest = new LeagueTest();
	List<Conference> conferenceList = leagueTest.populateLeagueObject().getConferences();
	Conference conference = new Conference();
	
	@Test
	public void ConferenceConstructorTest() {
		Conference conference = new Conference();
		Assert.assertNull(conference.getDivisions());
		Assert.assertNull(conference.getConferenceName());
	}
	
	@Test
	public void ConferenceParameterisedConstructorTest() {
		Conference conference = new Conference("Westeren Conference", Arrays.asList(new Division()));
		Assert.assertEquals("Westeren Conference", conference.getConferenceName());
		Assert.assertTrue(conference.getDivisions().size()==1);
	}
	
	@Test
	public void setConferenceNameTest() {
		Conference conference = new Conference();
		conference.setConferenceName("Westeren Conference");
		Assert.assertEquals("Westeren Conference", conference.getConferenceName());
	}
	

	@Test
	public void getConferenceNameTest() {
		Conference conference = new Conference("Westeren Conference", null);
		Assert.assertEquals("Westeren Conference", conference.getConferenceName());
	}
	
	@Test
	public void setDivisionsTest() {
		Conference conference = new Conference();
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

		Conference conference = new Conference("Westeren Conference", divisionsList);
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
		Assert.assertTrue(conference.getConferencefromConferenceName("Westeren Conference", conferenceList).getConferenceName().equalsIgnoreCase(conferenceName));
		Assert.assertNull(conference.getConferencefromConferenceName("Eastern Conference", conferenceList));
	}

}
