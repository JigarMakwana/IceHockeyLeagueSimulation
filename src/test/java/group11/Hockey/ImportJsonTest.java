package group11.Hockey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.net.URL;

import org.junit.Test;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.JsonParsing.JsonImport;
import group11.Hockey.db.League.ILeagueDb;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Assert;

public class ImportJsonTest {

	@Test
	public void parseFileTest() throws Exception {
		URL jsonFile = getClass().getClassLoader().getResource("HockeyTeam.json");

		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		when(leagueDbMock.checkLeagueNameExitsInDb("Dalhousie Hockey League")).thenReturn(true);
		
		JsonImport importJsonObj = new JsonImport(leagueDbMock);

		League leagueModelObj = null;

		leagueModelObj = importJsonObj.parseFile(jsonFile.getPath());

		assertEquals(leagueModelObj.getLeagueName(), "Dalhousie Hockey League");
		assertEquals(leagueModelObj.getConferences().size(), 1);
	}

	@Test(expected = Exception.class)
	public void parseFileDuplicateConfirenceNameTest() throws Exception {
		URL jsonFile = getClass().getClassLoader().getResource("HockeyTeamInvalid.json");
		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		when(leagueDbMock.checkLeagueNameExitsInDb("Dalhousie Hockey League")).thenReturn(true);
		JsonImport importJsonObj = new JsonImport(leagueDbMock);
		importJsonObj.parseFile(jsonFile.getPath());
	    
		
		
	}

}
