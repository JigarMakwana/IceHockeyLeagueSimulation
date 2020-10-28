package group11.Hockey.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			CheckAndSimulateTodaySchedule simulateToday=new CheckAndSimulateTodaySchedule(schedule,leagueObj);
			
			Date dateTime=parseObj.parseStringToDate(date);	
			int year=parseObj.parseStringToYear(date);
			year++;				
		    String endDate="01/06/"+year;
			Date endDateTime = parseObj.parseStringToDate(endDate);
			
			//GET TRADE DEADLINE
			TradeDeadline tradeDL=new TradeDeadline();
			Date tradeDeadLine=tradeDL.getTradeDeadline(date);
			//GET TRADE DEALINE CLOSE
			System.out.println("Started Simulation");
		    //SIMULATE SCHEDULE
			while(dateTime.before(endDateTime)) {	
				try {
					date=advance.getAdvanceDate(date,1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dateTime=parseObj.parseStringToDate(date);
				if(dateTime.after(endDateTime)) {
					//InitializePlayoff();
					//simulatePlayoff(date);
					//training();
					simulateToday.CheckAndSimulateToday(date);
					if(dateTime.compareTo(tradeDeadLine)<=0){
					//executeTrades();
						//System.out.println("Entered Trades");
						}
				}
				else{
					//training(currentDate);
					simulateToday.CheckAndSimulateToday(date);
					if(dateTime.compareTo(tradeDeadLine)<=0){
					//executeTrades();
						//System.out.println("Entered Trades");
						}
				}
				AgePlayer ageplayer=new AgePlayer();
				ageplayer.increaseAge(leagueObj,1);
				//persist();
				
			}
				//SIMULATE SCHEDULE CLOSE
			System.out.println(tradeDeadLine+"\n");
			return endDate;
		}
	

}
