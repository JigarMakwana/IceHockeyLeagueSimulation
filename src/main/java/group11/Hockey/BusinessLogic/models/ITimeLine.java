/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import java.util.Date;

public interface ITimeLine {
	public String getStartDate();

	public void setStartDate(String startDate);

	public String getLastSimulatedDate();

	public void setLastSimulatedDate(String lastSimulatedDate);

	public int getYear();

	public void setYear(int year);

	public String getSeasonEndDate();

	public void setSeasonEndDate(String seasonEndDate);

	public Date getStanleyEndDateTime();

	public void setStanleyEndDateTime(Date stanleyEndDateTime);

	public Date getRegularSeasonEndDateTime();

	public void setRegularSeasonEndDateTime(Date regularSeasonEndDateTime);

	public String getStanleyDate();

	public void setStanleyDate(String stanleyDate);

	public Date getFirstRoundEnd();

	public void setFirstRoundEnd(Date firstRoundEnd);

	public Date getSecondRoundEnd();

	public void setSecondRoundEnd(Date secondRoundEnd);

	public Date getSemiFinalsEnd();

	public void setSemiFinalsEnd(Date semiFinalsEnd);

	public String getCurrentDate();

	public void setCurrentDate(String currentDate);

	public Date getTradeDeadLine();

	public void setTradeDeadLine(Date tradeDeadLine);
}
