package group11.Hockey.BusinessLogic;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.Aging.AgePlayer;
import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.Parse;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class AdvanceToNextSeason extends StateMachineState {
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
		logger.info("Entered startState()");
		IParse parse = new Parse();
		IAdvance advance = new Advance();

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

		AgePlayer agePlayer = new AgePlayer(league, daysBetween, display);
		agePlayer.agePlayers();

		leagueDb.insertLeagueInDb(league);

		return DefaultHockeyFactory.makeFinalState();
	}

}
