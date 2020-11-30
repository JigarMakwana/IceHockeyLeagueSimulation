package group11.Hockey.db;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;

public class DeserializeTest {

	
	@Test
	public void deSerializeLeagueObjectFromFile() {
		DefaultHockeyFactory.makeDeserializeLeague();
		LeagueModelMock leagueMock = new LeagueModelMock();
		Division division = leagueMock.getLeagueInfo().getConferences().get(0).getDivisions().get(0);
		Assert.assertTrue(leagueMock.getLeagueInfo().getConferences().size() == 1);
		Assert.assertTrue(leagueMock.getLeagueInfo().getConferences().get(0).getConferenceName().equalsIgnoreCase("Eastern Conference"));
		Assert.assertTrue(division.getDivisionName().equalsIgnoreCase("Atlantic"));
	}
}
