package group11.Hockey;

import java.util.List;

import group11.Hockey.models.Conference;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;

public interface IValidations {
	public boolean isConferenceNameValid(String conferenceName, List<Conference> conferencesList);

	public boolean isDivisionValid(String divisionName, Conference conferenceItem);

	public boolean isTeamNameValid(String teamName, League league);

	public boolean generalManagerNameCheck(String name, League league);

	public boolean headCoachNameCheck(String name, League league);
	
	public boolean playerCheck(int playerNumber, League league, List<Integer> selectedValues, List<Player> skaters,
			List<Player> goalies);

}
