package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.Display;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

public class UserInputCheck implements IUserInputCheck {
	private ICommandLineInput commandLineInput;
	IValidations validation = new Validations();
	IDisplay display = new Display();

	public UserInputCheck(ICommandLineInput commandLineInput) {
		this.commandLineInput = commandLineInput;
	}

	@Override
	public String conferenceNameFromUserCheck(List<Conference> conferencesList) {
		boolean checkConferenceName = true;
		String conferenceName = null;
		while (checkConferenceName) {
			display.showMessageOnConsole("Enter Conference Name: ");
			conferenceName = commandLineInput.getValueFromUser();
			checkConferenceName = validation.isConferenceNameValid(conferenceName, conferencesList);
		}
		return conferenceName;
	}

	@Override
	public String divisonNameFromUserCheck(Conference conference) {
		boolean checkDiviosneName = true;
		String divisionName = null;
		while (checkDiviosneName) {
			display.showMessageOnConsole("Enter Division Name: ");
			divisionName = commandLineInput.getValueFromUser();
			checkDiviosneName = validation.isDivisionValid(divisionName, conference);
		}
		return divisionName;

	}

	@Override
	public void teamNameFromUserCheck(Team newTeam, League league) {
		boolean checkTeamName = true;
		String teamName = null;
		while (checkTeamName) {
			display.showMessageOnConsole("Enter Team Name:");
			teamName = commandLineInput.getValueFromUser();
			checkTeamName = validation.isTeamNameValid(teamName, league);
		}
		if (teamName != null) {
			newTeam.setTeamName(teamName);
			newTeam.setUserTeam(true);
		}

	}

	@Override
	public void generalManagerNameFromUserCheck(Team newTeam, League league) {
		boolean checkManagerName = true;
		String generalManager = null;
		while (checkManagerName) {
			display.showMessageOnConsole("Enter generalManager Name: ");
			generalManager = commandLineInput.getValueFromUser();
			checkManagerName = validation.generalManagerNameCheck(generalManager, league);
		}
		if (generalManager != null) {
			newTeam.addGeneralMangerToTeam(newTeam, generalManager, league);
		}

	}

	@Override
	public void headCoachNameFromUserCheck(Team newTeam, League league) {
		boolean checkHeadCoachName = true;
		String headCoach = null;
		while (checkHeadCoachName) {
			display.showMessageOnConsole("Enter headCoach Name: ");
			headCoach = commandLineInput.getValueFromUser();
			checkHeadCoachName = validation.headCoachNameCheck(headCoach, league);
		}
		if (headCoach != null) {
			newTeam.addCoachToTeam(newTeam, headCoach, league);
		}

	}

	@Override
	public void playerChoiceFromUser(Team newTeam, League league) {
		boolean playerValueCheck = true;
		List<Integer> selectedValuesFromUser = new ArrayList<Integer>();
		List<Player> skatersList = new ArrayList<Player>();
		List<Player> goalies = new ArrayList<Player>();
		Player player = new Player();
		String playerValue;
		display.showMessageOnConsole("Select 20 players for team: 18 Skaters and 2 Goalies");
		for (int i = 0; i < 20; i++) {
			while (playerValueCheck) {
				display.showMessageOnConsole("Select " + (i + 1) + " player");
				playerValue = commandLineInput.getValueFromUser();
				playerValueCheck = validation.playerCheck(playerValue, league, selectedValuesFromUser, skatersList,
						goalies);
				if (playerValueCheck == false) {
					String postion = league.getFreeAgents().get(Integer.parseInt(playerValue) - 1).getPosition();
					if (postion.equalsIgnoreCase(PositionEnum.FORWARD.toString())
							|| postion.equalsIgnoreCase(PositionEnum.DEFENSE.toString())) {
						skatersList.add(league.getFreeAgents().get(Integer.parseInt(playerValue) - 1));
					} else if (postion.equalsIgnoreCase(PositionEnum.GOALIE.toString())) {
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
		player.removeFreeAgentsFromLeague(league, finalListOfPlayers);

	}

}
