/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import java.util.Date;

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

	public Date getTradeDeadLine() {
		return tradeDeadLine;
	}

	public void setTradeDeadLine(Date tradeDeadLine) {
		this.tradeDeadLine = tradeDeadLine;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getLastSimulatedDate() {
		return lastSimulatedDate;
	}

	public void setLastSimulatedDate(String lastSimulatedDate) {
		this.lastSimulatedDate = lastSimulatedDate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSeasonEndDate() {
		return seasonEndDate;
	}

	public void setSeasonEndDate(String seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public Date getStanleyEndDateTime() {
		return stanleyEndDateTime;
	}

	public void setStanleyEndDateTime(Date stanleyEndDateTime) {
		this.stanleyEndDateTime = stanleyEndDateTime;
	}

	public Date getRegularSeasonEndDateTime() {
		return regularSeasonEndDateTime;
	}

	public void setRegularSeasonEndDateTime(Date regularSeasonEndDateTime) {
		this.regularSeasonEndDateTime = regularSeasonEndDateTime;
	}

	public String getStanleyDate() {
		return stanleyDate;
	}

	public void setStanleyDate(String stanleyDate) {
		this.stanleyDate = stanleyDate;
	}

	public Date getFirstRoundEnd() {
		return firstRoundEnd;
	}

	public void setFirstRoundEnd(Date firstRoundEnd) {
		this.firstRoundEnd = firstRoundEnd;
	}

	public Date getSecondRoundEnd() {
		return secondRoundEnd;
	}

	public void setSecondRoundEnd(Date secondRoundEnd) {
		this.secondRoundEnd = secondRoundEnd;
	}

	public Date getSemiFinalsEnd() {
		return semiFinalsEnd;
	}

	public void setSemiFinalsEnd(Date semiFinalsEnd) {
		this.semiFinalsEnd = semiFinalsEnd;
	}

}
