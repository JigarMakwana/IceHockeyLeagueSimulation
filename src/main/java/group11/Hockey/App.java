package group11.Hockey;

import group11.Hockey.BusinessLogic.CreateTeam;
import group11.Hockey.BusinessLogic.LoadTeam;
import group11.Hockey.BusinessLogic.models.League;
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
		if (args.length > 0) {
			String jsonFile = args[0];
			JsonImport importJson = new JsonImport(leagueDb);
			try {
				leagueObj = importJson.parseFile(jsonFile);
				//SerializeLeague seralizeLeague = new SerializeLeague();
				//seralizeLeague.serializeLeagueObject(leagueObj);
				AITrading aiToAITradObj = new AITrading(leagueObj);
				aiToAITradObj.generateTradeOffers();

//				CreateTeam createTeamObj = new CreateTeam(leagueObj, leagueDb, gameplayConfigDb,
//						playerDb, coachDb, managerDb);
////				leagueObj = createTeamObj.getTeam();
//				createTeamObj.createTeamMethod();
//				leagueObj.insertLeagueObject(leagueObj, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
//
//				System.out.println("****Create Team end****");
//				InitializeSeason initialize=new InitializeSeason(leagueObj,leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
//
//				try {
//					String advancedDate=initialize.startSeasons(1);
//					System.out.println("Simulation Ended and season advanced to "+advancedDate);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			} catch (Exception e) {
				System.out.print("Exception:--> " + e.getMessage());
				System.exit(0);
			}

		} else {
//			ITeamDb teamDb = new TeamDbImpl();
//			LoadTeam loadTeam = new LoadTeam(userInputMode, teamDb);
//
//			try {
//				leagueObj = importJson.parseFile(jsonFile);
//				//SerializeLeague seralizeLeague = new SerializeLeague();
//				//seralizeLeague.serializeLeagueObject(leagueObj);
//
//				CreateTeam createTeamObj = new CreateTeam(leagueObj, leagueDb, gameplayConfigDb,
//						playerDb, coachDb, managerDb);
////				leagueObj = createTeamObj.getTeam();
//				createTeamObj.createTeamMethod();
//				//leagueObj.insertLeagueObject(leagueObj, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
//				System.out.println("****Create Team end****");
//			} catch (Exception e) {
//				System.out.print("Exception:--> " + e.getMessage());
//				System.exit(0);
//			}
//
//		} else {
//			ITeamDb teamDb = new TeamDbImpl();
//			LoadTeam loadTeam = new LoadTeam(teamDb);
//
//			try {
//				loadTeam.getTeam();
//				System.out.println("****Load Team end****");
//			} catch (Exception e) {
//				System.out.print("Exception:-->");
//				System.out.println(e.getMessage());
//				System.exit(0);
//			}

		//}


	}
}
}
