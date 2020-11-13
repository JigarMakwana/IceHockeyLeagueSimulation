package group11.Hockey.BusinessLogic;

import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;

public class PlayoffScheduleFinalRounds implements IScheduleStrategy {
	private ILeague league;

	public PlayoffScheduleFinalRounds(ILeague league) {
		super();
		this.league = league;
	}

	public HashMap<String, HashMap<Team, Team>> getSchedule() {
		ITimeLine timeLine = league.getTimeLine();
		String date = timeLine.getCurrentDate();
		IAdvance advance = new Advance();
		HashMap<String, HashMap<Team, Team>> playoffSchedule = new HashMap<>();
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		IPrintToConsole console = new PrintToConsole();
		Team team1, team2;
		String message;

		String time = "00:00:00";
		int teamNumber1 = 0;
		int teamNumber2 = 1;
		int teams = 0, series = 0, totalSetTeams, qualifiedTeamsSize;
		qualifiedTeamsSize = qualifiedTeams.size();
		totalSetTeams = (qualifiedTeamsSize / 2);
		if (totalSetTeams == 4) {
			message = "\n********** Playoff Schedule - Second round **********";
			console.print(message);
		} else if (totalSetTeams == 2) {
			message = "\n********** Playoff Schedule - Semi-Final round **********";
			console.print(message);
		} else if (totalSetTeams == 1) {
			message = "\n********** Playoff Schedule - Final round **********";
			console.print(message);
		}
		// final sets of teams(team1,team2)
		while (teams < totalSetTeams) {
			teams++;
			series = 0;
			while (series < 7) {
				series++;
				team1 = qualifiedTeams.get(teamNumber1);
				team2 = qualifiedTeams.get(teamNumber2);

				team1.setWins(0);
				team2.setWins(0);

				HashMap<Team, Team> schedule = new HashMap<>();
				schedule.put(team1, team2);
				playoffSchedule.put(date + "T" + time, schedule);
				message = "Scheduled b/w " + team1.getTeamName() + " & " + team2.getTeamName() + " on " + date + " at "
						+ time;
				console.print(message);
				try {
					time = advance.getAdvanceTime(time, 6);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (time.equals("18:00:00")) {
					try {
						date = advance.getAdvanceDate(date, 1);
						time = "00:00:00";
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			teamNumber1 = teamNumber1 + 2;
			teamNumber2 = teamNumber2 + 2;
			if (teamNumber2 >= qualifiedTeamsSize) {
				break;
			}
		}
		return playoffSchedule;
	}
}
