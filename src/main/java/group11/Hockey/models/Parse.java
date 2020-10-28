package group11.Hockey.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Parse {

	public Date parseStringToDate(String date) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTime = null;
		try {
			dateTime = myFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return dateTime;
	}
	public int parseStringToYear(String date) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		int year = 0;
		Date dateTime= null;
		try {
			dateTime = myFormat.parse(date);
			Calendar c = Calendar.getInstance();  
			c.setTime(dateTime);
			year=c.get(Calendar.YEAR);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return year;
	}
}
