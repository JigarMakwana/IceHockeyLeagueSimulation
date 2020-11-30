package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.TeamCreation.CreateTeam;
import group11.Hockey.BusinessLogic.TeamCreation.IRenderTeam;
import group11.Hockey.BusinessLogic.TeamCreation.LoadTeam;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class TeamFactory {

	enum RenderType {
		CREATE, LOAD
	}

	public static IRenderTeam renderTeam(String type, League league, ICommandLineInput commandLineInput,
			IDisplay display, IValidations validation, ILeagueDb leagueDb) {
		if (RenderType.CREATE.toString().equalsIgnoreCase(type)) {
			return new CreateTeam(league, commandLineInput, display, validation, leagueDb);
		} else {
			return new LoadTeam(commandLineInput, display, validation, leagueDb);
		}
	}

}
