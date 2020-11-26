package group11.Hockey.BusinessLogic;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.Parse;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.db.League.ILeagueDb;

public class AdvanceToNextSeason extends StateMachineState {
	ILeague league;
	ILeagueDb leagueDb;
	private static Logger logger = LogManager.getLogger(AdvanceTime.class);

	public AdvanceToNextSeason(ILeague league, ILeagueDb leagueDb) {
		this.league = league;
		this.leagueDb = leagueDb;
	}

	@Override
	public StateMachineState startState() {

		logger.info("Entered AdvanceToNextSeason.java");
		IParse parse = new Parse();
		IAdvance advance = new Advance();

		ITimeLine timeLine = league.getTimeLine();
		String currentDate = timeLine.getCurrentDate();
		Date dateTime = parse.stringToDate(currentDate);
		String startDate = timeLine.getStartDate();
		int startYear = parse.stringToYear(startDate);
		String stanleyDate = timeLine.getStanleyDate();
		int endYear = parse.stringToYear(stanleyDate);

		List<Team> qualifiedTeams = league.getQualifiedTeams();

		int year = parse.stringToYear(currentDate);
		String advanced = "29/09/" + Integer.toString(year);
		Date advancedDate = parse.stringToDate(advanced);
		int daysBetween = (int) ((advancedDate.getTime() - dateTime.getTime()) / (24 * 60 * 60 * 1000));
		Team winner = qualifiedTeams.get(0);
		qualifiedTeams.remove(winner);
		String message = "\n********** Winner team of the season(" + startYear + "/" + endYear + ") is "
				+ winner.getTeamName() + " **********";
		System.out.println(message);
		currentDate = advance.getAdvanceDate(currentDate, daysBetween);
		// league.setStartDate(currentDate);
		timeLine.setLastSimulatedDate(advanced);
		league.setStartDate(advanced);

		AgePlayer agePlayer = new AgePlayer(league, daysBetween);
		agePlayer.agePlayers();

		leagueDb.insertLeagueInDb(league);

		return DefaultHockeyFactory.makeFinalState();
	}

}
