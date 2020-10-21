package group11.Hockey;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.models.Aging;
import group11.Hockey.models.GameplayConfig;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;

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
		GameplayConfig gamePlay = league.getGamePlayConfig();
		Aging age = gamePlay.getAging();
		Assert.assertEquals(age.getMaximumAge(), 50);

	}

}
