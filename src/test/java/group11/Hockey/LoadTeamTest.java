package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.ILoadLeague;
import group11.Hockey.BusinessLogic.LoadLeague;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.db.Team.TeamDbMock;


public class LoadTeamTest {

	@Test
	public void loadLeagueWithTeamNameTest(){
		String teamName = "Boston";

		ICommandLineInput userInputMode = mock(ICommandLineInput.class);
		when(userInputMode.getValueFromUser()).thenReturn(teamName);

		ITeamDb teamDbMock = new TeamDbMock();
		ILoadLeague loadLeague = new LoadLeague(userInputMode,teamDbMock);
		League league = null;
		try {
			league = loadLeague.loadLeagueWithTeamName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(league.getLeagueName(), "NHL");
		Assert.assertTrue(league.getConferences().size() == 1);
	}


}
