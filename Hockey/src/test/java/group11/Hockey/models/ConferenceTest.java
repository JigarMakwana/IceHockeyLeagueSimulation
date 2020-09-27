package group11.Hockey.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class ConferenceTest {

	@Test
	public void getConferenceNameTest() {
		Conference conference = new Conference("Westeren Conference", null);
		Assert.assertEquals("Westeren Conference", conference.getConferenceName());
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
	}

	@Test
	public void divisionsInConferenceListSizeTest() {
		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic Division", null);
		Division centralDivision = new Division("Central Division", null);
		divisionsList.add(atlanticDivision);
		divisionsList.add(centralDivision);
		Conference conference = new Conference("Westeren Conference", divisionsList);
		Assert.assertTrue(conference.getDivisions().size() == 2);
	}

}
