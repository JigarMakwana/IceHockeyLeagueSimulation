package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Team;

public class President implements ITrophyObserver {
	ILeague league;

	public President(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<Team> presidentTeams = league.getPresidentTeams();		
		Team president = null;
		int presidentPoints=0;		
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					if(team.getPoints()>=presidentPoints) {
						president=team;
						presidentPoints=president.getPoints();
					}
				}
			}
		}
		presidentTeams.add(president);	
	}
}
