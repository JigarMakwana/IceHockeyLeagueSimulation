/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.InputOutput.JsonParsing;

import java.io.FileReader;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class JsonImport extends ValidateJsonSchema {

	private Object fileObj;
	String fileName;
	ICommandLineInput commandLineInput;
	ILeagueDb leagueDb;
	IDisplay display;
	private static Logger logger = LogManager.getLogger(JsonImport.class);

	public JsonImport(String fileName, ICommandLineInput commandLineInput, ILeagueDb leagueDb, IDisplay display) {
		this.fileName = fileName;
		this.commandLineInput = commandLineInput;
		this.leagueDb = leagueDb;
		this.display = display;
	}
	
	

	@Override
	public StateMachineState startState() {
		StateMachineState stateMachineState = null;
		try {
			League league = parseFile(fileName);
			stateMachineState = DefaultHockeyFactory.makeCreateTeam(league, commandLineInput, leagueDb, display);
		} catch (Exception e) {
			logger.error("Exception occurred :" + e.getMessage());
		}
		return stateMachineState;
	}

	private League parseFile(String fileName) throws Exception {
		if (isValidJsonSchema(fileName)) {
			JSONParser parser = DefaultHockeyFactory.makeJSONParser();
			fileObj = parser.parse(new FileReader(fileName));
			League leagueModelObj = DefaultHockeyFactory.makeLeague();
			JSONObject jsonObject = (JSONObject) fileObj;

			for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (key.equalsIgnoreCase(Attributes.LEAGUENAME.getAttribute())) {
					String leagueName = (String) jsonObject.get(key);

					leagueModelObj.setLeagueName(leagueName);
				} else {
					Class loadedClass = Class.forName("group11.Hockey.InputOutput.JsonParsing.ParseRoot" + key);
					IParseRootElement parseRootElem = (IParseRootElement) loadedClass.newInstance();
					parseRootElem.parseRootElement(leagueModelObj, jsonObject);
				}
			}
			return leagueModelObj;
		} else {
			throw DefaultHockeyFactory.makeExceptionCall("Exception in the schema");
		}
	}

}
