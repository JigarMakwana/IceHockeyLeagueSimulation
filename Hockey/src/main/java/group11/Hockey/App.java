package group11.Hockey;

import group11.Hockey.models.League;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
     // C:\\Users\\RajKumar\\Documents\\MACS_Fall\\A_SDC\\HockeyTeamDummy.json
     		// C:\\Users\\RajKumar\\Documents\\MACS_Fall\\A_SDC\\HockeyTeamJsonSchema.json
     		if (args.length != 0) {
     			String jsonFile = args[0];
     			String jsonSchemaFile = args[1];

     			ValidateJson validate = new ValidateJson();
     			boolean isValid = validate.validateJson(jsonFile, jsonSchemaFile);
     			System.out.println(isValid);

     			ImportJson importJson = new ImportJson();
     			League leagueObj = importJson.parseFile(jsonFile);

     			// PersistJson persistJson = new PersistJson(new ImportJson());
     			// persistJson.parseFile(jsonFile);

     			IUserInputMode userInputMode = new CommandLineInput();
     			// PlayerChoice playerChoice = new PlayerChoice(userInputMode);
     			// int choice = playerChoice.getCreateOrLoadTeam();

     			CreateTeam createTeamObj = new CreateTeam(userInputMode,leagueObj);
     			leagueObj = createTeamObj.getTeam();
     		} else {
     			System.out.println("Load team");
     		}
    }
}
