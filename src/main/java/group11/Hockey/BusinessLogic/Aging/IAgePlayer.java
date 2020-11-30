package group11.Hockey.BusinessLogic.Aging;

import group11.Hockey.BusinessLogic.StateMachineState;

public interface IAgePlayer {

	StateMachineState startState();

	void agePlayers();

}