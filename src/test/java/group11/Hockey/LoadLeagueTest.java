package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.ILoadLeague;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.db.League.ILeagueDb;

public class LoadLeagueTest {

	@Test
	public void loadLeagueWithTeamNameTest() {
		String teamName = "Boston";

		ICommandLineInput userInputMode = mock(ICommandLineInput.class);
		when(userInputMode.getValueFromUser()).thenReturn(teamName);

		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		ILoadLeague loadLeague = (ILoadLeague) DefaultHockeyFactory.makeLoadTeam(userInputMode, leagueDbMock);
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
