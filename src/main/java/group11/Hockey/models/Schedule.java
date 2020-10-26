package group11.Hockey.models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Schedule {

	private String startDate;
	private String endDate;
	private League leagueObj;
	
	public Schedule(String startDate,String endDate,League leagueObj) {
		this.startDate=startDate;
		this.endDate=endDate;
		this.leagueObj=leagueObj;
	}
	
	public String getRegularStartDate() {
		return startDate;
	}
	
	public String getRegularSeasonEndDate() {
		return endDate;
	}
	
	public HashMap<String,HashMap<Team,Team>> getSeasonSchedule() throws ParseException  {
		
		int totalTeams=0,divLimit,divLimitReached;
		int team1DivCount,team1InConCount,team1OutConCount;
		int team2DivCount,team2InConCount,team2OutConCount;
		String date=startDate,time="00:00:00";
		Team t1;
		Team t2;		

		Advance advanceObj = new Advance();
		Parse parseObj=new Parse();
		ArrayList<Team> teamName = new ArrayList<Team>(); 
		HashMap<Team,Integer> scheduledDivisionMatchCount = new HashMap<>(); 
		HashMap<Team,Integer> scheduledInConferenceMatchCount = new HashMap<>(); 
		HashMap<Team,Integer> scheduledOutConferenceMatchCount = new HashMap<>(); 
		HashMap<String,Integer> simulatedHashmap = new HashMap<>();
		HashMap<Team, Team> schedule = new HashMap<>();
		
		List<Conference> cconferenceList = leagueObj.getConferences();
		for (Conference conference : cconferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					teamName.add(team);
					scheduledDivisionMatchCount.put(team,0);
					scheduledInConferenceMatchCount.put(team,0);
					scheduledOutConferenceMatchCount.put(team,0);
					totalTeams++;
				}
			}
		}
		
		Date dateTime=parseObj.parseStringToDate(date);		
		Date endDateTime = parseObj.parseStringToDate(endDate);
		String regularSeasonStartDate=advanceObj.getAdvanceDate(date,1);	
		
		HashMap<String,HashMap<Team,Team>> regularSchedule = new HashMap<>();		
		
		for(int i=0;i<totalTeams-1;i++) {
			team1DivCount=scheduledDivisionMatchCount.get(teamName.get(i));
			divLimit=24-team1DivCount;
			divLimitReached=0;	
			t1=teamName.get(i);
			
			while(divLimitReached<divLimit){				//ensures team schedules 24 games
				for(int j=i+1;j<totalTeams;j++){
					t2=teamName.get(j);	
					team2DivCount=scheduledDivisionMatchCount.get(t2);

					if(divLimitReached==divLimit){
						break;
					}
					
					if(dateTime.compareTo(endDateTime)>0) {
						date=regularSeasonStartDate;					
						time=advanceObj.getAdvanceTime(time,3);
					}				
					
					if(team2DivCount<24) {
					
						//ensures team played every other team once
						if((schedule.get(t1)!=t2)&&(schedule.get(t2)!=t1)&&(team1DivCount< 24)){	
							
							while(regularSchedule.get(date+"T"+time)!=null){
								time=advanceObj.getAdvanceTime(time,3);
							}
							
							schedule.put(t1,t2);
							regularSchedule.put(date+"T"+time, schedule);	
							simulatedHashmap.put(date+time+t1+t2, 0);

							date=advanceObj.getAdvanceDate(date,1);
							dateTime=parseObj.parseStringToDate(date);
							System.out.println(t1.getTeamName()+","+t2.getTeamName()+","+date);
							divLimitReached++;
							scheduledDivisionMatchCount.put(t1, scheduledDivisionMatchCount.get(t1) + 1);
							scheduledDivisionMatchCount.put(t2, scheduledDivisionMatchCount.get(t2) + 1);

						}	
					}
				} 
				
				// schedules repetitive games between already scheduled teams
				for(int k=1;k<totalTeams;k++){
					t2=teamName.get(k);	
					team2DivCount=scheduledDivisionMatchCount.get(t2);
					
					if(divLimitReached==divLimit){
						break;
					}
					
					if(team2DivCount<24){
						
						schedule.put(t1,t2);
						if(dateTime.compareTo(endDateTime)>0) {
							date=regularSeasonStartDate;					
							time=advanceObj.getAdvanceTime(time,3);
						}	
						
						while(regularSchedule.get(date+"T"+time)!=null){
							time=advanceObj.getAdvanceTime(time,3);
						}
						
						regularSchedule.put(date+"T"+time, schedule);	
						simulatedHashmap.put(date+time+t1+t2, 0);

						date=advanceObj.getAdvanceDate(date,1);
						dateTime=parseObj.parseStringToDate(date);
						System.out.println(t1.getTeamName()+","+t2.getTeamName()+","+date);
						divLimitReached++;
						scheduledDivisionMatchCount.put(t1, scheduledDivisionMatchCount.get(t1) + 1);
						scheduledDivisionMatchCount.put(t2, scheduledDivisionMatchCount.get(t2) + 1);
						
					
					}
				}
			
			}
			System.out.println("Scheduled "+scheduledDivisionMatchCount.get(teamName.get(i))+" for "+t1.getTeamName());
		}
		Iterator<String> itr = regularSchedule.keySet().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
		return regularSchedule;
	}
	

}
