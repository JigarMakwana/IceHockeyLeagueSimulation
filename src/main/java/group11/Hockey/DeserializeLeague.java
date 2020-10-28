package group11.Hockey;

import java.io.FileReader;

import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import group11.Hockey.models.League;

public class DeserializeLeague implements IDeserializeLeague {

	public League deSerializeLeagueObjectFromFile() {
		FileReader reader;
		League league = null;
		try {
			reader = new FileReader("./league.json");
			JSONParser jsonParser = new JSONParser();
			String value = jsonParser.parse(reader).toString();
			Gson gson = new Gson();
			league = gson.fromJson(value, League.class);
			System.out.println(league.toString());
		} catch (Exception e) {
			System.out.println("Eception occurred while deSerializing the object:" + e.getMessage());
		}
		return league;
	}
}
