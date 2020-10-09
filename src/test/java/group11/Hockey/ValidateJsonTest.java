package group11.Hockey;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;



public class ValidateJsonTest {

	@Test
	public void validateJsonWithoutExceptionTest() {
		URL jsonFile = getClass().getClassLoader().getResource("HockeyTeam.json");
		ValidateJson validateJsonClass = new ValidateJson();
		
		
		boolean isValid = validateJsonClass.validateJson(jsonFile.getPath());
		assertTrue(isValid);
	}
	
	@Test
	public void validateJsonWithExceptionTest() {
		ValidateJson validateJsonClass = new ValidateJson();
		
		
		boolean isValid = validateJsonClass.validateJson("//nofile.json");
		assertTrue(!isValid);
	}
	
	@Test
	public void validateJsonWithInCorrectJsonTest() {
		URL jsonFile = getClass().getClassLoader().getResource("HockeyTeamInvalid.json");
		ValidateJson validateJsonClass = new ValidateJson();
		
		
		boolean isValid = validateJsonClass.validateJson(jsonFile.getPath());
		assertTrue(!isValid);
	}

}
