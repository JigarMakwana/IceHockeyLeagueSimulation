package group11.Hockey.models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;

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
		Parse parse=new Parse();
		String time="00:00:00",id=date+"T"+time;
		HashMap<Team,Team> todayTeams=new HashMap<>();
		Team team1,team2,won,lost;
		todayTeams=schedule.get(id);
		int points,updatePoints,year,updateWin,loss;
		Date possibleSeasonEnd,possibleSeasonStart,dateTime;
		List<Team> qualifiedTeams=leagueObj.getQualifiedTeams();
		
		dateTime=parse.parseStringToDate(date);
		year=advanceObj.getYearFromStringDate(date);
		possibleSeasonStart=parse.parseStringToDate("29/09/"+Integer.toString(year));
		possibleSeasonEnd=advanceObj.getFirstSaturdayOfAprilInYear(year);
		
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
									won=team1;
									lost=team2;
								}
								else {
									won=team2;
									lost=team1;
								}
								
								if((dateTime.compareTo(possibleSeasonStart)<=0)&&(dateTime.compareTo(possibleSeasonEnd)>0)) {	
									if((team1.getWins()>=4) || (team2.getWins()>=4)) {
										if(team1.getWins()==4) {
											System.out.println("Winner already declared :"+team1.getTeamName()+"(4-"+team2.getWins()+")");
										}
										else {
											System.out.println("Winner already declared :"+team2.getTeamName()+"("+team1.getWins()+"-4)");
										}
										continue;
									}
									else {
										System.out.println("Team Won : "+won.getTeamName());
										points=won.getPoints();
										System.out.println("Points are "+points);
										updatePoints=points+2;
										System.out.println("Updated Points is "+updatePoints);
										won.setPoints(updatePoints);
										won.setLosses(0);
										loss=lost.getLosses();
										loss++;
										lost.setLosses(loss);
										updateWin=won.getWins();
										updateWin++;
										won.setWins(updateWin);
										if(updateWin==4) {
											//add won team into qualified teams list
											//qualifiedTeams.add(won);	
											if(qualifiedTeams.contains(lost)) {
												qualifiedTeams.remove(lost);
											}	
										}
									}
								}
								else {
									System.out.println("Team Won : "+won.getTeamName());
									points=won.getPoints();
									System.out.println("Points are "+points);
									updatePoints=points+2;
									System.out.println("Updated Points is "+updatePoints);
									won.setPoints(updatePoints);
									won.setLosses(0);
									loss=lost.getLosses();
									loss++;
									lost.setLosses(loss);
								}
									
							}
							//call checkInjury();
						}
					}	
				}	
			if((dateTime.compareTo(possibleSeasonStart)<=0)&&(dateTime.compareTo(possibleSeasonEnd)>0)) {
				try {
					time=advanceObj.getAdvanceTime(time,5);
					id=date+"T"+time;
					todayTeams=schedule.get(id);
				} catch (ParseException e) {
					e.printStackTrace();
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
