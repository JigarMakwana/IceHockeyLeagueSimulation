package group11.Hockey.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
