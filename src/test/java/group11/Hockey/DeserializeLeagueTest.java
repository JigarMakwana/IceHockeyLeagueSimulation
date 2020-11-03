package group11.Hockey;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DeserializeLeague;
import group11.Hockey.BusinessLogic.IDeserializeLeague;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;

public class DeserializeLeagueTest {

	
	@Test
	public void deSerializeLeagueObjectFromFile() {
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
