package group11.Hockey;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

import group11.Hockey.models.League;

public class ImportJsonTest {

	@Test
	public void parseFileTest() {
		URL jsonFile = getClass().getResource("HockeyTeam.json");
		
		ImportJson importJsonObj = new ImportJson();
		
		
		League leagueModelObj = null;
		try {
			leagueModelObj = importJsonObj.parseFile(jsonFile.getPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(leagueModelObj.getLeagueName(),"Dalhousie Hockey League");
		assertEquals(leagueModelObj.getConferences().size(), 1);
	}
	
	@Test
	public void parseFileDuplicateConfirenceNameTest() {
		URL jsonFile = getClass().getResource("HockeyTeamInvalid.json");
		ImportJson importJsonObj = new ImportJson();
		
		Exception exception = assertThrows(Exception.class, () -> {
			importJsonObj.parseFile(jsonFile.getPath());
	    });
		
		String exceptionMessage = "Division name Atlantic already exists";
	    String actualMessage = exception.getMessage();
	    
	    assertEquals(exceptionMessage, actualMessage);
	}

}
