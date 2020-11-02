package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;

public class Schedule implements ISchedule {

	private League league;

	public Schedule(League league) {
		this.league = league;
	}

	@Override
	public HashMap<String, HashMap<Team, Team>> getSeasonSchedule(String startDate) {
		IParse parse = new Parse();
		IAdvance advance = new Advance();
		IDeadlines deadline = new Deadlines();
		IPrintToConsole console = new PrintToConsole();
		Team t1;
		Team t2;
		Division div1;
		Division div2;
		Conference con1;
		Conference con2;
		int divLimit;
		int divLimitReached;
		int inConLimit;
		int inConLimitReached;
		int outConLimit;
		int outConLimitReached;
		int team1DivCount;
		int team1InConCount;
		int team1OutConCount;
		int totalGames = 0;
		int team2DivCount;
		int team2InConCount;
		int team2OutConCount;
		int totalDivTeams;
		int totalInConTeams;
		int totalOutConTeams;
		int team2TotalCount;
		int totalTeams = 0;
		int loop;
		String message;
		String date = startDate;
		String time = "00:00:00";
		Date dateTime = parse.stringToDate(date);
		Date endDateTime = deadline.getRegularSeasonDeadline(startDate);
		String regularSeasonStartDate = advance.getAdvanceDate(date, 1);
		ArrayList<Team> teamName = new ArrayList<Team>();
		HashMap<Team, Integer> scheduledDivisionMatchCount = new HashMap<>();
		HashMap<Team, Integer> scheduledInConferenceMatchCount = new HashMap<>();
		HashMap<Team, Integer> scheduledOutConferenceMatchCount = new HashMap<>();
		HashMap<String, Integer> simulatedHashmap = new HashMap<>();
		HashMap<Team, Conference> teamConference = new HashMap<>();
		HashMap<Team, Division> teamDivision = new HashMap<>();
		HashMap<Conference, Integer> conTeamCount = new HashMap<>();
		HashMap<Division, Integer> divTeamCount = new HashMap<>();
		HashMap<Team, Integer> totalGameCount = new HashMap<>();
		HashMap<String, HashMap<Team, Team>> regularSchedule = new HashMap<>();
		List<Conference> cconferenceList = league.getConferences();
		for (Conference conference : cconferenceList) {
			conTeamCount.put(conference, 0);
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				divTeamCount.put(division, 0);
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					teamName.add(team);
					scheduledDivisionMatchCount.put(team, 0);
					scheduledInConferenceMatchCount.put(team, 0);
					scheduledOutConferenceMatchCount.put(team, 0);
					teamConference.put(team, conference);
					teamDivision.put(team, division);
					totalTeams++;
					conTeamCount.put(conference, conTeamCount.get(conference) + 1);
					divTeamCount.put(division, divTeamCount.get(division) + 1);
					totalGameCount.put(team, 0);
				}
			}
		}
		for (int i = 0; i < totalTeams; i++) {
			t1 = teamName.get(i);
			team1DivCount = scheduledDivisionMatchCount.get(t1);
			team1InConCount = scheduledInConferenceMatchCount.get(t1);
			team1OutConCount = scheduledOutConferenceMatchCount.get(t1);
			divLimit = 28;
			inConLimit = 28;
			divLimitReached = team1DivCount;
			inConLimitReached = team1InConCount;
			outConLimitReached = team1OutConCount;
			div1 = teamDivision.get(t1);
			con1 = teamConference.get(t1);
			totalDivTeams = (divTeamCount.get(div1));
			totalInConTeams = (conTeamCount.get(con1));
			if (totalGameCount.get(t1) >= 82) {
				break;
			}
			for (int j = i + 1; j < totalTeams; j++) {
				t2 = teamName.get(j);
				div2 = teamDivision.get(t2);
				team2DivCount = scheduledDivisionMatchCount.get(t2);
				if (divLimitReached == divLimit) {
					break;
				}
				if ((div1.getDivisionName() == div2.getDivisionName())) {
					if ((team2DivCount < divLimit)) {
						date = advance.getAdvanceDate(date, 1);
						dateTime = parse.stringToDate(date);
						if (dateTime.compareTo(endDateTime) > 0) {
							date = regularSeasonStartDate;
							time = advance.getAdvanceTime(time, 1);
						}
						HashMap<Team, Team> schedule = new HashMap<>();
						schedule.put(t1, t2);
						regularSchedule.put(date + "T" + time, schedule);
						simulatedHashmap.put(date + time + t1 + t2, 0);
						message = t1.getTeamName() + "," + t2.getTeamName() + "," + date + " " + time;
						console.print(message);
						divLimitReached++;
						totalGameCount.put(t1, totalGameCount.get(t1) + 1);
						totalGameCount.put(t2, totalGameCount.get(t2) + 1);
						scheduledDivisionMatchCount.put(t1, scheduledDivisionMatchCount.get(t1) + 1);
						scheduledDivisionMatchCount.put(t2, scheduledDivisionMatchCount.get(t2) + 1);
					}
				}
			}
			loop = 0;
			while ((loop < totalDivTeams)) {
				loop++;
				for (int k = 0; k < totalTeams; k++) {
					t2 = teamName.get(k);
					div2 = teamDivision.get(t2);
					team2DivCount = scheduledDivisionMatchCount.get(t2);
					team2TotalCount = totalGameCount.get(t2);
					if (divLimitReached == divLimit) {
						break;
					}
					if ((div1.getDivisionName() == div2.getDivisionName())
							&& (((t1.getTeamName().compareTo(t2.getTeamName())) > 0)
									|| ((t1.getTeamName().compareTo(t2.getTeamName())) < 0))) {

						if ((team2TotalCount < 82) && (team2DivCount < divLimit)) {
							date = advance.getAdvanceDate(date, 1);
							dateTime = parse.stringToDate(date);
							if (dateTime.compareTo(endDateTime) > 0) {
								date = regularSeasonStartDate;
								time = advance.getAdvanceTime(time, 1);
							}
							HashMap<Team, Team> schedule = new HashMap<>();
							schedule.put(t1, t2);
							regularSchedule.put(date + "T" + time, schedule);
							simulatedHashmap.put(date + time + t1 + t2, 0);
							message = t1.getTeamName() + "," + t2.getTeamName() + "," + date + " " + time;
							console.print(message);
							divLimitReached++;
							totalGameCount.put(t1, totalGameCount.get(t1) + 1);
							totalGameCount.put(t2, totalGameCount.get(t2) + 1);
							scheduledDivisionMatchCount.put(t1, scheduledDivisionMatchCount.get(t1) + 1);
							scheduledDivisionMatchCount.put(t2, scheduledDivisionMatchCount.get(t2) + 1);
						}
					}
				}
			}
			for (int j = i + 1; j < totalTeams; j++) {
				t2 = teamName.get(j);
				div2 = teamDivision.get(t2);
				con2 = teamConference.get(t2);
				team2InConCount = scheduledInConferenceMatchCount.get(t2);
				team2TotalCount = totalGameCount.get(t2);
				if (inConLimitReached == inConLimit) {
					break;
				}
				if (((div1.getDivisionName().compareTo(div2.getDivisionName()) < 0)
						|| (div1.getDivisionName().compareTo(div2.getDivisionName()) > 0))
						&& (con2.getConferenceName() == con1.getConferenceName())) {
					if ((team2InConCount < inConLimit)) {
						date = advance.getAdvanceDate(date, 1);
						dateTime = parse.stringToDate(date);
						if (dateTime.compareTo(endDateTime) > 0) {
							date = regularSeasonStartDate;
							time = advance.getAdvanceTime(time, 1);
						}
						HashMap<Team, Team> schedule = new HashMap<>();
						schedule.put(t1, t2);
						regularSchedule.put(date + "T" + time, schedule);
						simulatedHashmap.put(date + time + t1 + t2, 0);
						message = t1.getTeamName() + "," + t2.getTeamName() + "," + date + " " + time;
						console.print(message);
						inConLimitReached++;
						totalGameCount.put(t1, totalGameCount.get(t1) + 1);
						totalGameCount.put(t2, totalGameCount.get(t2) + 1);
						scheduledInConferenceMatchCount.put(t1, scheduledInConferenceMatchCount.get(t1) + 1);
						scheduledInConferenceMatchCount.put(t2, scheduledInConferenceMatchCount.get(t2) + 1);
					}
				}
			}
			loop = 0;
			while ((loop < totalInConTeams)) {
				loop++;
				for (int k = 0; k < totalTeams; k++) {
					t2 = teamName.get(k);
					div2 = teamDivision.get(t2);
					con2 = teamConference.get(t2);
					team2InConCount = scheduledInConferenceMatchCount.get(t2);
					team2TotalCount = totalGameCount.get(t2);
					if (inConLimitReached == inConLimit) {
						break;
					}
					if (((div1.getDivisionName().compareTo(div2.getDivisionName()) < 0)
							|| (div1.getDivisionName().compareTo(div2.getDivisionName()) > 0))
							&& (con2.getConferenceName() == con1.getConferenceName())) {
						if ((team2TotalCount < 82) && (team2InConCount < inConLimit)) {
							date = advance.getAdvanceDate(date, 1);
							dateTime = parse.stringToDate(date);
							if (dateTime.compareTo(endDateTime) > 0) {
								date = regularSeasonStartDate;
								time = advance.getAdvanceTime(time, 1);
							}
							HashMap<Team, Team> schedule = new HashMap<>();
							schedule.put(t1, t2);
							regularSchedule.put(date + "T" + time, schedule);
							simulatedHashmap.put(date + time + t1 + t2, 0);
							message = t1.getTeamName() + "," + t2.getTeamName() + "," + date + " " + time;
							console.print(message);
							inConLimitReached++;
							totalGameCount.put(t1, totalGameCount.get(t1) + 1);
							totalGameCount.put(t2, totalGameCount.get(t2) + 1);
							scheduledInConferenceMatchCount.put(t1, scheduledInConferenceMatchCount.get(t1) + 1);
							scheduledInConferenceMatchCount.put(t2, scheduledInConferenceMatchCount.get(t2) + 1);
						}
					}	
				}
			}
			outConLimit = 82 - inConLimitReached - divLimitReached;
			for (int j = i + 1; j < totalTeams; j++) {
				t2 = teamName.get(j);
				div2 = teamDivision.get(t2);
				con2 = teamConference.get(t2);
				team2OutConCount = scheduledOutConferenceMatchCount.get(t2);
				team2TotalCount = totalGameCount.get(t2);
				if (outConLimitReached == outConLimit) {
					break;
				}
				if (((con2.getConferenceName().compareTo(con1.getConferenceName())) > 0)
						|| ((con2.getConferenceName().compareTo(con1.getConferenceName())) < 0)) {
					if ((team2TotalCount < 82) && (team2OutConCount < 26)) {
						date = advance.getAdvanceDate(date, 1);
						dateTime = parse.stringToDate(date);
						if (dateTime.compareTo(endDateTime) > 0) {
							date = regularSeasonStartDate;
							time = advance.getAdvanceTime(time, 1);
						}
						HashMap<Team, Team> schedule = new HashMap<>();
						schedule.put(t1, t2);
						regularSchedule.put(date + "T" + time, schedule);
						simulatedHashmap.put(date + time + t1 + t2, 0);
						message = t1.getTeamName() + "," + t2.getTeamName() + "," + date + " " + time;
						console.print(message);
						outConLimitReached++;
						totalGameCount.put(t1, totalGameCount.get(t1) + 1);
						totalGameCount.put(t2, totalGameCount.get(t2) + 1);
						scheduledOutConferenceMatchCount.put(t1, scheduledOutConferenceMatchCount.get(t1) + 1);
						scheduledOutConferenceMatchCount.put(t2, scheduledOutConferenceMatchCount.get(t2) + 1);
					}
				}
			}
			loop = 0;
			totalOutConTeams = totalTeams - totalDivTeams - totalInConTeams;
			while ((loop < totalOutConTeams)) {
				loop++;
				for (int k = 0; k < totalTeams; k++) {
					t2 = teamName.get(k);
					div2 = teamDivision.get(t2);
					con2 = teamConference.get(t2);
					team2OutConCount = scheduledOutConferenceMatchCount.get(t2);
					team2TotalCount = totalGameCount.get(t2);
					if (outConLimitReached == outConLimit) {
						break;
					}
					if (((con2.getConferenceName().compareTo(con1.getConferenceName())) > 0)
							|| ((con2.getConferenceName().compareTo(con1.getConferenceName())) < 0)) {
						if ((team2TotalCount < 82) && (team2OutConCount < 26)) {
							date = advance.getAdvanceDate(date, 1);
							dateTime = parse.stringToDate(date);
							if (dateTime.compareTo(endDateTime) > 0) {
								date = regularSeasonStartDate;
								time = advance.getAdvanceTime(time, 1);
							}
							HashMap<Team, Team> schedule = new HashMap<>();
							schedule.put(t1, t2);
							regularSchedule.put(date + "T" + time, schedule);
							simulatedHashmap.put(date + time + t1 + t2, 0);
							message = t1.getTeamName() + "," + t2.getTeamName() + "," + date + " " + time;
							console.print(message);
							outConLimitReached++;
							totalGameCount.put(t1, totalGameCount.get(t1) + 1);
							totalGameCount.put(t2, totalGameCount.get(t2) + 1);
							scheduledOutConferenceMatchCount.put(t1, scheduledOutConferenceMatchCount.get(t1) + 1);
							scheduledOutConferenceMatchCount.put(t2, scheduledOutConferenceMatchCount.get(t2) + 1);
						}
					}
				}
			}
		}
		for (Conference conference : cconferenceList) {
			conTeamCount.put(conference, 0);
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				divTeamCount.put(division, 0);
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					totalGames += (scheduledDivisionMatchCount.get(team)) + (scheduledInConferenceMatchCount.get(team))
							+ (scheduledOutConferenceMatchCount.get(team));
				}
			}
		}
		message = "\n********** Total games scheduled : " + totalGames + "(" + (totalGames / totalTeams)
				+ " games each team) **********";
		console.print(message);
		return regularSchedule;
	}

}
