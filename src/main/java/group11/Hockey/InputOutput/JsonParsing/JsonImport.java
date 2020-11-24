/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.InputOutput.JsonParsing;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.db.League.ILeagueDb;

public class JsonImport extends ValidateJsonSchema {

	private Object fileObj;
	String fileName;
	ICommandLineInput commandLineInput;
	ILeagueDb leagueDb;

	public JsonImport(String fileName, ICommandLineInput commandLineInput, ILeagueDb leagueDb) {
		this.fileName = fileName;
		this.commandLineInput = commandLineInput;
		this.leagueDb = leagueDb;
	}

	public JsonImport() {
	}

	@Override
	public StateMachineState startState() {
		StateMachineState stateMachineState = null;
		try {
			League league = parseFile(fileName);
			stateMachineState = DefaultHockeyFactory.makeCreateTeam(league, commandLineInput, leagueDb);
		} catch (Exception e) {
			System.out.println("Exception occurred :" + e.getMessage());
		}
		return stateMachineState;
	}

	public League parseFile(String fileName) throws Exception {
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
