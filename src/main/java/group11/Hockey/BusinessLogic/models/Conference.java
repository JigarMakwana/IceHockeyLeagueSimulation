package group11.Hockey.BusinessLogic.models;

import java.util.List;

/**
 * This class contains the business logic for the Conference model
 *
 *
 */
public class Conference implements IConference {
	private String conferenceName;
	private List<Division> divisions = null;

	public Conference() {
	}

	public Conference(String conferenceName, List<? extends IDivision> divisions) {
		super();
		this.conferenceName = conferenceName;
		this.divisions = (List<Division>) divisions;
	}

	/**
	 * @return the conferenceName
	 */
	public String getConferenceName() {
		return conferenceName;
	}

	/**
	 * @param conferenceName the conferenceName to set
	 */
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	/**
	 * @return the divisions
	 */
	public List<Division> getDivisions() {
		return divisions;
	}

	/**
	 * @param divisions the divisions to set
	 */
	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}

	@Override
	public String toString() {
		return "Conference [conferenceName=" + conferenceName + ", divisions=" + divisions + "]";
	}

	public boolean isConferenceNameValid(String conferenceName, List<IConference> conferences) {
		boolean isConferanceNameValid = false;
		for (IConference conference : conferences) {
			if (conference.getConferenceName().equalsIgnoreCase(conferenceName)) {
				isConferanceNameValid = true;
				break;
			}
		}
		return isConferanceNameValid;
	}

	public IConference getConferencefromConferenceName(String conferenceName, List<IConference> conferences) {
		IConference conference = null;
		for (IConference conf : conferences) {
			if (conf.getConferenceName().equalsIgnoreCase(conferenceName)) {
				conference = conf;
				break;
			}
		}
		return conference;
	}

}
