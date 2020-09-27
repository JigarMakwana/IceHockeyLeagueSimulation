package group11.Hockey.models;

import java.util.List;

/**
 * This class containe the business logic for the Conference model
 * 
 * @author jatinpartaprana
 *
 */
public class Conference {
	private String conferenceName;
	private List<Division> divisions = null;

	public Conference(String conferenceName, List<Division> divisions) {
		super();
		this.conferenceName = conferenceName;
		this.divisions = divisions;
	}
	
	public Conference() {
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
	
	public boolean isConferenceNameValid(String conferenceName, List<Conference> conferences) {
		boolean isConferanceNameValid = false;
		for(Conference conference: conferences) {
			if(conference.getConferenceName().equalsIgnoreCase(conferenceName)) {
				isConferanceNameValid = true;
				break;
			}
		}
		return isConferanceNameValid;
	}
	
	public Conference getConferencefromConferenceName(String conferenceName, List<Conference> conferences) {
		Conference conference = null;
		for(Conference conf: conferences) {
			if(conf.getConferenceName().equalsIgnoreCase(conferenceName)) {
				conference = conf;
				break;
			}
		}
		return conference;
	}

//	public boolean isDivisionNameValid(String divisionName) {
//		boolean isDivisionNameValid = false;
//		for(Division division: divisions) {
//			if(division.getDivisionName().equalsIgnoreCase(divisionName)) {
//				isDivisionNameValid = true;
//				break;
//			}
//		}
//		return isDivisionNameValid;
//	}
//	
//	public Division getConferencefromConferenceName(String divisionName) {
//		Division division = null;
//		for(Division div: divisions) {
//			if(div.getDivisionName().equalsIgnoreCase(divisionName)) {
//				division = div;
//				break;
//			}
//		}
//		return division;
//	}
}
