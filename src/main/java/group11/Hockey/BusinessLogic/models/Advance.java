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

public class Advance implements IAdvance {

private String advanceTime;
private String advanceDate;
	
	@Override
	public String getAdvanceTime(String time,int hours) {
		SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm:ss");
		Date dateTime = null;
		try {
			dateTime = myFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance(); 
		c.setTime(dateTime); 
		c.add(Calendar.HOUR_OF_DAY, hours);
		 advanceTime = myFormat.format(c.getTime());
		 return advanceTime;
	}
	
	@Override
	public String getAdvanceDate(String date,int days) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTime = null;
		try {
			dateTime = myFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance(); 
		c.setTime(dateTime); 
		c.add(Calendar.DATE, days);
		 advanceDate = myFormat.format(c.getTime());
		 return advanceDate;
	}
	
		
}
