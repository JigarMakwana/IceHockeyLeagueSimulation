package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.db.League.ILeagueDb;

public class Trophy extends StateMachineState {
	ILeague league;
	ILeagueDb leagueDb;
	IDisplay display;
	private static Logger logger = LogManager.getLogger(Trophy.class);

	public Trophy(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		this.league = league;
		this.leagueDb = leagueDb;
		this.display = display;
	}

	@Override
	public StateMachineState startState() {
		logger.info("Entered startState()");
		IParse parse = DefaultHockeyFactory.makeParse();
		IPrintToConsole console=DefaultHockeyFactory.makePrintToConsole();
		ITimeLine timeLine = league.getTimeLine();
		String startDate = timeLine.getStartDate();
		int startYear = parse.stringToYear(startDate);
		String stanleyDate = timeLine.getStanleyDate();
		int endYear = parse.stringToYear(stanleyDate);

		List<ITeam> qualifiedTeams = league.getQualifiedTeams();
		List<ITeam> presidentTeams = league.getPresidentTeams();
		List<IPlayer> calderPlayers = league.getCalderPlayers();
		List<IPlayer> venizaPlayers = league.getVenizaPlayers();
		List<ICoach> jackAdamsCoaches = league.getJackAdamsCoaches();
		List<IPlayer> mauricePlayers = league.getMauriceRichardPlayers();
		List<IPlayer> robHawkeyPlayers = league.getRobHawkeyPlayers();
		List<ITeam> participationTeams = league.getParticipationTeams();
		
		ITeam winner = qualifiedTeams.get(0);
		qualifiedTeams.remove(winner);
		String message = "\n********** Winner team of the season(" + startYear + "/" + endYear + ") is "
				+ winner.getTeamName() + " **********";
		console.print(message);
		DefaultHockeyFactory.makeEndOfStanleySubject(league);
		
		int seasons=presidentTeams.size();
		for(int i=(seasons-1);i>=0;i--) {
		console.print(message);
			console.print("\nWinners of the season(" + startYear-- + "/" + endYear-- + ")");
			console.print("President trophy winner is "+presidentTeams.get(i).getTeamName());
			console.print("Calder Memorial trophy winner is "+calderPlayers.get(i).getPlayerName());
			console.print("Veniza trophy winner is "+venizaPlayers.get(i).getPlayerName());
			console.print("Jack Adam's trophy winner is "+jackAdamsCoaches.get(i).getName());
			console.print("Maurice Richard trophy winner is "+mauricePlayers.get(i).getPlayerName());
			console.print("Rob Hawkey Memorial trophy winner is "+robHawkeyPlayers.get(i).getPlayerName());
			console.print("Participation trophy winner is "+participationTeams.get(i).getTeamName());
		}
		return DefaultHockeyFactory.makeAdvanceToNextSeason(league, leagueDb, display);
	}

}
