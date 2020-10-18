package group11.Hockey;

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
import group11.Hockey.models.League;
import group11.Hockey.models.Player;
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

			ILeagueDb leagueDb = new LeagueDbImpl();
			IGameplayConfigDb gameplayConfigDb = new GameplayConfigDb();
			IPlayerDb playerDb = new PlayerDb();
			ICoachDb coachDb = new CoachDb();
			IManagerDb managerDb = new ManagerDb();

			ImportJson importJson = new ImportJson(leagueDb);

			try {
				leagueObj = importJson.parseFile(jsonFile);

				//leagueObj.insertLeagueObject(leagueObj, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
				//Player p = new Player();
				//p.increaseAge(leagueObj, 300);
				CreateTeam createTeamObj = new CreateTeam(userInputMode, leagueObj, leagueDb, gameplayConfigDb,
						playerDb, coachDb, managerDb);
				leagueObj = createTeamObj.getTeam();
				System.out.println("****Create Team end****");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print("Exception:-->");
				System.out.println(e.getMessage());
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
				// e.printStackTrace();
				System.out.print("Exception:-->");
				System.out.println(e.getMessage());
				System.exit(0);
			}

		}

		PlayerChoice playerChoice = new PlayerChoice(userInputMode);
		int noOfSeasons = playerChoice.getNumberOfSeasonsToSimulate();
		System.out.println("Number of seasons to simulate -> " + noOfSeasons);

	}
}
