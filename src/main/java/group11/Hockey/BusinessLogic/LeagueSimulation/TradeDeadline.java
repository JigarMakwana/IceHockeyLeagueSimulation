package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class TradeDeadline {
	
	public Date getTradeDeadline(String startDate) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dateParts = startDate.split("/");			
		int year = Integer.valueOf(dateParts[2]);
		year++;
		LocalDate eDate = LocalDate.of(year, Month.FEBRUARY, 1);	
	    LocalDate lastMonday = eDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
	    String TradeEndDate = lastMonday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    Date tradeDeadLine = null;
		try {
			tradeDeadLine = myFormat.parse(TradeEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	    return tradeDeadLine;
	}
}
