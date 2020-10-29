package group11.Hockey.BusinessLogic;


import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.CommandLineInput;
import group11.Hockey.InputOutput.Display;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.League.ILeagueDb;

public class CreateTeam extends CommonUtilForLeague implements ICreateTeam {

	private String conferenceName;
	private String divisionName;
	private ICommandLineInput userInputMode = new CommandLineInput();
	League league;
	IDisplay display = new Display();
	IValidations validation = new Validations();
	Conference conference = new Conference();
	Division division = new Division();

	public CreateTeam(League leagueObj, ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb, IPlayerDb playerDb,
			ICoachDb coachDb, IManagerDb managerDb) {
		this.league = leagueObj;
	}

	public CreateTeam(League league, Conference conference, Division division) {
		this.league = league;
		this.conference = conference;
		this.division = division;
	}

	@Override
	public void createTeamMethod() {
		System.out.println("***Create Team***\\n");
		List<Conference> conferencesList = league.getConferences();
		Team newTeam = new Team();
		conferenceNameFromUserCheck(conferencesList);
		Conference conferenceItem = conference.getConferencefromConferenceName(conferenceName, conferencesList);
		divisonNameFromUserCheck(conferenceItem);
		Division divisionItem = division.getDivisionFromDivisionName(divisionName, conferenceItem.getDivisions());
		teamNameFromUserCheck(newTeam, league);
		display.displayListOfGeneralMangers(league);
		generalManagerNameFromUserCheck(newTeam, league);
		display.displayListOfCoaches(league);
		headCoachNameFromUserCheck(newTeam, league);
		display.displayListOfPLayers(league);
		playerChoiceFromUser(newTeam, league);
		divisionItem.addNewTeamInDivision(newTeam);
//		PlayerChoice playerChoice = new PlayerChoice(userInputMode);
//		int noOfSeasons = playerChoice.getNumberOfSeasonsToSimulate();
//		System.out.println("Number of seasons to simulate -> " + noOfSeasons);
	}

	public void conferenceNameFromUserCheck(List<Conference> conferencesList) {
		boolean checkConferenceName = true;
		while (checkConferenceName) {
			display.showMessageOnConsole("Enter Conference Name: ");
			conferenceName = userInputMode.getValueFromUser();
			checkConferenceName = validation.isConferenceNameValid(conferenceName, conferencesList);
		}
	}

	public void divisonNameFromUserCheck(Conference conference) {
		boolean checkDiviosneName = true;
		while (checkDiviosneName) {
			display.showMessageOnConsole("Enter Division Name: ");
			divisionName = userInputMode.getValueFromUser();
			checkDiviosneName = validation.isDivisionValid(divisionName, conference);
		}
	}

	public void teamNameFromUserCheck(Team newTeam, League league) {
		boolean checkTeamName = true;
		String teamName = null;
		while (checkTeamName) {
			display.showMessageOnConsole("Enter Team Name:");
			teamName = userInputMode.getValueFromUser();
			checkTeamName = validation.isTeamNameValid(teamName, league);
		}
		if (teamName != null) {
			newTeam.setTeamName(teamName);
			newTeam.setUserTeam(true);
		}
	}

	public void generalManagerNameFromUserCheck(Team newTeam, League league) {
		boolean checkManagerName = true;
		String generalManager = null;
		while (checkManagerName) {
			display.showMessageOnConsole("Enter generalManager Name: ");
			generalManager = userInputMode.getValueFromUser();
			checkManagerName = validation.generalManagerNameCheck(generalManager, league);
		}
		if (generalManager != null) {
			addGeneralMangerToTeam(newTeam, generalManager, league);
		}
	}

	public void headCoachNameFromUserCheck(Team newTeam, League league) {
		boolean checkHeadCoachName = true;
		String headCoach = null;
		while (checkHeadCoachName) {
			display.showMessageOnConsole("Enter headCoach Name: ");
			headCoach = userInputMode.getValueFromUser();
			checkHeadCoachName = validation.headCoachNameCheck(headCoach, league);
		}
		if (headCoach != null) {
			addCoachToTeam(newTeam, headCoach, league);
		}

	}

