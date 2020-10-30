package group11.Hockey.BusinessLogic.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class Advance {

private String advanceTime;
private String advanceDate;
	
	public String getAdvanceTime(String time,int hours) throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm:ss");
		Date dateTime=myFormat.parse(time);
		Calendar c = Calendar.getInstance(); 
		c.setTime(dateTime); 
		c.add(Calendar.HOUR_OF_DAY, hours);
		 advanceTime = myFormat.format(c.getTime());
		 return advanceTime;
	}
	
	public String getAdvanceDate(String date,int days) throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTime=myFormat.parse(date);
		Calendar c = Calendar.getInstance(); 
		c.setTime(dateTime); 
		c.add(Calendar.DATE, days);
		 advanceDate = myFormat.format(c.getTime());
		 return advanceDate;
	}
	
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
	
	public int getYearFromStringDate(String date) {
		String[] dateParts = date.split("/");			
		int year = Integer.valueOf(dateParts[2]);
		return year;
	}
	
}
