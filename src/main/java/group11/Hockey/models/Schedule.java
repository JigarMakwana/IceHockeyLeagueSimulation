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
	//
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
	
	public HashMap<String, HashMap<Team, Team>> getSeasonSchedule() throws ParseException  {
		
		int totalTeams=0,divLimit,divLimitReached,inConLimit,inConLimitReached,outConLimit,outConLimitReached;
		int team1DivCount,team1InConCount,team1OutConCount,divGames=0,totalDivGames,inConGames=0,totalInConGames,outConGames=0,totalOutConGames;
		int team2DivCount,team2InConCount,team2OutConCount,loop=0,totalDivTeams,totalInConTeams,totalOutConTeams,team1TotalCount,team2TotalCount;
		String date=startDate,time="00:00:00";
		Team t1,t2;
		Division div1,div2;	
		Conference con1,con2;

		Advance advanceObj = new Advance();
		Parse parseObj=new Parse();
		ArrayList<Team> teamName = new ArrayList<Team>(); 
		HashMap<Team,Integer> scheduledDivisionMatchCount = new HashMap<>(); 
		HashMap<Team,Integer> scheduledInConferenceMatchCount = new HashMap<>(); 
		HashMap<Team,Integer> scheduledOutConferenceMatchCount = new HashMap<>(); 
		HashMap<String,Integer> simulatedHashmap = new HashMap<>();
		HashMap<Team, Team> schedule = new HashMap<>();
		HashMap<Team,Conference> teamConference=new HashMap<>();
		HashMap<Team,Division> teamDivision=new HashMap<>();
		HashMap<Conference,Integer> conTeamCount=new HashMap<>();
		HashMap<Division,Integer> divTeamCount=new HashMap<>();
		HashMap<Team,Integer> totalGameCount=new HashMap<>();
		
		List<Conference> cconferenceList = leagueObj.getConferences();
		for (Conference conference : cconferenceList) {		
			conTeamCount.put(conference,0);
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				divTeamCount.put(division,0);
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					teamName.add(team);
					scheduledDivisionMatchCount.put(team,0);
					scheduledInConferenceMatchCount.put(team,0);
					scheduledOutConferenceMatchCount.put(team,0);
					teamConference.put(team, conference);
					teamDivision.put(team, division);
					totalTeams++;
					conTeamCount.put(conference,conTeamCount.get(conference)+1);
					divTeamCount.put(division,divTeamCount.get(division)+1);
					totalGameCount.put(team,0);
				}
			}
		}
		
		Date dateTime=parseObj.parseStringToDate(date);		
		Date endDateTime = parseObj.parseStringToDate(endDate);
		String regularSeasonStartDate=advanceObj.getAdvanceDate(date,1);	
		
		HashMap<String,HashMap<Team,Team>> regularSchedule = new HashMap<>();		
		
		
		for(int i=0;i<totalTeams;i++) {
			t1=teamName.get(i);
			team1DivCount=scheduledDivisionMatchCount.get(t1);
			team1InConCount=scheduledInConferenceMatchCount.get(t1);
			team1OutConCount=scheduledOutConferenceMatchCount.get(t1);
			//System.out.println("Entered"+i);
			divLimit=28;
			inConLimit=28;
			
			divLimitReached=team1DivCount;	
			inConLimitReached=team1InConCount;
			outConLimitReached=team1OutConCount;
			
			div1=teamDivision.get(t1);
			con1=teamConference.get(t1);
			
			totalDivGames=(divTeamCount.get(div1))*28;
			totalInConGames=(conTeamCount.get(con1))*28;
			totalDivTeams=(divTeamCount.get(div1));
			totalInConTeams=(conTeamCount.get(con1));
			
			if(totalGameCount.get(t1)>=82)
			{
				break;
			}
			
					//ensures team schedules 24 games
				for(int j=i+1;j<totalTeams;j++){
					t2=teamName.get(j);	
					div2=teamDivision.get(t2);
					team2DivCount=scheduledDivisionMatchCount.get(t2);
					if(totalGameCount.get(t2)>=82)
					{
						break;
					}
					if(divLimitReached==divLimit){
						break;
					}
					if((div1.getDivisionName()==div2.getDivisionName())) {						
						
						if(dateTime.compareTo(endDateTime)>0) {
							date=regularSeasonStartDate;					
							time=advanceObj.getAdvanceTime(time,3);
						}				
						
						
						//ensures team played every other team once
						if((schedule.get(t1)!=t2)&&(schedule.get(t2)!=t1)&&(team2DivCount< divLimit)){	
							
							while(regularSchedule.get(date+"T"+time)!=null){
								time=advanceObj.getAdvanceTime(time,3);
							}
							
							schedule.put(t1,t2);
							regularSchedule.put(date+"T"+time, schedule);	
							simulatedHashmap.put(date+time+t1+t2, 0);

							date=advanceObj.getAdvanceDate(date,1);
							dateTime=parseObj.parseStringToDate(date);
							//System.out.println(t1.getTeamName()+","+t2.getTeamName()+","+date+" "+time);
							divLimitReached++;
							divGames++;
							totalGameCount.put(t1, totalGameCount.get(t1)+1);
							totalGameCount.put(t2, totalGameCount.get(t2)+1);
							scheduledDivisionMatchCount.put(t1, scheduledDivisionMatchCount.get(t1) + 1);
							scheduledDivisionMatchCount.put(t2, scheduledDivisionMatchCount.get(t2) + 1);

						}						
					}
				}
				
				// schedules repetitive games between already scheduled teams
				loop=0;
				while((loop<totalDivTeams)){
				loop++;	
				//System.out.println("entered Div while "+i);
				if(divLimitReached==divLimit){
					break;
				}
				for(int k=0;k<totalTeams;k++){
					t2=teamName.get(k);
					div2=teamDivision.get(t2);
					team2DivCount=scheduledDivisionMatchCount.get(t2);
					team2TotalCount=totalGameCount.get(t2);
					if(totalGameCount.get(t2)>=82)
					{
						break;
					}					
					
					if((div1.getDivisionName()==div2.getDivisionName())&&(t1.getTeamName()!=t2.getTeamName())) {	
						
						
						
						if((team2TotalCount<82)&&(team2DivCount<divLimit)){
							
							if(dateTime.compareTo(endDateTime)>0) {
								date=regularSeasonStartDate;					
								time=advanceObj.getAdvanceTime(time,3);
							}	
							
							while(regularSchedule.get(date+"T"+time)!=null){
								time=advanceObj.getAdvanceTime(time,3);
							}
							
							schedule.put(t1,t2);
							regularSchedule.put(date+"T"+time, schedule);	
							simulatedHashmap.put(date+time+t1+t2, 0);
	
							date=advanceObj.getAdvanceDate(date,1);
							dateTime=parseObj.parseStringToDate(date);
							//System.out.println(t1.getTeamName()+","+t2.getTeamName()+","+date+" "+time);
							divLimitReached++;
							divGames++;
							totalGameCount.put(t1, totalGameCount.get(t1)+1);
							totalGameCount.put(t2, totalGameCount.get(t2)+1);
							scheduledDivisionMatchCount.put(t1, scheduledDivisionMatchCount.get(t1) + 1);
							scheduledDivisionMatchCount.put(t2, scheduledDivisionMatchCount.get(t2) + 1);
							
						
						}
					}
				}
			}//System.out.println("exit Div while "+i);
			System.out.println("Scheduled "+scheduledDivisionMatchCount.get(teamName.get(i))+" in division games for "+i);
			//System.out.println("\nIN DIVISION STARTS\n");
			//In conference but not same division starts
						//ensures team schedules 24 games
				for(int l=i+1;l<totalTeams;l++){
					t2=teamName.get(l);	
					div2=teamDivision.get(t2);
					con2=teamConference.get(t2);
					team2InConCount=scheduledInConferenceMatchCount.get(t2);
					team2TotalCount=totalGameCount.get(t2);
					if(totalGameCount.get(t2)>=82)
					{
						break;
					}
					if(inConLimitReached==inConLimit){
						break;
					}
					
					if((div1.getDivisionName()!=div2.getDivisionName())&&(con2.getConferenceName()==con1.getConferenceName())) {	
	
						
						if(dateTime.compareTo(endDateTime)>0) {
							date=regularSeasonStartDate;					
							time=advanceObj.getAdvanceTime(time,3);
						}				
						
									
						//ensures team played every other team once
						if((schedule.get(t1)!=t2)&&(schedule.get(t2)!=t1)&&(team2InConCount< inConLimit)){	
							
							while(regularSchedule.get(date+"T"+time)!=null){
								time=advanceObj.getAdvanceTime(time,3);
							}
							
							schedule.put(t1,t2);
							regularSchedule.put(date+"T"+time, schedule);	
							simulatedHashmap.put(date+time+t1+t2, 0);

							date=advanceObj.getAdvanceDate(date,1);
							dateTime=parseObj.parseStringToDate(date);
							//System.out.println(t1.getTeamName()+","+t2.getTeamName()+","+date+" "+time);
							inConLimitReached++;
							inConGames++;
							totalGameCount.put(t1, totalGameCount.get(t1)+1);
							totalGameCount.put(t2, totalGameCount.get(t2)+1);
							scheduledInConferenceMatchCount.put(t1, scheduledInConferenceMatchCount.get(t1) + 1);
							scheduledInConferenceMatchCount.put(t2, scheduledInConferenceMatchCount.get(t2) + 1);

						}						
					}
				}
				loop=0;
				//System.out.println("in con teams"+totalInConTeams);
				// schedules repetitive games between already scheduled teams
				while((inConLimitReached<inConLimit)&&(loop<totalInConTeams)){
					loop++;	
					//System.out.println("entered in con while "+i);
					if(inConLimitReached==inConLimit){
						break;
					}
				for(int m=0;m<totalTeams;m++){
					t2=teamName.get(m);
					div2=teamDivision.get(t2);
					con2=teamConference.get(t2);
					team2InConCount=scheduledInConferenceMatchCount.get(t2);
					team2TotalCount=totalGameCount.get(t2);
					if(totalGameCount.get(t2)>=82)
					{
						break;
					}

					if((div1.getDivisionName()!=div2.getDivisionName())&&(con2.getConferenceName()==con1.getConferenceName())) {						
						
						
						
						if((team2TotalCount<82)&&(team2InConCount< inConLimit)){
							
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
							//System.out.println(t1.getTeamName()+","+t2.getTeamName()+","+date+" "+time);
							inConLimitReached++;
							inConGames++;
							totalGameCount.put(t1, totalGameCount.get(t1)+1);
							totalGameCount.put(t2, totalGameCount.get(t2)+1);
							scheduledInConferenceMatchCount.put(t1, scheduledInConferenceMatchCount.get(t1) + 1);
							scheduledInConferenceMatchCount.put(t2, scheduledInConferenceMatchCount.get(t2) + 1);
							
						
						}
					}
				}
			}//System.out.println("exit incon while "+i);
			System.out.println("Scheduled "+scheduledInConferenceMatchCount.get(teamName.get(i))+" in conference games for "+i);
			//In conference but not same division ends
			outConLimit=82-divLimit-inConLimit;
			//System.out.println("\nOUT DIVISION STARTS\n");
			//Out conference starts
						//ensures team schedules 24 games
			if(outConLimitReached==outConLimit){
				break;
			}
				for(int n=i+1;n<totalTeams;n++){
					t2=teamName.get(n);	
					div2=teamDivision.get(t2);
					con2=teamConference.get(t2);
					team2OutConCount=scheduledOutConferenceMatchCount.get(t2);
					team2TotalCount=totalGameCount.get(t2);
					if(totalGameCount.get(t2)>=82)
					{
						break;
					}

					if(con2.getConferenceName()!=con1.getConferenceName()) {						
						
						if(dateTime.compareTo(endDateTime)>0) {
							date=regularSeasonStartDate;					
							time=advanceObj.getAdvanceTime(time,3);
						}				
	
						//ensures team played every other team once
						if((schedule.get(t1)!=t2)&&(schedule.get(t2)!=t1)&&(team2OutConCount< outConLimit)){	
							
							while(regularSchedule.get(date+"T"+time)!=null){
								time=advanceObj.getAdvanceTime(time,3);
							}
							
							schedule.put(t1,t2);
							regularSchedule.put(date+"T"+time, schedule);	
							simulatedHashmap.put(date+time+t1+t2, 0);

							date=advanceObj.getAdvanceDate(date,1);
							dateTime=parseObj.parseStringToDate(date);
							//System.out.println(t1.getTeamName()+","+t2.getTeamName()+","+date+" "+time);
							outConLimitReached++;
							outConGames++;
							totalGameCount.put(t1, totalGameCount.get(t1)+1);
							totalGameCount.put(t2, totalGameCount.get(t2)+1);
							scheduledOutConferenceMatchCount.put(t1, scheduledOutConferenceMatchCount.get(t1) + 1);
							scheduledOutConferenceMatchCount.put(t2, scheduledOutConferenceMatchCount.get(t2) + 1);

						}	
						
					}
				}
				
				totalOutConGames=82-inConGames-divGames;
				totalOutConTeams=totalTeams-totalInConTeams;
				// schedules repetitive games between already scheduled teams
				loop=0;
				while((outConLimitReached<outConLimit)&&(loop<totalOutConTeams)){
					loop++;	
					//System.out.println("entered out con while "+i);
					if(outConLimitReached==outConLimit){
						break;
					}
				for(int o=0;o<totalTeams;o++){
					t2=teamName.get(o);
					div2=teamDivision.get(t2);
					con2=teamConference.get(t2);
					if(totalGameCount.get(t2)>=82)
					{
						break;
					}
					team2OutConCount=scheduledOutConferenceMatchCount.get(t2);
					team2TotalCount=totalGameCount.get(t2);

					if(con2.getConferenceName()!=con1.getConferenceName()) {
						
						
						if((team2TotalCount<82)&&(team2OutConCount< outConLimit)){
							
							if(dateTime.compareTo(endDateTime)>0) {
								date=regularSeasonStartDate;					
								time=advanceObj.getAdvanceTime(time,3);
							}	
							
							while(regularSchedule.get(date+"T"+time)!=null){
								time=advanceObj.getAdvanceTime(time,3);
							}
							
							schedule.put(t1,t2);
							regularSchedule.put(date+"T"+time, schedule);	
							simulatedHashmap.put(date+time+t1+t2, 0);
	
							date=advanceObj.getAdvanceDate(date,1);
							dateTime=parseObj.parseStringToDate(date);
							//System.out.println(t1.getTeamName()+","+t2.getTeamName()+","+date+" "+time);
							outConLimitReached++;
							outConGames++;
							totalGameCount.put(t1, totalGameCount.get(t1)+1);
							totalGameCount.put(t2, totalGameCount.get(t2)+1);
							scheduledOutConferenceMatchCount.put(t1, scheduledOutConferenceMatchCount.get(t1) + 1);
							scheduledOutConferenceMatchCount.put(t2, scheduledOutConferenceMatchCount.get(t2) + 1);
							
						
						}
					}
				}
			}//System.out.println("exit out con while "+i);
			
			
			System.out.println("Scheduled "+scheduledOutConferenceMatchCount.get(teamName.get(i))+" out conference games for "+i);
			//Out conference ends
			//System.out.println("Exit "+i);
		}
		
		return regularSchedule;
	}
	

}
