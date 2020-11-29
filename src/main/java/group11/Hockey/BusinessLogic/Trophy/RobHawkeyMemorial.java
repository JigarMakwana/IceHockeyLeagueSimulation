package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class RobHawkeyMemorial implements ITrophyObserver {
	ILeague league;
	
	public RobHawkeyMemorial(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<Player> robHawkeyPlayers = league.getRobHawkeyPlayers();
		Player robHawkeyMemorial=null;
		int robHawkeyMemorialPenalities=0;
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					List<Player> playersList = team.getPlayers();
					for (Player player : playersList) {
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

