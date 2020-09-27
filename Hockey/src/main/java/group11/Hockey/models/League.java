package group11.Hockey.models;

import java.util.List;

/**
 * This class contains the business logic for the League model
 * 
 * @author jatinpartaprana
 *
 */
public class League {
	private String leagueName;
	private List<Conference> conferences = null;
	private List<FreeAgent> freeAgents = null;

	public League(String leagueName, List<Conference> conferences, List<FreeAgent> freeAgents) {
		super();
		this.leagueName = leagueName;
		this.conferences = conferences;
		this.freeAgents = freeAgents;
	}
	
	public League() {
		super();
	}

	/**
	 * @return the leagueName
	 */
	public String getLeagueName() {
		return leagueName;
	}

	/**
	 * @param leagueName the leagueName to set
	 */
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	/**
	 * @return the conferences
	 */
	public List<Conference> getConferences() {
		return conferences;
	}

	/**
	 * @param conferences the conferences to set
	 */
	public void setConferences(List<Conference> conferences) {
		this.conferences = conferences;
	}

	/**
	 * @return the freeAgents
	 */
	public List<FreeAgent> getFreeAgents() {
		return freeAgents;
	}

	/**
	 * @param freeAgents the freeAgents to set
	 */
	public void setFreeAgents(List<FreeAgent> freeAgents) {
		this.freeAgents = freeAgents;
	}
	
//	public boolean isConferenceNameValid(String conferenceName) {
//		boolean isConferanceNameValid = false;
//		for(Conference conference: conferences) {
//			if(conference.getConferenceName().equalsIgnoreCase(conferenceName)) {
//				isConferanceNameValid = true;
//				break;
//			}
//		}
//		return isConferanceNameValid;
//	}
	
//	public Conference getConferencefromConferenceName(String conferenceName) {
//		Conference conf = null;
//		for(Conference conference: conferences) {
//			if(conference.getConferenceName().equalsIgnoreCase(conferenceName)) {
//				conf = conference;
//				break;
//			}
//		}
//		return conf;
//	}
}
