package group11.Hockey.BusinessLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class Parse implements IParse{

	@Override
	public Date stringToDate(String date) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTime = null;
		try {
			dateTime = myFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return dateTime;
	}
	
	@Override
	public int stringToYear(String date) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		int year = 0;
		Date dateTime= null;
		try {
			dateTime = myFormat.parse(date);
			Calendar c = Calendar.getInstance();  
			c.setTime(dateTime);
			year=c.get(Calendar.YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return year;
	}
	
	@Override
	public String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String stringDate = dateFormat.format(date);  
	    return stringDate;
	}
	
	@Override
	public Date getFirstSaturdayOfAprilInYear(int year) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate eDate = LocalDate.of(year, Month.APRIL, 1);	
	    LocalDate firstSaturday = eDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
	    String startDate = firstSaturday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    Date firstSat = null;
		try {
			firstSat = myFormat.parse(startDate);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return firstSat;
	}

}
