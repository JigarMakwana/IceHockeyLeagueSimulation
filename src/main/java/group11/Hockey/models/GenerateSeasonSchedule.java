package group11.Hockey.models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class GenerateSeasonSchedule {

	private String startDate;
	private String endDate;
	private League leagueObj;
	
	public GenerateSeasonSchedule(String startDate,String endDate,League leagueObj) {
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
	
	public HashMap<String,List<String>> getSeasonSchedule() throws ParseException  {
		int totalTeams=5;
		
		List<Conference> cconferenceList = leagueObj.getConferences();
		for (Conference conference : cconferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					System.out.println("Team object"+team.getTeamName());
					/*if (team.getTeamName() != null && team.getTeamName().equalsIgnoreCase(teamName)) {
						isTeamNameValid = false;
						return isTeamNameValid;
					}*/
				}
			}
		}
		
		
		ArrayList<String> teamName = new ArrayList<String>(); 
		teamName.add("team1");
		teamName.add("team2");
		teamName.add("team3");
		teamName.add("team4");
		teamName.add("team5");
		String date=startDate;
		String time="00:00:00";
		String t1;
		String t2;		

		Advance advanceObj = new Advance();
		Parse parseObj=new Parse();
		
		Date dateTime=parseObj.parseStringToDate(date);		
		Date endDateTime = parseObj.parseStringToDate(endDate);
		String regularSeasonStartDate=advanceObj.getAdvanceDate(date,1);	
		
		HashMap<String,List<String>> regularSchedule = new HashMap<>();		
		
		for(int i=0;i<totalTeams-1;i++) {
			for(int j=i+1;j<totalTeams;j++) {
				date=advanceObj.getAdvanceDate(date,1);
				dateTime=parseObj.parseStringToDate(date);
				
				if(dateTime.compareTo(endDateTime)>0) {
					date=regularSeasonStartDate;					
					time=advanceObj.getAdvanceTime(time,3);
				}
				
				System.out.println(teamName.get(i)+","+teamName.get(j)+","+date);
				t1=teamName.get(i);
				t2=teamName.get(j);
				ArrayList<String> schedule = new ArrayList<>();
				schedule.add(t1);
				schedule.add(t2);
				regularSchedule.put(date+"T"+time, schedule);				
			}
			
		}
		//System.out.println(Arrays.asList(regularSchedule));
		return regularSchedule;
	}
	

}
