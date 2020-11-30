// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IDeadlines;

public class DeadlinesTest {

	@Test
	public void getTradeDeadlineTest() throws ParseException {
		IDeadlines deadLines = DefaultHockeyFactory.makeDeadlines();
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = deadLines.getTradeDeadline("30/10/2020");
		Date deadLine = myFormat.parse("22/02/2021");
		Assert.assertEquals(deadLine, date);
	}
	
	@Test
	public void getRegularSeasonDeadlineTest() throws ParseException {
		IDeadlines deadLines = DefaultHockeyFactory.makeDeadlines();
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = deadLines.getRegularSeasonDeadline("30/10/2020");
		Date deadLine = myFormat.parse("03/04/2021");
		Assert.assertEquals(deadLine, date);
		
	}
	
	@Test
	public void getStanleyPlayoffDeadlineTest() throws ParseException {
		IDeadlines deadLines = DefaultHockeyFactory.makeDeadlines();
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = deadLines.getStanleyPlayoffDeadline("30/10/2020");
		Date deadLine = myFormat.parse("01/06/2021");
		Assert.assertEquals(deadLine, date);
	}
	
	@Test
	public void getStanleyPlayoffBeginDateTest() throws ParseException {
		IDeadlines deadLines = DefaultHockeyFactory.makeDeadlines();
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = deadLines.getStanleyPlayoffBeginDate("30/10/2020");
		Date deadLine = myFormat.parse("14/04/2021");
		Assert.assertEquals(deadLine, date);
	}


}