	public void playerChoiceFromUser(Team newTeam, League league) {
		boolean playerValueCheck = true;
		List<Integer> selectedValuesFromUser = new ArrayList<Integer>();
		List<Player> skatersList = new ArrayList<Player>();
		List<Player> goalies = new ArrayList<Player>();
		String playerValue;
		display.showMessageOnConsole("Select 20 players for team: 18 Skaters and 2 Goalies");
		for (int i = 0; i < 3; i++) {
			while (playerValueCheck) {
				display.showMessageOnConsole("Select " + (i+1) + " player");
				playerValue = userInputMode.getValueFromUser();
				playerValueCheck = validation.playerCheck(Integer.parseInt(playerValue), league, selectedValuesFromUser,
						skatersList, goalies);
				if (playerValueCheck == false) {
					String postion = league.getFreeAgents().get(Integer.parseInt(playerValue) - 1).getPosition();
					if (postion.equalsIgnoreCase("forward") || postion.equalsIgnoreCase("defense")) {
						skatersList.add(league.getFreeAgents().get(Integer.parseInt(playerValue) - 1));
					} else if (postion.equalsIgnoreCase("goalie")) {
						goalies.add(league.getFreeAgents().get(Integer.parseInt(playerValue) - 1));
					}
				}
			}
			playerValueCheck = true;
		}

		List<Player> finalListOfPlayers = new ArrayList<Player>();
		finalListOfPlayers.addAll(skatersList);
		finalListOfPlayers.addAll(goalies);
		newTeam.setPlayers(finalListOfPlayers);
		removeFreeAgentsFromLeague(league, finalListOfPlayers);

	}

//	public League getTeam() {
//		ICommandLineInput userInputMode = new CommandLineInput();
//		userInputMode.displayMessage("***Create Team***\n");
//		List<Conference> conferencesList = leagueObj.getConferences();
//		// Conference name
//		while (isNotValidConference(conferenceName, conferencesList)) {
//			userInputMode.displayMessage("Enter Conference Name: ");
//			conferenceName = userInputMode.getName();
//		}
//		Conference conferenceItem = conferenceObj.getConferencefromConferenceName(conferenceName, conferencesList);
//		// Division Name
//		while (isNotValidDivision(divisionName, conferenceItem)) {
//			userInputMode.displayMessage("Enter Division Name: ");
//			divisionName = userInputMode.getName();
//		}
//		Division divisionItem = divisionObj.getDivisionFromDivisionName(divisionName, conferenceItem.getDivisions());
//
//		boolean isNotValidNumber = true;
//		while (isNotValidNumber) {
//			int numberOfTeams = 1;
//			try {
//				userInputMode.displayMessage("Enter Number of Teams to create: ");
//				numberOfTeams = userInputMode.getInt();
//				isNotValidNumber = false;
//				for (int i = 0; i < numberOfTeams; i++) {
//					// Create Team
//					userInputMode.displayMessage("Team " + (i + 1) + "/" + numberOfTeams);
//					createTeam(divisionItem);
//				}
//			} catch (Exception e) {
//				userInputMode.displayMessage("not a valid number");
//			}
//		}
//
//		leagueObj.insertLeagueObject(leagueObj, leagueDb, gameplayConfigDb, playerDb, coachDb, managerDb);
//
//		return leagueObj;
//
//	}
//
//	private void createTeam(Division division) {
//		String teamName = null;
//		String generalManager = null;
//		String headCoach = null;
//		Team newTeam = new Team();
//		while (isNotValidTeamName(teamName, newTeam)) {
//			userInputMode.displayMessage("Enter Team Name: ");
//			teamName = userInputMode.getName();
//			newTeam.setTeamName(teamName);
//		}
//
//		while (isNotValidGeneralManager(generalManager, newTeam)) {
//			userInputMode.displayMessage("Enter generalManager Name: ");
//			generalManager = userInputMode.getName();
//			newTeam.setGeneralManager(generalManager);
//		}
//
//		while (isNotValidHeadCoach(headCoach, newTeam)) {
//			userInputMode.displayMessage("Enter headCoach Name: ");
//			headCoach = userInputMode.getName();
//			// newTeam.setHeadCoach(headCoach);
//		}
//
//		division.addNewTeamInDivision(newTeam);
//
//	}

}
