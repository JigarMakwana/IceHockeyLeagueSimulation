// Author: Harry B00856244
package group11.Hockey.BusinessLogic.Trophy;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.ILeague;

public class EndOfStanleySubject extends TrophySubject{

	ILeague league;
	
	public EndOfStanleySubject(ILeague league) {
		super();		
		ITrophyObserver calderMemorial=DefaultHockeyFactory.makeCalderMemorial(league);
		ITrophyObserver veniza=DefaultHockeyFactory.makeVeniza(league);
		ITrophyObserver jackAdams=DefaultHockeyFactory.makeJackAdams(league);
		ITrophyObserver mauriceRichard=DefaultHockeyFactory.makeMauriceRichard(league);
		ITrophyObserver robHawkeyMemorial=DefaultHockeyFactory.makeRobHawkeyMemorial(league);
		
		addObserver(calderMemorial);
		addObserver(veniza);
		addObserver(jackAdams);
		addObserver(mauriceRichard);
		addObserver(robHawkeyMemorial);
		
		notifyObservers();
		
		removeObserver(calderMemorial);
		removeObserver(veniza);
		removeObserver(jackAdams);
		removeObserver(mauriceRichard);
		removeObserver(robHawkeyMemorial);
	}

}
