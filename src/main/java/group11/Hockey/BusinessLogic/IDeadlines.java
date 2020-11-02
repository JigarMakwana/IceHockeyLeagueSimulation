package group11.Hockey.BusinessLogic;

import java.util.Date;

public interface IDeadlines {
	public Date getTradeDeadline(String startDate);
	public Date getRegularSeasonDeadline(String startDate);
	public Date getStanleyPlayoffDeadline(String startDate);
	public Date getStanleyPlayoffBeginDate(String startDate);
}
