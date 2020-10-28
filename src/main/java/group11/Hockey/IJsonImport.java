package group11.Hockey;

import group11.Hockey.models.League;

public interface IJsonImport {
	
	public League parseFile(String fileName) throws Exception ;

}
