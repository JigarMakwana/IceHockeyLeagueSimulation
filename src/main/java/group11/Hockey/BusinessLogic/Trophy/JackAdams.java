package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

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
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					List<Player> playersList = team.getPlayers();
					for (Player player : playersList) {
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

