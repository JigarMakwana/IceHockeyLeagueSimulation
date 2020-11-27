package group11.Hockey.InputOutput.JsonParsing;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.IAging;
import group11.Hockey.BusinessLogic.models.IGameplayConfig;
import group11.Hockey.BusinessLogic.models.League;

public class ParseRootgameplayConfigTest {

	private static JSONObject jsonObject;

	@BeforeClass
	public static void init() throws FileNotFoundException, IOException, ParseException {
		ParseJsonForTestMock parseJsonObj = new ParseJsonForTestMock();
		jsonObject = parseJsonObj.parseJson();
	}

	@Test
	public void parseRootElementTest() throws Exception {
		League league = new League();
		ParseRootgameplayConfig parseRoot = new ParseRootgameplayConfig();
		parseRoot.parseRootElement(league, jsonObject);
		IGameplayConfig gamePlay = league.getGamePlayConfig();
		IAging age = gamePlay.getAging();
		Assert.assertEquals(age.getMaximumAge(), 47);

	}

}
