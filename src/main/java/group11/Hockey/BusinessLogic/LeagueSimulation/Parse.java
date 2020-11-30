package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Parse implements IParse{

	private static Logger logger = LogManager.getLogger(Parse.class);

	@Override
	public Date stringToDate(String date) {
		logger.debug("Entered stringToDate()");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTime = null;
		try {
			dateTime = myFormat.parse(date);
		} catch (ParseException e) {
			logger.error("Exception caught : "+e);
			e.printStackTrace();
		}
		return dateTime;
	}

	@Override
	public int stringToYear(String date) {
		logger.debug("Entered stringToYear()");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		int year = 0;
		Date dateTime= null;
		try {
			dateTime = myFormat.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(dateTime);
			year=c.get(Calendar.YEAR);
		} catch (ParseException e) {
			logger.error("Exception caught : "+e);
			e.printStackTrace();
		}
		return year;
	}

	@Override
	public String dateToString(Date date) {
		logger.debug("Entered dateToString()");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = dateFormat.format(date);
	    return stringDate;
	}

	@Override
	public Date getFirstSaturdayOfAprilInYear(int year) {
		logger.debug("Entered getFirstSaturdayOfAprilInYear()");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate eDate = LocalDate.of(year, Month.APRIL, 1);
	    LocalDate firstSaturday = eDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
	    String startDate = firstSaturday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    Date firstSat = null;
		try {
			firstSat = myFormat.parse(startDate);
		} catch (Exception e) {
			logger.error("Exception caught : "+e);
			e.printStackTrace();
		}
		return firstSat;
	}

}
