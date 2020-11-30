// Author: Harry B00856244
package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public class JackAdams implements ITrophyObserver{
ILeague league;
	
	public JackAdams(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<ICoach> jackAdamsCoaches = league.getJackAdamsCoaches();		
		ICoach jackAdams=null;
		float jackAdamsPoints=0;
		float playerPoints;
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<IDivision> divisionList = conference.getDivisions();
			for (IDivision division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
					List<IPlayer> playersList = team.getPlayers();
					for (IPlayer player : playersList) {
						playerPoints=player.getSkating()+player.getShooting()+player.getChecking()+player.getSaving();
						if(playerPoints>jackAdamsPoints) {							
							jackAdams=team.getHeadCoach();;
							jackAdamsPoints=playerPoints;
						}
					}					
				}
			}
		}
		jackAdamsCoaches.add(jackAdams);
	}

}

