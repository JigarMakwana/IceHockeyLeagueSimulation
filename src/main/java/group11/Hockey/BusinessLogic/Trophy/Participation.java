// Author: Harry B00856244
package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;

public class Participation implements ITrophyObserver {
	ILeague league;
	private Integer maxPoints=Integer.MAX_VALUE;

	public Participation(ILeague league) {
		this.league=league;
	}
	
	@Override
	public void AwardTrophy() {
		List<ITeam> participationTeams = league.getParticipationTeams();
		ITeam participation = null;		
		Integer participationPoints=maxPoints;
		List<IConference> conferenceList = league.getConferences();
		for (IConference conference : conferenceList) {
			List<IDivision> divisionList = conference.getDivisions();
			for (IDivision division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
					if(team.getPoints()<=participationPoints && team.getPoints()>0) {
						participation=team;
						participationPoints=participation.getPoints();
					}
				}
			}
		}
		participationTeams.add(participation);	}
}
