package group11.Hockey;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import group11.Hockey.models.League;

public class SerializeLeague implements ISerialize {

	@Override
	public void serializeLeagueObject(League league) {
		Writer writer = null;
		try {
			writer = Files.newBufferedWriter(Paths.get("./league.json"));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(league, writer);
			writer.close();
		} catch (IOException e) {
			System.out.println("Eception occurred while serializing the object:" + e.getMessage());
		}

	}
}
