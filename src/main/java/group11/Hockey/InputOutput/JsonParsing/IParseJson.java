package group11.Hockey.InputOutput.JsonParsing;

import java.util.List;

import org.json.simple.JSONObject;

public interface IParseJson {

	public <T> List<T> parseElement(JSONObject jsonObj) throws Exception;
}
