/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.InputOutput.JsonParsing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.InputOutput.DefaultInputOutputFactory;

public class ParseRootConferenceTest {

	private static JSONObject jsonObject;

	@BeforeClass
	public static void init() throws FileNotFoundException, IOException, ParseException {
		IParseJsonForTestMock parseJsonObj = DefaultParseTestFactory.makeParseJsonForTestMock();
		jsonObject = parseJsonObj.parseJson();
	}

	@Test
	public void parseRootElementTest() throws Exception {
		ILeague league = DefaultHockeyFactory.makeLeague();
		IParseRootElement parseRoot = DefaultInputOutputFactory.makeParseRootconferences();
		parseRoot.parseRootElement(league, jsonObject);
		List<IConference> lc = league.getConferences();
		Assert.assertEquals(lc.size(), 2);

	}

}
