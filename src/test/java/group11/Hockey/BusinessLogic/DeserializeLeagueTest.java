package group11.Hockey.BusinessLogic;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.DeserializeLeague;
import group11.Hockey.InputOutput.IDeserializeLeague;

public class DeserializeLeagueTest {

	
	@Test
	public void deSerializeLeagueObjectFromFileTest() {
		IDeserializeLeague deserializeLeague = new DeserializeLeague();
		League league = deserializeLeague.deSerializeLeagueObjectFromFile();
		Division division = league.getConferences().get(0).getDivisions().get(0);
		Assert.assertTrue(league.getConferences().size() == 1);
		Assert.assertTrue(league.getConferences().get(0).getConferenceName().equalsIgnoreCase("Eastern Conference"));
		Assert.assertTrue(division.getDivisionName().equalsIgnoreCase("Atlantic"));
		Assert.assertTrue(league.getFreeAgents().size() == 20);
		Assert.assertTrue(league.getCoaches().size() == 1);
	}
}
