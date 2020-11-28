package group11.Hockey.BusinessLogic.models;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class contains the business logic for the Conference model
 * 
 *
 */
public class Conference implements IConference {
	private String conferenceName;
	private List<Division> divisions = null;
	private static Logger logger = LogManager.getLogger(Conference.class);

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
		logger.info("Entered getConferenceName()");
		return conferenceName;
	}

	/**
	 * @param conferenceName the conferenceName to set
	 */
	public void setConferenceName(String conferenceName) {
		logger.info("Entered setConferenceName()");
		this.conferenceName = conferenceName;
	}

	/**
	 * @return the divisions
	 */
	public List<Division> getDivisions() {
		logger.info("Entered getDivisions()");
		return divisions;
	}

	/**
	 * @param divisions the divisions to set
	 */
	public void setDivisions(List<Division> divisions) {
		logger.info("Entered setDivisions()");
		this.divisions = divisions;
	}

	@Override
	public String toString() {
		logger.info("Entered toString()");
		return "Conference [conferenceName=" + conferenceName + ", divisions=" + divisions + "]";
	}

	public boolean isConferenceNameValid(String conferenceName, List<IConference> conferences) {
		logger.info("Entered isConferenceNameValid()");
		boolean isConferanceNameValid = false;
		for (IConference conference : conferences) {
			if (conference.getConferenceName().equalsIgnoreCase(conferenceName)) {
				logger.info(conference.getConferenceName()+" is valid conference");
				isConferanceNameValid = true;
				break;
			}
		}
		return isConferanceNameValid;
	}

	public IConference getConferencefromConferenceName(String conferenceName, List<IConference> conferences) {
		logger.info("Entered getConferencefromConferenceName()");
		IConference conference = null;
		for (IConference conf : conferences) {
			if (conf.getConferenceName().equalsIgnoreCase(conferenceName)) {
				logger.info(conf.getConferenceName()+" is a valid conference");
				conference = conf;
				break;
			}
		}
		return conference;
	}

}
