package group11.Hockey.db;

import java.io.FileReader;

import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.League;
/**
 * 
 * @author Jatin Partap Rana
 *
 */
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
			JSONParser jsonParser = DefaultHockeyFactory.makeGsonJsonParser();
			String value = jsonParser.parse(reader).toString();
			Gson gson = DefaultHockeyFactory.makeGson();
			league = gson.fromJson(value, League.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return league;
	}
}
