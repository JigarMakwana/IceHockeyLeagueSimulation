package group11.Hockey.models;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class Deadlines {

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tradeDeadLine;
	}

	public Date getRegularSeasonDeadline(String startDate) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dateParts = startDate.split("/");
		int year = Integer.valueOf(dateParts[2]);
		year++;
		LocalDate eDate = LocalDate.of(year, Month.APRIL, 1);
		LocalDate firstSaturday = eDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		String regularSeasonEndDate = firstSaturday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Date regularDeadLine = null;
		try {
			regularDeadLine = myFormat.parse(regularSeasonEndDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regularDeadLine;
	}

	public Date getStanleyPlayoffDeadline(String startDate) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dateParts = startDate.split("/");
		int year = Integer.valueOf(dateParts[2]);
		year++;
		String playoffEndDate = "01/06/" + Integer.toString(year);
		Date stanleyPlayoffDeadLine = null;
		try {
			stanleyPlayoffDeadLine = myFormat.parse(playoffEndDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stanleyPlayoffDeadLine;
	}

	public Date getStanleyPlayoffBeginDate(String startDate) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dateParts = startDate.split("/");
		int year = Integer.valueOf(dateParts[2]);
		year++;
		LocalDate eDate = LocalDate.of(year, Month.APRIL, 10);
        LocalDate secondWednesday = eDate.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.WEDNESDAY));
		String stanleyDate = secondWednesday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Date stanleyDateTime = null;
		try {
			stanleyDateTime = myFormat.parse(stanleyDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stanleyDateTime;
	}

}
