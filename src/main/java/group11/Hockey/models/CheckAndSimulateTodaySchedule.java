package group11.Hockey.models;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

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
		String time="00:00:00",id=date+"T"+time;
		HashMap<Team,Team> todayTeams=new HashMap<>();
		Team team1,team2;
		todayTeams=schedule.get(id);
		int points,updatePoints;
		
		while(schedule.containsKey(id)){
			if(time=="24:00:00") {
				break;
			}
			List<Conference> cconferenceList = leagueObj.getConferences();
			for (Conference conference : cconferenceList) {		
				List<Division> divisionList = conference.getDivisions();
				for (Division division : divisionList) {
					List<Team> teamList = division.getTeams();
					for (Team team : teamList) {	
						
							team1=team;	
							team2=todayTeams.get(team);	
							if(team2!=null) {
								System.out.println(id+" teams are "+team1.getTeamName()+" and "+team2.getTeamName());
								long wonTeam=Math.round( Math.random() )  ;
								if(wonTeam==0) {
									System.out.println("Team Won : "+team1.getTeamName());
									points=team1.getPoints();
									System.out.println("Points is "+points);
									updatePoints=points+2;
									System.out.println("Updated Points is "+updatePoints);
									//set team1 strength, add two points
									//fetch and set team1 consecutive losses to zero
									//fetch and set team2 consecutive losses, increment by one
								}
								else {
									System.out.println("Team Won : "+team2.getTeamName());
									points=team2.getPoints();
									System.out.println("Points is "+points);
									updatePoints=points+2;
									System.out.println("Updated Points is "+updatePoints);
									//set team2 strength, add two points
									//fetch and set team2 consecutive losses to zero
									//fetch and set team1 consecutive losses, increment by one
								}
								//call checkInjury();
								
							}
						}	
					}					
				}
			try {
				time=advanceObj.getAdvanceTime(time,1);
				id=date+"T"+time;
				todayTeams=schedule.get(id);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			}

		
	}
	
}
