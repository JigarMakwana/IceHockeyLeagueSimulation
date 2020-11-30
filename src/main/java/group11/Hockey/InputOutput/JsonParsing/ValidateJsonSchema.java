/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.InputOutput.JsonParsing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import group11.Hockey.App;
import group11.Hockey.BusinessLogic.StateMachineState;

public abstract class ValidateJsonSchema extends StateMachineState {
	private static Logger logger = LogManager.getLogger(ValidateJsonSchema.class);

	public ValidateJsonSchema() {
		super();
	}

	public boolean isValidJsonSchema(String jsonFilePath) throws Exception {
		File jsonFile = new File(jsonFilePath);
		InputStream inputStreamJson = null;
		InputStream inputStreamJsonSchema = App.class.getResourceAsStream("/HockeyTeamJsonSchema.json");

		try {
			inputStreamJson = new FileInputStream(jsonFile);

		} catch (FileNotFoundException e) {
			logger.error(jsonFilePath + "File not found " + e.getMessage());
			throw new Exception();
		}

		JSONObject jsonSchema = new JSONObject(new JSONTokener(inputStreamJsonSchema));
		Schema schema = SchemaLoader.load(jsonSchema);
		try {
			schema.validate(new JSONObject(new JSONTokener(inputStreamJson)));
			return true;
		} catch (ValidationException e) {
			logger.error("Exception: " + e.getMessage());
			e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(System.out::println);
			throw new Exception();
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw new Exception();
		}
	}

}
