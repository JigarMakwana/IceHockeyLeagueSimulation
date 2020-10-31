package group11.Hockey;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.JsonParsing.ParseRootcoaches;
import org.junit.Assert;
import org.junit.BeforeClass;

public class ParseRootcoachesTest {

	private static JSONObject jsonObject;

	@BeforeClass
	public static void init() throws FileNotFoundException, IOException, ParseException {
		ParseJsonForTestMock parseJsonObj = new ParseJsonForTestMock();
		jsonObject = parseJsonObj.parseJson();
	}

	@Test
	public void parseRootElementTest() throws Exception {
		League league = new League();
		ParseRootcoaches parseRootcoaches = new ParseRootcoaches();
		parseRootcoaches.parseRootElement(league, jsonObject);
		List<Coach> lc = league.getCoaches();
		Assert.assertEquals(lc.size(), 3);

	}

}
