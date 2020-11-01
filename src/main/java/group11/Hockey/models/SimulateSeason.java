package group11.Hockey.models;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.AgePlayer;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.db.CoachDb;
import group11.Hockey.db.GameplayConfigDb;
import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.ManagerDb;
import group11.Hockey.db.PlayerDb;
import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.League.LeagueDbImpl;

public class SimulateSeason {

	private HashMap<String, HashMap<Team, Team>> schedule;
	private League leagueObj;
	private ILeagueDb leagueDb;
	private IGameplayConfigDb gameplayConfigDb;
	private IPlayerDb playerDb;
	private ICoachDb coachDb;
	private IManagerDb managerDb;

	public SimulateSeason(HashMap<String, HashMap<Team, Team>> regularSchedule, League leagueObj,ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb,
			IPlayerDb playerDb, ICoachDb coachDb, IManagerDb managerDb) {
		this.schedule = regularSchedule;
		this.leagueObj = leagueObj;
		this.leagueDb = leagueDb;
		this.gameplayConfigDb = gameplayConfigDb;
		this.playerDb = playerDb;
		this.coachDb = coachDb;
		this.managerDb = managerDb;
	}

	public String StartSimulatingSeason(String date) {

		Advance advance = new Advance();
		Parse parseObj = new Parse();
		Deadlines deadline = new Deadlines();
		HashMap<String, HashMap<Team, Team>> playoffSchedule = new HashMap<>();
		PlayoffSchedule playoff = new PlayoffSchedule(leagueObj);
		List<Team> qualifiedTeams = leagueObj.getQualifiedTeams();
		Team winner;

		Date dateTime, stanleyStartDateTime, stanleyEndDateTime, regularSeasonEndDateTime, tradeDeadLine, firstRoundEnd,
				secondRoundEnd, semiFinalsEnd, finalsEnd;
		String stanleyDate, firstRound = null, secondRound = null, semiFinalRound = null;
		int startYear,endYear;

		startYear=parseObj.parseStringToYear(date);
		dateTime = parseObj.parseStringToDate(date);
		stanleyStartDateTime = deadline.getStanleyPlayoffBeginDate(date);
		stanleyDate = parseObj.parseDateToString(stanleyStartDateTime);
		stanleyEndDateTime = deadline.getStanleyPlayoffDeadline(date);
		regularSeasonEndDateTime = deadline.getRegularSeasonDeadline(date);
		tradeDeadLine = deadline.getTradeDeadline(date);

		try {
			firstRound = advance.getAdvanceDate(stanleyDate, 19);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			secondRound = advance.getAdvanceDate(firstRound, 10);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			semiFinalRound = advance.getAdvanceDate(secondRound, 5);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		endYear=parseObj.parseStringToYear(stanleyDate);
		firstRoundEnd = parseObj.parseStringToDate(firstRound);
		secondRoundEnd = parseObj.parseStringToDate(secondRound);
		semiFinalsEnd = parseObj.parseStringToDate(semiFinalRound);
		finalsEnd = stanleyEndDateTime;

		System.out.println("********** Simulation started **********");
		
		// on or before stanley playoff end date
		while (dateTime.compareTo(stanleyEndDateTime) <= 0) { 
			try {
				date = advance.getAdvanceDate(date, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dateTime = parseObj.parseStringToDate(date);

			// training(leagueObj,1);

			// on or before regular season end date
			if (dateTime.compareTo(regularSeasonEndDateTime) <= 0) { 
				CheckAndSimulateTodaySchedule simulateToday = new CheckAndSimulateTodaySchedule(schedule, leagueObj);
				simulateToday.CheckAndSimulateToday(date);
			}

			// on the last day regular season
			if (dateTime.equals(regularSeasonEndDateTime)) { 
				System.out.println("********** Regular season ended **********");
				System.out.println("\n********** Generating Playoff schedule **********");
				playoffSchedule = playoff.generatePlayoffScheduleRound1(stanleyDate);
			}

			// on and after stanley playoff begin date
			if (dateTime.compareTo(stanleyStartDateTime) >= 0) { 

				// on or before 1st playoff end date
				if (dateTime.compareTo(firstRoundEnd) <= 0) { 
					CheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, leagueObj);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}

				// on the last day 1st playoff round
				if (dateTime.equals(firstRoundEnd)) { 
					playoffSchedule = playoff.generatePlayoffScheduleReaminingRounds(date);
				}

				// on or before 2nd playoff end date
				if ((dateTime.compareTo(secondRoundEnd) <= 0) && (dateTime.compareTo(firstRoundEnd) >= 0)) { 
					CheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, leagueObj);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}

				// on the last day 2nd playoff round
				if (dateTime.equals(secondRoundEnd)) { 
					playoffSchedule = playoff.generatePlayoffScheduleReaminingRounds(date);
				}
				
				// on or before semi finals end date
				if ((dateTime.compareTo(semiFinalsEnd) <= 0) && (dateTime.compareTo(secondRoundEnd) >= 0)) { 
					CheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, leagueObj);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}
				
				// on the last day semi finals playoff round
				if (dateTime.equals(semiFinalsEnd)) { 
					playoffSchedule = playoff.generatePlayoffScheduleReaminingRounds(date);

				}
				
				// on or before finals end date
				if ((dateTime.compareTo(finalsEnd) <= 0) && (dateTime.compareTo(semiFinalsEnd) >= 0)) { 
					CheckAndSimulateTodaySchedule simulatePlayoffToday = new CheckAndSimulateTodaySchedule(
							playoffSchedule, leagueObj);
					simulatePlayoffToday.CheckAndSimulateToday(date);
				}

			}
			
			// on or before trade deadline
			if (dateTime.compareTo(tradeDeadLine) <= 0) { 
				// executeTrades();
				// System.out.println("Entered Trades");
			}

			AgePlayer ageplayer = new AgePlayer();
			ageplayer.increaseAge(leagueObj, 1);

			// on the last day of stanley playoff
			if ((dateTime.equals(stanleyEndDateTime))||(qualifiedTeams.size()==1)) { 
				int year=parseObj.parseStringToYear(date);
				String advanced="29/09/"+Integer.toString(year);
				Date advancedDate=parseObj.parseStringToDate(advanced);
				int daysBetween = (int) ((advancedDate.getTime() - dateTime.getTime())/ (24 * 60 * 60 * 1000));
				winner = qualifiedTeams.get(0);
				qualifiedTeams.remove(winner);
				System.out.println("\n********** Winner team of the season("+startYear+"/"+endYear+") is " + winner.getTeamName()+" **********");
				try {
					date = advance.getAdvanceDate(date, daysBetween);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				//ageplayer.increaseAge(leagueObj, daysBetween);

				leagueObj.insertLeagueObject(leagueObj, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
				break;
			}
			System.out.println("Persist");
			
			leagueObj.insertLeagueObject(leagueObj, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);

		}
		return date;
	}

}
