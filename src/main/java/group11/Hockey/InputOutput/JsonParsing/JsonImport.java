package group11.Hockey.InputOutput.JsonParsing;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.db.League.ILeagueDb;

public class JsonImport extends ValidateJsonSchema {

	private Object fileObj;

	public JsonImport(ILeagueDb leagueDb) {
		super(leagueDb);
	}

	public League parseFile(String fileName) throws Exception {
		if (isValidJsonSchema(fileName)) {
			JSONParser parser = new JSONParser();
			fileObj = parser.parse(new FileReader(fileName));
			League leagueModelObj = new League();
			JSONObject jsonObject = (JSONObject) fileObj;

			for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (key.equalsIgnoreCase(Attributes.LEAGUENAME.getAttribute())) {
					String leagueName = (String) jsonObject.get(key);
					if (isValidLeagueName(leagueName)) {
						leagueModelObj.setLeagueName(leagueName);
					} else {
						throw new Exception(leagueName + " -> League name already exists in the system");
					}
				} else {
					Class loadedClass = Class.forName("group11.Hockey.InputOutput.JsonParsing.ParseRoot" + key);
					IParseRootElement parseRootElem = (IParseRootElement) loadedClass.newInstance();
					parseRootElem.parseRootElement(leagueModelObj, jsonObject);
				}
			}
			return leagueModelObj;
		} else {
			throw new Exception("Exception in the schema");
		}
	}
}
