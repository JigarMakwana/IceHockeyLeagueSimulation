package group11.Hockey.InputOutput.JsonParsing;

import group11.Hockey.BusinessLogic.models.League;

public interface IJsonImport {
	
	public League parseFile(String fileName) throws Exception ;

}
