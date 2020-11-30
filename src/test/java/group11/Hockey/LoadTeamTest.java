package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.db.League.ILeagueDb;

public class LoadTeamTest {

	@Test
	public void renderTeamTest() {
		String teamName = "Boston";

		ICommandLineInput userInputMode = mock(ICommandLineInput.class);
		when(userInputMode.getValueFromUser()).thenReturn(teamName);

		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		DefaultHockeyFactory.makeLoadTeam(userInputMode, leagueDbMock);

		Assert.assertFalse(teamName == "NHL");

	}

}
