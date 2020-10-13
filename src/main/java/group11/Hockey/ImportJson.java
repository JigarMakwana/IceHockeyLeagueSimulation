package group11.Hockey;

import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.models.Conference;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;

public class ImportJson extends ValidateJson {

	private Object fileObj;

	public ImportJson(ILeagueDb leagueDb) {
		super(leagueDb);
	}

	public League parseFile(String fileName) throws Exception {
		if (validateJsonSchema(fileName)) {
			JSONParser parser = new JSONParser();

			fileObj = parser.parse(new FileReader(fileName));

			leagueModelObj = new League();
			JSONObject jsonObject = (JSONObject) fileObj;
			// parse league name
			String leagueName = (String) jsonObject.get("leagueName");
			if (isValidLeagueName(leagueName)) {
				leagueModelObj.setLeagueName(leagueName);
			} else {
				throw new Exception(leagueName + " -> League name already exists in the system");
			}

			ParseJson parseJson = new ParseJson();

			// parse Conference
			List<Conference> conferencesList = parseJson.parseElement(Conference.class, jsonObject,
					new ParseConference());
			leagueModelObj.setConferences(conferencesList);

			// parse freeAgents
			List<Player> playersList = parseJson.parseElement(Player.class, jsonObject, new ParseFreeAgent());
			leagueModelObj.setFreeAgents(playersList);

			return leagueModelObj;
		} else {
			throw new Exception("Exception in the schema");
		}
	}

}
