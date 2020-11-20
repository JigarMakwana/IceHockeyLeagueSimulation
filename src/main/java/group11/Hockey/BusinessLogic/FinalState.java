package group11.Hockey.BusinessLogic;

public class FinalState extends StateMachineState {
	
	private static FinalState finalStateInstance = null;
	
	private FinalState() {
		
	}
	public static FinalState getInstance() {
		if(finalStateInstance == null) {
			finalStateInstance = new FinalState();
		}
		return finalStateInstance;
	}
	@Override
	public StateMachineState startState() {
		return null;
	}

	public boolean ShouldContinue() {
		return false;
	}

}
