package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class Veniza implements ITrophyObserver {
	ILeague league;
	
	public Veniza(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<Player> venizaPlayers = league.getVenizaPlayers();		
		Player veniza=null;
		int savesByVenizaGoalie=0;
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					List<Player> playersList = team.getPlayers();
					for (Player player : playersList) {
						if(player.getSavesByGoalieInSeason()>savesByVenizaGoalie) {
							veniza=player;
							savesByVenizaGoalie=veniza.getSavesByGoalieInSeason();
						}
					}					
				}
			}
		}
		venizaPlayers.add(veniza);
	}

}
