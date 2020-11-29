package group11.Hockey.BusinessLogic.models;

import java.util.List;

public interface IConference {

	public boolean isConferenceNameValid(String conferenceName, List<IConference> conferences);

	public IConference getConferencefromConferenceName(String conferenceName, List<IConference> conferences);

	public List<Division> getDivisions();

	public void setDivisions(List<Division> divisions);

	public void setConferenceName(String name);

	public String getConferenceName();

}
