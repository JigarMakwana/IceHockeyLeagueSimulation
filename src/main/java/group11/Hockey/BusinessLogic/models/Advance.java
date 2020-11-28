package group11.Hockey.BusinessLogic.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Advance implements IAdvance {

	private String advanceTime;
	private String advanceDate;
	private static Logger logger = LogManager.getLogger(Advance.class);

	@Override
	public String getAdvanceTime(String time, int hours) {
		logger.info("Entered getAdvanceTime()");
		SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm:ss");
		Date dateTime = null;
		try {
			dateTime = myFormat.parse(time);
		} catch (ParseException e) {
			logger.error("Exception occured : "+e);
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(dateTime);
		c.add(Calendar.HOUR_OF_DAY, hours);
		advanceTime = myFormat.format(c.getTime());
		return advanceTime;
	}

	@Override
	public String getAdvanceDate(String date, int days) {
		logger.info("Entered getAdvanceDate()");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTime = null;
		try {
			dateTime = myFormat.parse(date);
		} catch (ParseException e) {
			logger.error("Exception occured : "+e);
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(dateTime);
		c.add(Calendar.DATE, days);
		advanceDate = myFormat.format(c.getTime());
		return advanceDate;
	}

}
