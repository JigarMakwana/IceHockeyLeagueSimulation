package group11.Hockey;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;



public class ValidateJsonTest {

	@Test
	public void validateJsonWithoutExceptionTest() {
		URL jsonFile = getClass().getResource("HockeyTeam.json");
		URL jsonSchemaFile = getClass().getResource("HockeyTeamJsonSchema.json");
		ValidateJson validateJsonClass = new ValidateJson();
		
		
		boolean isValid = validateJsonClass.validateJson(jsonFile.getPath(), jsonSchemaFile.getPath());
		assertTrue(isValid);
	}
	
	@Test
	public void validateJsonWithExceptionTest() {
		URL jsonSchemaFile = getClass().getResource("HockeyTeamJsonSchema.json");
		ValidateJson validateJsonClass = new ValidateJson();
		
		
		boolean isValid = validateJsonClass.validateJson("//nofile.json", jsonSchemaFile.getPath());
		assertTrue(!isValid);
	}
	
	@Test
	public void validateJsonWithInCorrectJsonTest() {
		URL jsonFile = getClass().getResource("HockeyTeamInvalid.json");
		URL jsonSchemaFile = getClass().getResource("HockeyTeamJsonSchema.json");
		ValidateJson validateJsonClass = new ValidateJson();
		
		
		boolean isValid = validateJsonClass.validateJson(jsonFile.getPath(), jsonSchemaFile.getPath());
		assertTrue(!isValid);
	}

}
