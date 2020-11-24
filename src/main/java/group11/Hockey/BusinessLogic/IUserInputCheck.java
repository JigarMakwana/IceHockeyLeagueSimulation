package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;

public interface IUserInputCheck {
	public String conferenceNameFromUserCheck(List<Conference> conferencesList);
	public String divisonNameFromUserCheck(Conference conference);
	public void teamNameFromUserCheck(Team newTeam, League league);
	public void generalManagerNameFromUserCheck(Team newTeam, League league);
	public void headCoachNameFromUserCheck(Team newTeam, League league);
	public void playerChoiceFromUser(Team newTeam, League league);
	public int userResolveRosterInput(int listSize);
	public int validateUserTradeInput();
}
