package group11.Hockey.models;

import java.util.Calendar;
import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;
import group11.Hockey.db.CoachDb;
import group11.Hockey.db.GameplayConfigDb;
import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.ManagerDb;
import group11.Hockey.db.PlayerDb;
import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.League.LeagueDbImpl;

public class InitializeSeason implements IInitializeSeason{

	private ILeagueDb leagueDb;
	private IGameplayConfigDb gameplayConfigDb;
	private IPlayerDb playerDb;
	private ICoachDb coachDb;
	private IManagerDb managerDb;
	private League league;

	public InitializeSeason(League league, ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb,
			IPlayerDb playerDb, ICoachDb coachDb, IManagerDb managerDb) {
		super();
		this.league = league;
		this.leagueDb = leagueDb;
		this.gameplayConfigDb = gameplayConfigDb;
		this.playerDb = playerDb;
		this.coachDb = coachDb;
		this.managerDb = managerDb;
	}

	@Override
	public String startSeasons(int seasonCount) {
		String startDate,lastSimulatedDate=league.getStartDate();
		int year;
		if((lastSimulatedDate==null)||(lastSimulatedDate.isEmpty())) {
			year = Calendar.getInstance().get(Calendar.YEAR);
			startDate = "29/09/" + Integer.toString(year);
		}
		else {
			IParse parse=new Parse();
			year=parse.stringToYear(lastSimulatedDate);
			startDate=lastSimulatedDate;
		}

		int count = seasonCount;
		String seasonEndDate = null,message;
		IPrintToConsole console=new PrintToConsole();
		IAdvance advance=new Advance();
		while (count > 0) {
			startDate=advance.getAdvanceDate(startDate, 1);
			league.setStartDate(startDate);
			message="Start date : " + startDate;
			console.print(message);
			league.setStartDate(startDate);

			ISchedule regularSeasonSchedule = new Schedule(league);
			HashMap<String, HashMap<Team, Team>> regularSchedule = null;
			regularSchedule = regularSeasonSchedule.getSeasonSchedule(startDate);

			ISimulateSeason simulateSeason = new SimulateSeason(regularSchedule, league, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
			seasonEndDate = simulateSeason.StartSimulatingSeason(startDate);
			startDate=seasonEndDate;
			year++;
			count--;
		}
		return seasonEndDate;
	}

}
