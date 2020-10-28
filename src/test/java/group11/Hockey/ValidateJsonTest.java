package group11.Hockey;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.net.URL;

import org.junit.Test;

import group11.Hockey.db.League.ILeagueDb;



public class ValidateJsonTest {

	@Test
	public void validateJsonWithoutExceptionTest() {
		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		
		URL jsonFile = getClass().getClassLoader().getResource("HockeyTeam.json");
		ValidateJson validateJsonClass = new ValidateJson(leagueDbMock);
		
		
		boolean isValid = validateJsonClass.isValidJsonSchema(jsonFile.getPath());
		assertTrue(isValid);
	}
	
	@Test
	public void validateJsonWithExceptionTest() {
		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		ValidateJson validateJsonClass = new ValidateJson(leagueDbMock);
		
		
		boolean isValid = validateJsonClass.isValidJsonSchema("//nofile.json");
		assertFalse(isValid);
	}
	
	@Test
	public void validateJsonWithInCorrectJsonTest() {
		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		URL jsonFile = getClass().getClassLoader().getResource("HockeyTeamInvalid.json");
		ValidateJson validateJsonClass = new ValidateJson(leagueDbMock);
		
		
		boolean isValid = validateJsonClass.isValidJsonSchema(jsonFile.getPath());
		assertFalse(isValid);
	}

}
