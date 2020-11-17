package group11.Hockey.db;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import group11.Hockey.BusinessLogic.models.ILeague;

public class Serialize implements ISerialize {

	private static Serialize serializeLeagueInstance = null;

	private Serialize() {

	}

	public static Serialize getInstance() {
		if (serializeLeagueInstance == null) {
			serializeLeagueInstance = new Serialize();
		}
		return serializeLeagueInstance;
	}

	@Override
	public void serializeLeagueObject(ILeague league) {
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
