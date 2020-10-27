package group11.Hockey.models;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CheckAndSimulateTodaySchedule {

	private HashMap<String,HashMap<Team,Team>> schedule;
	private League leagueObj;
	//
	public CheckAndSimulateTodaySchedule(HashMap<String,HashMap<Team,Team>> schedule,League leagueObj) {
		super();
		this.schedule = schedule;
		this.leagueObj=leagueObj;
	}

	public void CheckAndSimulateToday(String date) {
		Advance advanceObj = new Advance();
		Parse parseObj=new Parse();
		String time="00:00:00";
		HashMap<Team,Team> todayTeams=new HashMap<>();
		HashMap<Team,Team> todayTeamsCheck=new HashMap<>();
		Team team1;
		Team team2;
		String id;
		while(schedule.get(date+"T"+time)!=null){	
			/*List<Conference> cconferenceList = leagueObj.getConferences();
			for (Conference conference : cconferenceList) {		
				List<Division> divisionList = conference.getDivisions();
				for (Division division : divisionList) {
					List<Team> teamList = division.getTeams();
					for (Team team : teamList) {	
							team1=team;			
							id=date+"T"+time;
							todayTeams=schedule.get(id);
							team2=todayTeams.get(team);	
							if(team2!=null) {
								System.out.println(id+" teams are "+team1.getTeamName()+" and "+team2.getTeamName());
							}
					}					
				}
			}*/
			id=date+"T"+time;
			todayTeams=schedule.get(id);
			 Iterator it = todayTeams.entrySet().iterator();
			    while (it.hasNext()) {
			        HashMap.Entry pair = (HashMap.Entry)it.next();
			        System.out.println(pair.getKey() + " = " + pair.getValue());
			        it.remove(); // avoids a ConcurrentModificationException
			    }

			
			
			//System.out.println(date+"T"+time);
			try {
				time=advanceObj.getAdvanceTime(time,3);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//team1=todayTeams.get(key);
		long wonTeam=Math.round( Math.random() )  ;
		//System.out.println(todayTeams+" is todays teams");
	    //System.out.println(date+" "+time+" Won Team"+wonTeam);
		//HashMap<String,List<String>> today=schedule.getSeasonSchedule();
		
		/*if(today.get(date+"T"+time))
		{
			
		}*/
	}

}
