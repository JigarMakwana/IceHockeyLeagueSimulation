package group11.Hockey.models;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.AgePlayer;

public class SimulateSeason {

	private HashMap<String,HashMap<Team,Team>> schedule;
	private League leagueObj;	
	//
	
	

	public SimulateSeason(HashMap<String,HashMap<Team,Team>> regularSchedule,League leagueObj) {
		this.schedule=regularSchedule;
		this.leagueObj=leagueObj;
	}



	public String StartSimulatingSeason(String date) {	
		
			Advance advance=new Advance();
			Parse parseObj=new Parse();
			Deadlines deadline=new Deadlines();
			HashMap<String,HashMap<Team,Team>> playoffSchedule = new HashMap<>();
			PlayoffSchedule playoff=new PlayoffSchedule(leagueObj);
			List<Team> qualifiedTeams=leagueObj.getQualifiedTeams();
			Team winner;
			
			Date dateTime,stanleyStartDateTime,stanleyEndDateTime,regularSeasonEndDateTime,tradeDeadLine,firstRoundEnd,secondRoundEnd,semiFinalsEnd,finalsEnd;
			String stanleyDate,firstRound = null,secondRound = null,semiFinalRound = null;
			
			dateTime=parseObj.parseStringToDate(date);
			stanleyStartDateTime = deadline.getStanleyPlayoffBeginDate(date);
			stanleyDate=parseObj.parseDateToString(stanleyStartDateTime);
			stanleyEndDateTime = deadline.getStanleyPlayoffDeadline(date);
			regularSeasonEndDateTime = deadline.getRegularSeasonDeadline(date);
			tradeDeadLine=deadline.getTradeDeadline(date);
			
			try {
				firstRound=advance.getAdvanceDate(stanleyDate,29);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				secondRound=advance.getAdvanceDate(firstRound,15);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				semiFinalRound=advance.getAdvanceDate(secondRound,7);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			firstRoundEnd=parseObj.parseStringToDate(firstRound);
			secondRoundEnd=parseObj.parseStringToDate(secondRound);
			semiFinalsEnd=parseObj.parseStringToDate(semiFinalRound);
			finalsEnd=stanleyEndDateTime;			
			
			System.out.println("Simulation started");
		    //SIMULATE SCHEDULE
			while(dateTime.compareTo(stanleyEndDateTime)<=0) {															//on or before stanley playoff end date
				try {
					date=advance.getAdvanceDate(date,1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dateTime=parseObj.parseStringToDate(date);
				
				//training(leagueObj,1);
				
				if(dateTime.compareTo(regularSeasonEndDateTime)<=0) {													//on or before regular season end date
					CheckAndSimulateTodaySchedule simulateToday=new CheckAndSimulateTodaySchedule(schedule,leagueObj);
					simulateToday.CheckAndSimulateToday(date);
				}
				
				if(dateTime.equals(regularSeasonEndDateTime)) {															//on the last day regular season
					playoffSchedule=playoff.generatePlayoffScheduleRound1(stanleyDate);
				}
				
				if(dateTime.compareTo(stanleyStartDateTime)>=0){														//on and after stanley playoff begin date
					
					if(dateTime.compareTo(firstRoundEnd)<=0) {															//on or before 1st playoff end date
						CheckAndSimulateTodaySchedule simulatePlayoffToday=new CheckAndSimulateTodaySchedule(playoffSchedule,leagueObj);
						simulatePlayoffToday.CheckAndSimulateToday(date);
					}
					
					if(dateTime.equals(firstRoundEnd)) {																//on the last day 1st playoff round					
						playoffSchedule=playoff.generatePlayoffScheduleReaminingRounds(date);
					}
					
					if((dateTime.compareTo(secondRoundEnd)<=0)&&(dateTime.compareTo(firstRoundEnd)>=0)) {				//on or before 2nd playoff end date
						CheckAndSimulateTodaySchedule simulatePlayoffToday=new CheckAndSimulateTodaySchedule(playoffSchedule,leagueObj);
						simulatePlayoffToday.CheckAndSimulateToday(date);
					}
					
					if(dateTime.equals(secondRoundEnd)) {																//on the last day 2nd playoff round					
						playoffSchedule=playoff.generatePlayoffScheduleReaminingRounds(date);
					}
					
					if((dateTime.compareTo(semiFinalsEnd)<=0)&&(dateTime.compareTo(secondRoundEnd)>=0)) {				//on or before semi finals end date
						CheckAndSimulateTodaySchedule simulatePlayoffToday=new CheckAndSimulateTodaySchedule(playoffSchedule,leagueObj);
						simulatePlayoffToday.CheckAndSimulateToday(date);
					}
					
					if(dateTime.equals(semiFinalsEnd)) {																//on the last day semi finals playoff round					
						playoffSchedule=playoff.generatePlayoffScheduleReaminingRounds(date);
						
					}
					
					if((dateTime.compareTo(finalsEnd)<=0)&&(dateTime.compareTo(semiFinalsEnd)>=0)) {						//on or before finals end date
						CheckAndSimulateTodaySchedule simulatePlayoffToday=new CheckAndSimulateTodaySchedule(playoffSchedule,leagueObj);
						simulatePlayoffToday.CheckAndSimulateToday(date);
					}
					
				}
				
				
				if(dateTime.compareTo(tradeDeadLine)<=0){					//on or before trade deadline
					//executeTrades();
					//System.out.println("Entered Trades");
				}
				
				AgePlayer ageplayer=new AgePlayer();
				ageplayer.increaseAge(leagueObj,1);
				
				if(dateTime.equals(stanleyEndDateTime)) {					//on the last day of stanley playoff
					winner=qualifiedTeams.get(0);
					System.out.println("\nWinner team of this season is "+winner.getTeamName());
					try {
						date=advance.getAdvanceDate(date,119);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					ageplayer.increaseAge(leagueObj,119);
				}
				
				//persist();
				
			}
			
				//SIMULATE SCHEDULE CLOSE
			return date;
		}
	

}
