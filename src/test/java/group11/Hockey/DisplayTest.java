package group11.Hockey;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.InputOutput.IDisplay;

public class DisplayTest {
	@Test
	public void showMessageOnConsoleTest() {
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IDisplay display = DefaultHockeyFactory.makeDisplay();
		display.showMessageOnConsole("Welcome to hockey simulation");
		Assert.assertTrue(league.getLeagueName().equalsIgnoreCase("Dalhousie Hockey League"));
	}
	
	@Test
	public void displayListOfGeneralMangersTest() {
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IDisplay display = DefaultHockeyFactory.makeDisplay();
		display.displayListOfGeneralMangers(league);
		Assert.assertTrue(league.getLeagueName().equalsIgnoreCase("Dalhousie Hockey League"));
	}
	
	@Test
	public void displayListOfCoachesTest() {
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IDisplay display = DefaultHockeyFactory.makeDisplay();
		display.displayListOfCoaches(league);
		Assert.assertTrue(league.getConferences().size() > 0);
	}
	
	@Test
	public void displayListOfPLayersTest() {
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IDisplay display = DefaultHockeyFactory.makeDisplay();
		display.displayListOfPLayers(league);
		Assert.assertTrue(league.getConferences().size() > 0);
	}
}
