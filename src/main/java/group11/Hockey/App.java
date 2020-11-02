package group11.Hockey;

import group11.Hockey.BusinessLogic.CreateTeam;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.LoadLeague;
import group11.Hockey.BusinessLogic.Validations;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.CommandLineInput;
import group11.Hockey.InputOutput.Display;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.InputOutput.JsonParsing.JsonImport;
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
import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.db.Team.TeamDbImpl;
import group11.Hockey.models.InitializeSeason;

public class App {
	public static void main(String[] args) {
		System.out.println("Welcome to Hockey Simulation!");
		League leagueObj = null;
		ILeagueDb leagueDb = new LeagueDbImpl();
		IGameplayConfigDb gameplayConfigDb = new GameplayConfigDb();
		IPlayerDb playerDb = new PlayerDb();
		ICoachDb coachDb = new CoachDb();
		IManagerDb managerDb = new ManagerDb();
		IDisplay display = new Display();
		IValidations validation = new Validations(display);
		ICommandLineInput commandLineInput = new CommandLineInput();
		IConference conference = new Conference();
		IDivision division = new Division();
		App app = new App();
		if (args.length > 0) {
			String jsonFile = args[0];
			JsonImport importJson = new JsonImport(leagueDb);
			try {
				leagueObj = importJson.parseFile(jsonFile);
				CreateTeam createTeamObj = new CreateTeam(leagueObj, commandLineInput, display, validation, conference,
						division);
				createTeamObj.createTeamMethod();
				app.startSimulation(leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb, display, validation,
						commandLineInput, leagueObj);

				System.out.println("****Create Team end****");
			} catch (Exception e) {
				System.out.print("Exception:--> " + e.getMessage());
				System.exit(0);
			}

		} else {
			ITeamDb teamDb = new TeamDbImpl();
			LoadLeague loadTeam = new LoadLeague(commandLineInput, teamDb);
			try {
				League league = loadTeam.loadLeagueWithTeamName();

				app.startSimulation(leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb, display, validation,
						commandLineInput, league);

				System.out.println("****Load Team end****");
			} catch (Exception e) {
				System.out.print("Exception:-->");
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}
	}

	private void startSimulation(ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb, IPlayerDb playerDb,
			ICoachDb coachDb, IManagerDb managerDb, IDisplay display, IValidations validation,
			ICommandLineInput commandLineInput, League league) {
		boolean seasonsCheck = true;
		String numberOfSeasons = null;
		while (seasonsCheck) {
			display.showMessageOnConsole("Enter number of seasons to simulate:");
			numberOfSeasons = commandLineInput.getValueFromUser();
			seasonsCheck = validation.isNoOfSeasonsValueValid(numberOfSeasons);
		}
		int seasons = Integer.parseInt(numberOfSeasons);
		InitializeSeason initialize = new InitializeSeason(league, leagueDb, gameplayConfigDb, playerDb, coachDb,
				managerDb);

		initialize.startSeasons(seasons);
	}
}
