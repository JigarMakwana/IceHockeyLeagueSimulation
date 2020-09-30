package group11.Hockey;

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
		if (args.length != 0) {
			String jsonFile = args[0];
			String jsonSchemaFile = args[1];

			ValidateJson validate = new ValidateJson();
			boolean isValid = validate.validateJson(jsonFile, jsonSchemaFile);
			System.out.println("valide json:->:"+isValid);

			ImportJson importJson = new ImportJson();
			League leagueObj = null;
			try {
				leagueObj = importJson.parseFile(jsonFile);
				IUserInputMode userInputMode = new CommandLineInput();

				CreateTeam createTeamObj = new CreateTeam(userInputMode, leagueObj);
				leagueObj = createTeamObj.getTeam();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Exception:-->");
				System.out.println(e.getMessage());
			}

			// PersistJson persistJson = new PersistJson(new ImportJson());
			// persistJson.parseFile(jsonFile);

		} else {
			System.out.println("Load team");
		}
	}
}
