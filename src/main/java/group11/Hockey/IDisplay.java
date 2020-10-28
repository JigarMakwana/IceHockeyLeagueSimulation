package group11.Hockey;

import group11.Hockey.models.League;

public interface IDisplay {
	
	public void showMessageOnConsole(String message);
	
	public void displayListOfGeneralMangers(League league);
	
	public void displayListOfCoaches(League league);
	
	public void displayListOfPLayers(League league);
}
