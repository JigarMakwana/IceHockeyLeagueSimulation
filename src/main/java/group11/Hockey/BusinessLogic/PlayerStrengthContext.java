/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

public class PlayerStrengthContext {
	private IPlayerStrengthStrategy currentContext;

	public PlayerStrengthContext(IPlayerStrengthStrategy currentContext) {
		super();
		this.currentContext = currentContext;
	}

	public float executeStrategy() {
		return currentContext.claculateStrength();
	}
}
