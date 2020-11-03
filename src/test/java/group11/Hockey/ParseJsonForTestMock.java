package group11.Hockey;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJsonForTestMock {
	public JSONObject parseJson() throws FileNotFoundException, IOException, ParseException {
		String fileName = ParseRootcoachesTest.class.getClassLoader().getResource("HockeyTeam.json").getPath();
		JSONParser parser = new JSONParser();
		Object fileObj = parser.parse(new FileReader(fileName));
		JSONObject jsonObject = (JSONObject) fileObj;
		return jsonObject;
	}
}
