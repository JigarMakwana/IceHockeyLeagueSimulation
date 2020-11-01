package group11.Hockey.models;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
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

public class InitializeSeason {
	//
	private int seasonCount;
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
	

	public int getSeasonCount() {
		return seasonCount;
	}

	public String startSeasons(int seasonCount) {
		String lastSimulatedDate=leagueObj.getStartDate();
		Parse parse=new Parse();
		int year;
		if((lastSimulatedDate.isEmpty())||(lastSimulatedDate==null)) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		else {
			year=parse.parseStringToYear(lastSimulatedDate);
		}
		 
		int count = seasonCount;
		String seasonEndDate = null;
		while (count > 0) {
			String startDate = "29/09/" + Integer.toString(year);
			System.out.println("Start date : " + startDate);
			leagueObj.setStartDate(startDate);

			Schedule regularSeasonSchedule = new Schedule(leagueObj);
			HashMap<String, HashMap<Team, Team>> regularSchedule = null;
			try {
				regularSchedule = regularSeasonSchedule.getSeasonSchedule(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			SimulateSeason simulateSeason = new SimulateSeason(regularSchedule, leagueObj, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
			seasonEndDate = simulateSeason.StartSimulatingSeason(startDate);
			year++;
			count--;
		}
		System.out.println("Persist");
		// persist();
		return seasonEndDate;
	}

}
