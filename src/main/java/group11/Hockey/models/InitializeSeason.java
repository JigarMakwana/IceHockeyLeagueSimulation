package group11.Hockey.models;

import java.util.Calendar;
import java.util.HashMap;

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
	private League leagueObj;

	public InitializeSeason(League leagueObj, ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb,
			IPlayerDb playerDb, ICoachDb coachDb, IManagerDb managerDb) {
		super();
		this.leagueObj = leagueObj;
		this.leagueDb = leagueDb;
		this.gameplayConfigDb = gameplayConfigDb;
		this.playerDb = playerDb;
		this.coachDb = coachDb;
		this.managerDb = managerDb;
	}

	@Override
	public String startSeasons(int seasonCount) {
		String lastSimulatedDate=leagueObj.getStartDate();
		int year;
		if((lastSimulatedDate==null)||(lastSimulatedDate.isEmpty())) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		else {
			IParse parse=new Parse();
			year=parse.stringToYear(lastSimulatedDate);
		}

		int count = seasonCount;
		String seasonEndDate = null,message;
		IPrintToConsole console=new PrintToConsole();
		while (count > 0) {
			String startDate = "29/09/" + Integer.toString(year);
			message="Start date : " + startDate;
			console.print(message);
			leagueObj.setStartDate(startDate);

			ISchedule regularSeasonSchedule = new Schedule(leagueObj);
			HashMap<String, HashMap<Team, Team>> regularSchedule = null;
			regularSchedule = regularSeasonSchedule.getSeasonSchedule(startDate);

			ISimulateSeason simulateSeason = new SimulateSeason(regularSchedule, leagueObj, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
			seasonEndDate = simulateSeason.StartSimulatingSeason(startDate);
			year++;
			count--;
		}
		return seasonEndDate;
	}

}
