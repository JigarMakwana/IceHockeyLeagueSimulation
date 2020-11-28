package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Team;

public interface IUserInputCheck {
	public String conferenceNameFromUserCheck(List<IConference> conferencesList);

	public String divisonNameFromUserCheck(IConference conference);

	public void teamNameFromUserCheck(Team newTeam, ILeague league);

	public void generalManagerNameFromUserCheck(Team newTeam, ILeague league);

	public void headCoachNameFromUserCheck(Team newTeam, ILeague league);

	public void playerChoiceFromUser(Team newTeam, ILeague league);

	public int validateUserTradeInput();

	public int userResolveRosterInput(int listSize);
}
