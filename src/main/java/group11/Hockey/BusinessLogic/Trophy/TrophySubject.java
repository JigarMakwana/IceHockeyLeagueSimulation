package group11.Hockey.BusinessLogic.Trophy;

import java.util.*;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophySubject;

public abstract class TrophySubject implements ITrophySubject {

	private final List<ITrophyObserver> observers;
	
	public TrophySubject() {
		observers = new ArrayList<ITrophyObserver>();	
	}
	
	@Override
	public void addObserver(ITrophyObserver observer)
	{
		observers.add(observer);
	}
	
	@Override
	public void removeObserver(ITrophyObserver observer)
	{
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers()
	{
		List<ITrophyObserver> iter = observers;
		int observer=iter.size();
		for(int i=0;i<observer;i++)
		{
			iter.get(i).AwardTrophy();
		}
	}




}
