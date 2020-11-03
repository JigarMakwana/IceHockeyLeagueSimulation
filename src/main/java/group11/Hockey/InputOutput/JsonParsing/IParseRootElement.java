package group11.Hockey.InputOutput.JsonParsing;

import org.json.simple.JSONObject;

import group11.Hockey.BusinessLogic.models.League;

public interface IParseRootElement {
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception; 
}
