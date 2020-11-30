// Author: Harry B00856244
package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public class RobHawkeyMemorial implements ITrophyObserver {
	ILeague league;
	
	public RobHawkeyMemorial(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<IPlayer> robHawkeyPlayers = league.getRobHawkeyPlayers();
		IPlayer robHawkeyMemorial=null;
		int robHawkeyMemorialPenalities=0;
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<IDivision> divisionList = conference.getDivisions();
			for (IDivision division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
					List<IPlayer> playersList = team.getPlayers();
					for (IPlayer player : playersList) {
						if(player.getPenaltiesInSeason()>robHawkeyMemorialPenalities) {
							robHawkeyMemorial=player;
							robHawkeyMemorialPenalities=robHawkeyMemorial.getPenaltiesInSeason();
						}
					}					
				}
			}
		}
		robHawkeyPlayers.add(robHawkeyMemorial);
	}

}

