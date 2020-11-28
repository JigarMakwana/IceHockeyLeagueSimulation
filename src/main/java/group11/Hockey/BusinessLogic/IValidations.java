package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;

public interface IValidations {
	public boolean isConferenceNameValid(String conferenceName, List<IConference> conferencesList);

	public boolean isDivisionValid(String divisionName, IConference conferenceItem);

	public boolean isTeamNameValid(String teamName, ILeague league);

	public boolean generalManagerNameCheck(String name, ILeague league);

	public boolean headCoachNameCheck(String name, ILeague league);

	public boolean playerCheck(String playerNumber, ILeague league, List<Integer> selectedValues, List<Player> skaters,
			List<Player> goalies);

	public boolean isStrBlank(String str);

	public boolean isNoOfSeasonsValueValid(String numberOfSeasons);

	public boolean isUserTradeInputValid(int userInput);

	public boolean isUserResolveRosterInputValid(int userInput, int listSize);
}
