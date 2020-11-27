package group11.Hockey.InputOutput.JsonParsing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;

public class ParseRootfreeAgentsTest {

	private static JSONObject jsonObject;

	@BeforeClass
	public static void init() throws FileNotFoundException, IOException, ParseException {
		ParseJsonForTestMock parseJsonObj = new ParseJsonForTestMock();
		jsonObject = parseJsonObj.parseJson();
	}

	@Test
	public void parseRootElementTest() throws Exception {
		League league = new League();
		ParseRootfreeAgents parseRoot = new ParseRootfreeAgents();
		parseRoot.parseRootElement(league, jsonObject);
		List<Player> lc = (List<Player>) league.getFreeAgents();
		Assert.assertEquals(lc.size(), 51);
	}

}
