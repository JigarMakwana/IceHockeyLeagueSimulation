package group11.Hockey.models;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int count=seasonCount;
		while(count>0) {		
			String startDate="30/09/"+Integer.toString(year);			
			year++;	
			LocalDate eDate = LocalDate.of(year, Month.APRIL, 1);	
		    LocalDate firstSaturday = eDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		    String regularSeasonEndDate = firstSaturday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	
			System.out.println("Start date : "+startDate); 
			System.out.println("End date : "+regularSeasonEndDate); 			
			
			Schedule regularSeasonSchedule=new Schedule(startDate,regularSeasonEndDate,leagueObj);
			HashMap<String,HashMap<Team,Team>> regularSchedule=regularSeasonSchedule.getSeasonSchedule();
			
			Advance advanceObj=new Advance();
			//String regularSeasonStartDate=advanceObj.getAdvanceDate(startDate,1);
			SimulateSeason simulateSeason=new SimulateSeason(regularSchedule,leagueObj);
			simulateSeason.StartSimulatingSeason(startDate,regularSeasonEndDate);
			count--;	
		}
		return seasonCount+" Seasons Simulated";
	}

}
