package group11.Hockey;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.ISerialize;
import group11.Hockey.BusinessLogic.SerializeLeague;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.models.LeagueModelMock;

public class SerializeLeagueTest {

	
	@Test
	public void serializeLeagueObjectTest() {
		ISerialize serializeLeague =  new SerializeLeague();
		LeagueModelMock leagueMock = new LeagueModelMock();
		League leagueObj = leagueMock.getLeagueInfo();
		serializeLeague.serializeLeagueObject(leagueObj);
		File leagueFile = new File("./league.json");
		boolean fileExistsCheck = leagueFile.exists();
		Assert.assertTrue(fileExistsCheck);
	}
}
