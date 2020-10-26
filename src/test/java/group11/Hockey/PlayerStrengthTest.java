package group11.Hockey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

public class PlayerStrengthTest {

	@Test
	public void calculatePlayerStrengthTest() {
		IPosition position = mock(IPosition.class);
		when(position.claculateStrength()).thenReturn((float) 20);

		PlayerStrength playerStrength = new PlayerStrength();
		float strength = playerStrength.calculatePlayerStrength(position);
		Assert.assertEquals(strength, 20, 20);
	}

}
