package group11.Hockey;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

import group11.Hockey.models.League;

public class ImportJsonTest {

	@Test
	public void parseFileTest() throws Exception {
		URL jsonFile = getClass().getClassLoader().getResource("HockeyTeam.json");

		ImportJson importJsonObj = new ImportJson();

		League leagueModelObj = null;

		leagueModelObj = importJsonObj.parseFile(jsonFile.getPath());

		assertEquals(leagueModelObj.getLeagueName(), "Dalhousie Hockey League");
		assertEquals(leagueModelObj.getConferences().size(), 1);
	}

	@Test(expected = Exception.class)
	public void parseFileDuplicateConfirenceNameTest() throws Exception {
		URL jsonFile = getClass().getClassLoader().getResource("HockeyTeamInvalid.json");
		ImportJson importJsonObj = new ImportJson();
		importJsonObj.parseFile(jsonFile.getPath());
	    
		
		
	}

}
