package group11.Hockey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ValidateJson {
	public boolean validateJson(String jsonFile, String jsonSchemaFile) {
		File file1 = new File(jsonFile);
		File file2 = new File(jsonSchemaFile);
		InputStream inputStreamJson = null;
		InputStream inputStreamJsonSchema = null;
		try {
			inputStreamJson = new FileInputStream(file1);
			inputStreamJsonSchema = new FileInputStream(file2);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			return false;
		}

		JSONObject rawSchema = new JSONObject(new JSONTokener(inputStreamJsonSchema));
		Schema schema = SchemaLoader.load(rawSchema);
		try {
			schema.validate(new JSONObject(new JSONTokener(inputStreamJson)));
			return true;
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			//e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(System.out::println);
		}
		return false;
	}
}
