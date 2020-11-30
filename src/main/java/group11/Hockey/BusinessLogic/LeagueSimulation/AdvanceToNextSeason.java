// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.Aging.IAgePlayer;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IAdvanceToNextSeason;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IParse;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class AdvanceToNextSeason extends StateMachineState implements IAdvanceToNextSeason {
	ILeague league;
	ILeagueDb leagueDb;
	IDisplay display;
	private static Logger logger = LogManager.getLogger(AdvanceToNextSeason.class);

	public AdvanceToNextSeason(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		this.league = league;
		this.leagueDb = leagueDb;
		this.display = display;
	}

	@Override
	public StateMachineState startState() {
		logger.debug("Entered startState()");
		IParse parse = DefaultHockeyFactory.makeParse();
		IAdvance advance = DefaultHockeyFactory.makeAdvance();

		ITimeLine timeLine = league.getTimeLine();
		String currentDate = timeLine.getCurrentDate();
		Date dateTime = parse.stringToDate(currentDate);

		int year = parse.stringToYear(currentDate);
		String advanced = "29/09/" + Integer.toString(year);
		Date advancedDate = parse.stringToDate(advanced);
		int daysBetween = (int) ((advancedDate.getTime() - dateTime.getTime()) / (24 * 60 * 60 * 1000));
		currentDate = advance.getAdvanceDate(currentDate, daysBetween);
		timeLine.setLastSimulatedDate(advanced);
		league.setStartDate(advanced);

		StateMachineState agePlayer = DefaultHockeyFactory.makeAgePlayer(league, daysBetween, leagueDb, display);
		((IAgePlayer) agePlayer).agePlayers();

		leagueDb.insertLeagueInDb(league);

		return DefaultHockeyFactory.makeFinalState();
	}

}
