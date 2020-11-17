package group11.Hockey.db;

import java.io.FileReader;

import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import group11.Hockey.BusinessLogic.models.League;

public class Deserialize implements IDeserialize {
	
	private static Deserialize deserializeLeagueInstance = null;
	
	private Deserialize() {
		
	}
	
	public static Deserialize getInstance()
	{
		if(deserializeLeagueInstance == null) {
			deserializeLeagueInstance = new Deserialize();
		}
		return deserializeLeagueInstance;
	}

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
			e.printStackTrace();
			System.out.println("Eception occurred while deSerializing the object:" + e.getMessage());
		}
		return league;
	}
}
