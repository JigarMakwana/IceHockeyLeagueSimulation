package group11.Hockey.InputOutput;

import group11.Hockey.BusinessLogic.models.League;

public interface IDisplay {
	
	public void showMessageOnConsole(String message);
	
	public void displayListOfGeneralMangers(League league);
	
	public void displayListOfCoaches(League league);
	
	public void displayListOfPLayers(League league);
}
