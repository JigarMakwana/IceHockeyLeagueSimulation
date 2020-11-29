package group11.Hockey.BusinessLogic.Trophy;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.ILeague;

public class EndOfRegularSeasonSubject extends TrophySubject {

	ILeague league;
	
	public EndOfRegularSeasonSubject(ILeague league) {
		super();
		this.league=league;
		ITrophyObserver president=DefaultHockeyFactory.makePresident(league);
		ITrophyObserver participation=DefaultHockeyFactory.makeParticipation(league);
		
		addObserver(president);
		addObserver(participation);
		
		notifyObservers();
		
		removeObserver(president);
		removeObserver(participation);
	}

}
