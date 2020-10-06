package group11.Hockey;

import java.net.URL;

import group11.Hockey.models.League;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		// C:\\Users\\RajKumar\\Documents\\MACS_Fall\\A_SDC\\HockeyTeamDummy.json
		// C:\\Users\\RajKumar\\Documents\\MACS_Fall\\A_SDC\\HockeyTeamJsonSchema.json
		League leagueObj = null;
		IUserInputMode userInputMode = new CommandLineInput();
		if (args.length != 0) {
			String jsonFile = args[0];
			//String jsonSchemaFile = "C:\\Users\\RajKumar\\Documents\\MACS_Fall\\A_SDC\\HockeyTeamJsonSchema.json";
			// String jsonSchemaFile = args[1];

			ValidateJson validate = new ValidateJson();
			URL jsonSchemaFile = App.class.getResource("HockeyTeamJsonSchema.json");
			boolean isValid = validate.validateJson(jsonFile, jsonSchemaFile.getPath());
			System.out.println("valide json:->:" + isValid);
			if (isValid) {
				ImportJson importJson = new ImportJson();

				try {
					leagueObj = importJson.parseFile(jsonFile);
					

					CreateTeam createTeamObj = new CreateTeam(userInputMode, leagueObj);
					leagueObj = createTeamObj.getTeam();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Exception:-->");
					System.out.println(e.getMessage());
				}
			}

		} else {
			LoadTeam loadTeam = new LoadTeam(userInputMode);

			try {
				leagueObj = loadTeam.getTeam();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		PlayerChoice playerChoice = new PlayerChoice(userInputMode);
		int noOfSeasons = playerChoice.getNumberOfSeasonsToSimulate();
		System.out.println(noOfSeasons);
		

	}
}
