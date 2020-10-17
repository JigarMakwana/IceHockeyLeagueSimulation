package group11.Hockey;

import org.json.simple.JSONObject;

import group11.Hockey.models.League;

public interface IParseRootElement {
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception; 
}
