package group11.Hockey;

import java.net.URL;

import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.League.LeagueDbImpl;
import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.db.Team.TeamDbImpl;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Welcome to Hockey Simulation!");
		League leagueObj = null;
		IUserInputMode userInputMode = new CommandLineInput();
		if (args.length != 0) {
			// if (false) {
			String jsonFile = args[0];

			ValidateJson validate = new ValidateJson();
			URL jsonSchemaFile = App.class.getClassLoader().getResource("HockeyTeamJsonSchema.json");
			boolean isValid = validate.validateJson(jsonFile, jsonSchemaFile.getPath());
			System.out.println("valide json:->:" + isValid);
			if (isValid) {
				ImportJson importJson = new ImportJson();

				try {
					leagueObj = importJson.parseFile(jsonFile);
					ILeagueDb leagueDb = new LeagueDbImpl();
					CreateTeam createTeamObj = new CreateTeam(userInputMode, leagueObj, leagueDb);
					leagueObj = createTeamObj.getTeam();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Exception:-->");
					System.out.println(e.getMessage());
					System.exit(0);
				}
			}
			else {
				System.exit(0);
			}

		} else {
			ITeamDb teamDb = new TeamDbImpl();
			LoadTeam loadTeam = new LoadTeam(userInputMode, teamDb);

			try {
				Team team = loadTeam.getTeam();
				System.out.println("****Load Team end****");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}

		}

		PlayerChoice playerChoice = new PlayerChoice(userInputMode);
		int noOfSeasons = playerChoice.getNumberOfSeasonsToSimulate();
		System.out.println(noOfSeasons);

	}
}
