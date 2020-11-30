package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IDeadlines;
/**
 * 
 * @author Harry
 *
 */
public class Deadlines implements IDeadlines {

	private static Logger logger = LogManager.getLogger(Deadlines.class);

	@Override
	public Date getTradeDeadline(String startDate) {
		logger.debug("Entered getTradeDeadline()");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dateParts = startDate.split("/");
		int year = Integer.valueOf(dateParts[2]);
		year++;
		LocalDate eDate = LocalDate.of(year, Month.FEBRUARY, 1);
		LocalDate lastMonday = eDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
		String TradeEndDate = lastMonday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Date tradeDeadLine = null;
		try {
			tradeDeadLine = myFormat.parse(TradeEndDate);
		} catch (Exception e) {
			logger.error("Error occured : "+e);
			e.printStackTrace();
		}
		return tradeDeadLine;
	}

	@Override
	public Date getRegularSeasonDeadline(String startDate) {
		logger.debug("Entered getRegularSeasonDeadline()");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dateParts = startDate.split("/");
		int year = Integer.valueOf(dateParts[2]);
		year++;
		LocalDate eDate = LocalDate.of(year, Month.APRIL, 1);
		LocalDate firstSaturday = eDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		String regularSeasonEndDate = firstSaturday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Date regularDeadLine = null;
		try {
			regularDeadLine = myFormat.parse(regularSeasonEndDate);
		} catch (Exception e) {
			logger.error("Error occured : "+e);
			e.printStackTrace();
		}
		return regularDeadLine;
	}

	@Override
	public Date getStanleyPlayoffDeadline(String startDate) {
		logger.debug("Entered getStanleyPlayoffDeadline()");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dateParts = startDate.split("/");
		int year = Integer.valueOf(dateParts[2]);
		year++;
		String playoffEndDate = "01/06/" + Integer.toString(year);
		Date stanleyPlayoffDeadLine = null;
		try {
			stanleyPlayoffDeadLine = myFormat.parse(playoffEndDate);
		} catch (Exception e) {
			logger.error("Error occured : "+e);
			e.printStackTrace();
		}
		return stanleyPlayoffDeadLine;
	}

	@Override
	public Date getStanleyPlayoffBeginDate(String startDate) {
		logger.debug("Entered getStanleyPlayoffBeginDate()");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dateParts = startDate.split("/");
		int year = Integer.valueOf(dateParts[2]);
		year++;
		LocalDate eDate = LocalDate.of(year, Month.APRIL, 10);
        LocalDate secondWednesday = eDate.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.WEDNESDAY));
		String stanleyDate = secondWednesday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Date stanleyDateTime = null;
		try {
			stanleyDateTime = myFormat.parse(stanleyDate);
		} catch (Exception e) {
			logger.error("Error occured : "+e);
			e.printStackTrace();
		}
		return stanleyDateTime;
	}

}
