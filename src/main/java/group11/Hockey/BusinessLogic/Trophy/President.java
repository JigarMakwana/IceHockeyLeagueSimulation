// Author: Harry B00856244
package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;

public class President implements ITrophyObserver {
	ILeague league;

	public President(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<ITeam> presidentTeams = league.getPresidentTeams();		
		ITeam president = null;
		int presidentPoints=0;		
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<IDivision> divisionList = conference.getDivisions();
			for (IDivision division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
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
