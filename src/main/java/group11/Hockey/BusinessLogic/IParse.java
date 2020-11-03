package group11.Hockey.BusinessLogic;

import java.util.Date;

public interface IParse {
	public Date stringToDate(String date);

	public int stringToYear(String date);

	public String dateToString(Date date);

	public Date getFirstSaturdayOfAprilInYear(int year);
}
