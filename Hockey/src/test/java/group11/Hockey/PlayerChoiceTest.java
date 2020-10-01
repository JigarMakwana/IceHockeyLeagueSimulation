package group11.Hockey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import junit.framework.Assert;

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
