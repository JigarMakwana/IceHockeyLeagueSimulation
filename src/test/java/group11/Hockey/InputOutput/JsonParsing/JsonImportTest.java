package group11.Hockey.InputOutput.JsonParsing;

import static org.mockito.Mockito.mock;
import java.net.URL;
import org.junit.Test;
import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.db.League.ILeagueDb;
import junit.framework.Assert;

public class JsonImportTest {

	@Test
	public void startStateTest() throws Exception {
		URL jsonFile = getClass().getClassLoader().getResource("HockeyTeamInvalid.json");
		ILeagueDb leagueDbMock = mock(ILeagueDb.class);
		ICommandLineInput commandLineMock = mock(ICommandLineInput.class);

		JsonImport importJsonObj = DefaultHockeyFactory.getJsonImport(jsonFile.getFile(), commandLineMock,
				leagueDbMock);
		StateMachineState league = importJsonObj.startState();
		Assert.assertNull(league);
	}

}
