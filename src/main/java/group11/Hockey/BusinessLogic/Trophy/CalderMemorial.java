package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class CalderMemorial implements ITrophyObserver{
	ILeague league;
	
	public CalderMemorial(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<Player> calderPlayers = league.getCalderPlayers();		
		Player calder=null;
		float calderPoints=0;
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
						if(playerPoints>calderPoints) {
							calder=player;
							calderPoints=playerPoints;
						}
					}					
				}
			}
		}
		calderPlayers.add(calder);
	}

}
