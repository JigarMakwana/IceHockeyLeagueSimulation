package group11.Hockey.BusinessLogic.models;

import java.util.List;

public interface IConference {

	public boolean isConferenceNameValid(String conferenceName, List<Conference> conferences);
	public Conference getConferencefromConferenceName(String conferenceName, List<Conference> conferences);
}
