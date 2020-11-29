package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class MauriceRichard implements ITrophyObserver{
	ILeague league;
	
	public MauriceRichard(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<Player> mauricePlayers = league.getMauriceRichardPlayers();		
		Player maurice=null;
		int mauriceGoals=0;
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					List<Player> playersList = team.getPlayers();
					for (Player player : playersList) {
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
