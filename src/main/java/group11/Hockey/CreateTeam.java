package group11.Hockey;

import java.util.List;

import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

public class CreateTeam extends ValidateCreateTeam {

	private String conferenceName;
	private String divisionName;
	private IUserInputMode userInputMode;
	private IGameplayConfigDb gameplayConfigDb;
	private IPlayerDb playerDb;
	private ICoachDb coachDb;
	private IManagerDb managerDb;

	public CreateTeam(IUserInputMode userInputMode, League leagueObj, ILeagueDb leagueDb,
			IGameplayConfigDb gameplayConfigDb, IPlayerDb playerDb, ICoachDb coachDb, IManagerDb managerDb) {
		super(leagueObj, userInputMode, leagueDb);
		this.userInputMode = userInputMode;
		this.gameplayConfigDb = gameplayConfigDb;
		this.playerDb = playerDb;
		this.coachDb = coachDb;
	}

	public League getTeam() {

		userInputMode.displayMessage("***Create Team***\n");
		List<Conference> conferencesList = leagueObj.getConferences();
		// Conference name
		while (isNotValidConference(conferenceName, conferencesList)) {
			userInputMode.displayMessage("Enter Conference Name: ");
			conferenceName = userInputMode.getName();
		}
		Conference conferenceItem = conferenceObj.getConferencefromConferenceName(conferenceName, conferencesList);
		// Division Name
		while (isNotValidDivision(divisionName, conferenceItem)) {
			userInputMode.displayMessage("Enter Division Name: ");
			divisionName = userInputMode.getName();
		}
		Division divisionItem = divisionObj.getDivisionFromDivisionName(divisionName, conferenceItem.getDivisions());

		boolean isNotValidNumber = true;
		while (isNotValidNumber) {
			int numberOfTeams = 1;
			try {
				userInputMode.displayMessage("Enter Number of Teams to create: ");
				numberOfTeams = userInputMode.getInt();
				isNotValidNumber = false;
				for (int i = 0; i < numberOfTeams; i++) {
					// Create Team
					userInputMode.displayMessage("Team " + (i + 1) + "/" + numberOfTeams);
					createTeam(divisionItem);
				}
			} catch (Exception e) {
				userInputMode.displayMessage("not a valid number");
			}
		}

		leagueObj.insertLeagueObject(leagueObj, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);

		return leagueObj;

	}

	private void createTeam(Division division) {
		String teamName = null;
		String generalManager = null;
		String headCoach = null;
		Team newTeam = new Team();
		while (isNotValidTeamName(teamName, newTeam)) {
			userInputMode.displayMessage("Enter Team Name: ");
			teamName = userInputMode.getName();
			newTeam.setTeamName(teamName);
		}

		while (isNotValidGeneralManager(generalManager, newTeam)) {
			userInputMode.displayMessage("Enter generalManager Name: ");
			generalManager = userInputMode.getName();
			newTeam.setGeneralManager(generalManager);
		}

		while (isNotValidHeadCoach(headCoach, newTeam)) {
			userInputMode.displayMessage("Enter headCoach Name: ");
			headCoach = userInputMode.getName();
			// newTeam.setHeadCoach(headCoach);
		}

		division.addNewTeamInDivision(newTeam);

	}

}
