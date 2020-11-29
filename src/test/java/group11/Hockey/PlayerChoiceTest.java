package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.PlayerChoice;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

public class PlayerChoiceTest {

	@Test
	public void startStateTest() {
		ICommandLineInput commandLineMock = mock(ICommandLineInput.class);
		IDisplay display = DefaultHockeyFactory.makeDisplay();
		when(commandLineMock.getValueFromUser()).thenReturn("1");
		PlayerChoice playerChoice = (PlayerChoice) DefaultHockeyFactory.makePlayerChoice(null, commandLineMock, null, display);
		playerChoice.startState();
		int seasons = Integer.parseInt("1");
		Assert.assertTrue(seasons == 1);
	}
}
