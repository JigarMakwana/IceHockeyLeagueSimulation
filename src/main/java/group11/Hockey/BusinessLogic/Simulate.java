package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.models.InitializeSeason;

public class Simulate extends StateMachineState {
	private ILeague league;
	private int seasons;

	public Simulate(ILeague league, int seasons) {
		super();
		this.league = league;
		this.seasons = seasons;
	}

	@Override
	public StateMachineState startState() {
		while (seasons > 0) {
			StateMachineState currentState = new InitializeSeason(league);
			do {
				currentState = currentState.startState();
			} while (currentState.ShouldContinue());
			seasons--;
		}

		return new FinalState();
	}

}
