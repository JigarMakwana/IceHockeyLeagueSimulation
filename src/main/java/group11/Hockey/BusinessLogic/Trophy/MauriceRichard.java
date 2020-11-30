// Author: Harry B00856244
package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public class MauriceRichard implements ITrophyObserver{
	ILeague league;
	
	public MauriceRichard(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<IPlayer> mauricePlayers = league.getMauriceRichardPlayers();		
		IPlayer maurice=null;
		int mauriceGoals=0;
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<IDivision> divisionList = conference.getDivisions();
			for (IDivision division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
					List<IPlayer> playersList = team.getPlayers();
					for (IPlayer player : playersList) {
						if(player.getGoalsInSeason()>mauriceGoals) {
							maurice=player;
							mauriceGoals=maurice.getGoalsInSeason();
						}
					}					
				}
			}
		}
		mauricePlayers.add(maurice);
	}

}
