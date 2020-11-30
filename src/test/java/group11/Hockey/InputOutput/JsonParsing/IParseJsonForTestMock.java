package group11.Hockey.InputOutput.JsonParsing;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface IParseJsonForTestMock {
	public JSONObject parseJson() throws FileNotFoundException, IOException, ParseException;
}
