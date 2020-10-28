/*package group11.Hockey.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayoffSchedule {

	private League leagueObj;

	public PlayoffSchedule(League leagueObj) {
		this.leagueObj=leagueObj;
	}
	
	public HashMap<Integer,Team> generatePlayoffSchedule(){
		HashMap<Integer,Team> firstRoundWinner=new HashMap<>();
		HashMap<Integer,Team> qualifiedTeams=new HashMap<Integer,Team>();
		Team wildCard1,wildCard2,team1,team2;
		int teamNumber;
		
		List<Conference> cconferenceList = leagueObj.getConferences();
		for (Conference conference : cconferenceList) {		
			teamNumber=0;
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				qualifiedTeams[teamNumber++]=getTop1stTeam;
				qualifiedTeams[teamNumber++]=getTop2ndTeam;
				qualifiedTeams[teamNumber++]=getTop3rdTeam;
				teamNumber++;								//creating space to add wild card
			}
			
			wildcard1=getTop1stFromRemainingTeams;
			wildCard2=getTop2ndFromRemainingTeams;

			if(qualifiedTeams[0]<qualifiedTeams[4]){	//assigning least wildcard to great teams & great wildcard to least ranked
				qualifiedTeams[3]=wildCard1;
				qualifiedTeams[7]=wildCard2;
			}
			else{
				qualifiedTeams[3]=wildCard2;
				qualifiedTeams[7]=wildCard1;
			}
			
			int teamNumber1=0;
			int teamNumber2=3;
			int matches=1,series=1,team1Wins=0,team2Wins=0;
			while(matches>4){							//four final sets of teams(team1,team2) from each conference - FIRST ROUND
				matches++;
				team1Wins=0;
				team2Wins=0;
				while(series>7){
					series++;
					team1= sortedTeams[teamNumber1];
					team2= sortedTeams[teamNumber2];
					
					if(team1Wins>=4){
						firstRoundWinner.put(matches, team1);
						break;
					}
					if(team2Wins>=4){
						firstRoundWinner.put(matches, team2);
						break;
					}
					
					long wonTeam=Math.round( Math.random() );
					
					if(wonTeam==0){
						team1Wins++;
					}
					else{
						team2Wins++;
					}
				}
				if((teamNumber1<teamNumber2)&&(teamNumber2-teamNumber1>1)){
					teamNumber1++;
					teamNumber2--;
				}
				else{
					teamNumber1=4;
					teamNumber2=7;
				}
			}
		}
		return firstRoundWinner;
	}
}*/
