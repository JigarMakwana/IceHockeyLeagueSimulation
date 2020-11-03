package group11.Hockey.InputOutput.JsonParsing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import group11.Hockey.App;
import group11.Hockey.db.League.ILeagueDb;

public class ValidateJsonSchema extends ValidateJsonAttributes{
	
	public ValidateJsonSchema() {
		super();
	}
	
	public ValidateJsonSchema(ILeagueDb leagueDb) {
		super(leagueDb);
	}

	public boolean isValidJsonSchema(String jsonFilePath) {
		File jsonFile = new File(jsonFilePath);
		InputStream inputStreamJson = null;
		InputStream inputStreamJsonSchema = App.class.getResourceAsStream("/HockeyTeamJsonSchema.json");

		try {
			inputStreamJson = new FileInputStream(jsonFile);

		} catch (FileNotFoundException e) {
			System.out.println(e);
			return false;
		}

		JSONObject jsonSchema = new JSONObject(new JSONTokener(inputStreamJsonSchema));
		Schema schema = SchemaLoader.load(jsonSchema);
		try {
			schema.validate(new JSONObject(new JSONTokener(inputStreamJson)));
			return true;
		} catch (ValidationException e) {
			System.out.println("Exception: "+e.getMessage() );
			e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(System.out::println);
		}
		catch (Exception e) {
			System.out.println("Exception: "+e.getMessage() );
		}
		return false;
	}
	
	}
