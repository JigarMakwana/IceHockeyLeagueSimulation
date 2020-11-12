package group11.Hockey.BusinessLogic;

import java.util.Date;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.models.IParse;
import group11.Hockey.models.Parse;

public class AdvanceToNextSeason implements IState {
	String advanceDatedate;
	List<Team> qualifiedTeams;
	Date dateTime;
	int startYear;
	int endYear;
	ILeague league;

	public AdvanceToNextSeason(String advanceDatedate, List<Team> qualifiedTeams, Date dateTime, int startYear,
			int endYear, ILeague league) {
		this.advanceDatedate = advanceDatedate;
		this.qualifiedTeams = qualifiedTeams;
		this.dateTime = dateTime;
		this.startYear = startYear;
		this.endYear = endYear;
		this.league = league;
	}

	@Override
	public IState startState() {
		IParse parse = new Parse();
		IAdvance advance = new Advance();
		int year = parse.stringToYear(advanceDatedate);
		String advanced = "29/09/" + Integer.toString(year);
		Date advancedDate = parse.stringToDate(advanced);
		int daysBetween = (int) ((advancedDate.getTime() - dateTime.getTime()) / (24 * 60 * 60 * 1000));
		Team winner = qualifiedTeams.get(0);
		qualifiedTeams.remove(winner);
		String message = "\n********** Winner team of the season(" + startYear + "/" + endYear + ") is "
				+ winner.getTeamName() + " **********";
		System.out.println(message);
		advanceDatedate = advance.getAdvanceDate(advanceDatedate, daysBetween);
		league.setStartDate(advanceDatedate);

		IState currentState = new AgePlayer(league, daysBetween);
		currentState.startState();

		return null;
	}

}
