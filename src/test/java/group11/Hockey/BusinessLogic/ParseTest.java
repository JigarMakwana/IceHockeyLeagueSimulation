package group11.Hockey.BusinessLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;


public class ParseTest {

	@Test
	public void stringToDateTest() {
		Parse parse=new Parse();
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = myFormat.parse("01/10/2020");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date dateTime = parse.stringToDate("01/10/2020");
		Assert.assertEquals(date, dateTime);
	}
	
	@Test
	public void stringToYearTest() {
		Parse parse=new Parse();
		int dateYear= parse.stringToYear("01/10/2020");
		Assert.assertEquals(2020,dateYear);
	}
	
	@Test
	public void dateToStringTest() {
		Parse parseObj=new Parse();
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTime = null;
		try {
			dateTime = myFormat.parse("01/10/2020");
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		String newDate=parseObj.dateToString(dateTime);
		Assert.assertEquals("01/10/2020",newDate);
	}
	
	@Test
	public void getFirstSaturdayOfAprilInYear() {
		Parse parseObj=new Parse();
		Date firstSaturday=parseObj.getFirstSaturdayOfAprilInYear(2021);
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTime = null;
		try {
			dateTime = myFormat.parse("03/04/2021");
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		Assert.assertEquals(dateTime,firstSaturday);
	}
}
