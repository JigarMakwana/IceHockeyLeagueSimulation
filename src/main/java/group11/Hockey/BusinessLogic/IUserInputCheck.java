package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Team;

public interface IUserInputCheck {
	public String conferenceNameFromUserCheck(List<Conference> conferencesList);

	public String divisonNameFromUserCheck(Conference conference);

	public void teamNameFromUserCheck(Team newTeam, ILeague league);

	public void generalManagerNameFromUserCheck(Team newTeam, ILeague league);

	public void headCoachNameFromUserCheck(Team newTeam, ILeague league);

	public void playerChoiceFromUser(Team newTeam, ILeague league);
}
