package group11.Hockey;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

public class PlayerChoiceTest {

	@Test
	public void getNumberOfSeasonsToSimulateTest() {
		
		IUserInputMode userInputMode = mock(IUserInputMode.class);
		when(userInputMode.getInt()).thenReturn(1);
		
		PlayerChoice playerChoice = new PlayerChoice(userInputMode);
		int nuOfSeasons = playerChoice.getNumberOfSeasonsToSimulate();
		Assert.assertEquals(userInputMode.getInt(), nuOfSeasons);
		
	}

}
