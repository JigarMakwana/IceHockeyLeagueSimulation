package group11.Hockey.models;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CheckAndSimulateTodaySchedule {

	private HashMap<String,HashMap<Team,Team>> schedule;
	
	public CheckAndSimulateTodaySchedule(HashMap<String,HashMap<Team,Team>> schedule) {
		super();
		this.schedule = schedule;
	}

	public void CheckAndSimulateToday(String date) {
		String time="00:00:00";
		Random random = new Random();
	    IntStream wonTeam=random.ints(1, 2);
	    //System.out.println(date+" "+time+" Won Team"+wonTeam);
		//HashMap<String,List<String>> today=schedule.getSeasonSchedule();
		
		/*if(today.get(date+"T"+time))
		{
			
		}*/
	}

}
