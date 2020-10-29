package group11.Hockey.models;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;

public class InitializeSeason {	
	//
	private int seasonCount;
	
	private League leagueObj;
	
	public InitializeSeason(League leagueObj) {
		super();
		this.leagueObj = leagueObj;
	}

	public int getSeasonCount() {
		return seasonCount;
	}
	
	public String startSeasons(int seasonCount) throws ParseException {
		//get last date simulated from db;
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int count=seasonCount;
		String seasonEndDate = null;
		while(count>0) {		
			String startDate="30/09/"+Integer.toString(year);		
			
			System.out.println("Start date : "+startDate); 		
			
			Schedule regularSeasonSchedule=new Schedule(leagueObj);
			HashMap<String,HashMap<Team,Team>> regularSchedule=regularSeasonSchedule.getSeasonSchedule(startDate);
			
			SimulateSeason simulateSeason=new SimulateSeason(regularSchedule,leagueObj);
			seasonEndDate=simulateSeason.StartSimulatingSeason(startDate);
			count--;	
		}
		//set or return last date to current date after simulations
		return seasonEndDate;
	}

}