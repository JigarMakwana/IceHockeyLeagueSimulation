/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TimeLine implements ITimeLine {
	private String startDate;
	private String lastSimulatedDate;
	private int year;
	private String seasonEndDate;
	private Date stanleyEndDateTime;
	private Date regularSeasonEndDateTime;
	private String stanleyDate;
	private Date firstRoundEnd;
	private Date secondRoundEnd;
	private Date semiFinalsEnd;
	private String currentDate;
	private Date tradeDeadLine;
	private static Logger logger = LogManager.getLogger(TimeLine.class);

	public Date getTradeDeadLine() {
		logger.info("Entered getTradeDeadLine()");
		return tradeDeadLine;
	}

	public void setTradeDeadLine(Date tradeDeadLine) {
		logger.info("Entered setTradeDeadLine()");
		this.tradeDeadLine = tradeDeadLine;
	}

	public String getCurrentDate() {
		logger.info("Entered getCurrentDate()");
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		logger.info("Entered setCurrentDate()");
		this.currentDate = currentDate;
	}

	public String getStartDate() {
		logger.info("Entered getStartDate()");
		return startDate;
	}

	public void setStartDate(String startDate) {
		logger.info("Entered setStartDate()");
		this.startDate = startDate;
	}

	public String getLastSimulatedDate() {
		logger.info("Entered getLastSimulatedDate()");
		return lastSimulatedDate;
	}

	public void setLastSimulatedDate(String lastSimulatedDate) {
		logger.info("Entered setLastSimulatedDate()");
		this.lastSimulatedDate = lastSimulatedDate;
	}

	public int getYear() {
		logger.info("Entered getYear()");
		return year;
	}

	public void setYear(int year) {
		logger.info("Entered setYear()");
		this.year = year;
	}

	public String getSeasonEndDate() {
		logger.info("Entered getSeasonEndDate()");
		return seasonEndDate;
	}

	public void setSeasonEndDate(String seasonEndDate) {
		logger.info("Entered setSeasonEndDate()");
		this.seasonEndDate = seasonEndDate;
	}

	public Date getStanleyEndDateTime() {
		logger.info("Entered getStanleyEndDateTime()");
		return stanleyEndDateTime;
	}

	public void setStanleyEndDateTime(Date stanleyEndDateTime) {
		logger.info("Entered setStanleyEndDateTime()");
		this.stanleyEndDateTime = stanleyEndDateTime;
	}

	public Date getRegularSeasonEndDateTime() {
		logger.info("Entered getRegularSeasonEndDateTime()");
		return regularSeasonEndDateTime;
	}

	public void setRegularSeasonEndDateTime(Date regularSeasonEndDateTime) {
		logger.info("Entered setRegularSeasonEndDateTime()");
		this.regularSeasonEndDateTime = regularSeasonEndDateTime;
	}

	public String getStanleyDate() {
		logger.info("Entered getStanleyDate()");
		return stanleyDate;
	}

	public void setStanleyDate(String stanleyDate) {
		logger.info("Entered setStanleyDate()");
		this.stanleyDate = stanleyDate;
	}

	public Date getFirstRoundEnd() {
		logger.info("Entered getFirstRoundEnd()");
		return firstRoundEnd;
	}

	public void setFirstRoundEnd(Date firstRoundEnd) {
		logger.info("Entered setFirstRoundEnd()");
		this.firstRoundEnd = firstRoundEnd;
	}

	public Date getSecondRoundEnd() {
		logger.info("Entered getSecondRoundEnd()");
		return secondRoundEnd;
	}

	public void setSecondRoundEnd(Date secondRoundEnd) {
		logger.info("Entered setSecondRoundEnd()");
		this.secondRoundEnd = secondRoundEnd;
	}

	public Date getSemiFinalsEnd() {
		logger.info("Entered getSemiFinalsEnd()");
		return semiFinalsEnd;
	}

	public void setSemiFinalsEnd(Date semiFinalsEnd) {
		logger.info("Entered setSemiFinalsEnd()");
		this.semiFinalsEnd = semiFinalsEnd;
	}

}
