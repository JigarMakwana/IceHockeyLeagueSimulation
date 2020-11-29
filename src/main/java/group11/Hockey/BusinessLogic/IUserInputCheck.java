package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;

public interface IUserInputCheck {
	public String conferenceNameFromUserCheck(List<IConference> conferencesList);

	public String divisonNameFromUserCheck(IConference conference);

	public void teamNameFromUserCheck(ITeam newTeam, ILeague league);

	public void generalManagerNameFromUserCheck(ITeam newTeam, ILeague league);

	public void headCoachNameFromUserCheck(ITeam newTeam, ILeague league);

	public void playerChoiceFromUser(ITeam newTeam, ILeague league);

	public int validateUserTradeInput();

	public int userResolveRosterInput(int listSize);
}
