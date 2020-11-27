package group11.Hockey;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.db.ISerialize;

public class SerializeLeagueTest {

	@Test
	public void serializeLeagueObjectTest() {
		ISerialize serializeLeague = DefaultHockeyFactory.makeSerializeLeague();
		LeagueModelMock leagueMock = new LeagueModelMock();
		ILeague leagueObj = leagueMock.getLeagueInfo();
		serializeLeague.serializeLeagueObject(leagueObj);
		File leagueFile = new File("./league.json");
		boolean fileExistsCheck = leagueFile.exists();
		Assert.assertTrue(fileExistsCheck);
	}
}
