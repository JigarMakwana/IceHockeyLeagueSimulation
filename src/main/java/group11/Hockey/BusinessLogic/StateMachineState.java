package group11.Hockey.BusinessLogic;

public abstract class StateMachineState {
	public abstract StateMachineState startState();

	public boolean ShouldContinue() {
		return true;
	}
}
