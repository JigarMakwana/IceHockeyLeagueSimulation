package group11.Hockey.BusinessLogic.Trophy.Interfaces;

public interface ITrophySubject {

	void addObserver(ITrophyObserver observer);

	void removeObserver(ITrophyObserver observer);

	void notifyObservers();

}