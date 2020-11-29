package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public class CalderMemorial implements ITrophyObserver{
	ILeague league;
	
	public CalderMemorial(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<IPlayer> calderPlayers = league.getCalderPlayers();		
		IPlayer calder=null;
		float calderPoints=0;
		float playerPoints;
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
					List<IPlayer> playersList = team.getPlayers();
					for (IPlayer player : playersList) {
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
