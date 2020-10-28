package group11.Hockey;

import java.util.List;

import group11.Hockey.models.Coach;
import group11.Hockey.models.GeneralManager;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;

public class Display implements IDisplay {

	@Override
	public void showMessageOnConsole(String message) {
		System.out.println(message);
	}

	@Override
	public void displayListOfGeneralMangers(League league) {
		int count = 1;
		System.out.println("Available General Manger List: ");
		List<GeneralManager> generalManagers = league.getGeneralManagers();
		for (GeneralManager generalManger : generalManagers) {
			System.out.println(count + ") General Manager Name: " + generalManger.getName());
			count++;
		}

	}

	@Override
	public void displayListOfCoaches(League league) {
		int count = 1;
		List<Coach> coaches = league.getCoaches();
		System.out.println("Available Coaches List: ");
		for (Coach coach : coaches) {
			System.out.println("Press " + count + "for coach" + coach.getName());
			System.out.println("******Coach Skill Details******");
			System.out.println("Skating :" + coach.getSkating());
			System.out.println("Shooting :" + coach.getShooting());
			System.out.println("Checking :" + coach.getChecking());
			System.out.println("Saving :" + coach.getSaving());
			count++;
		}
	}

	@Override
	public void displayListOfPLayers(League league) {
		int count = 1;
		List<Player> freeAgents = league.getFreeAgents();
		System.out.println("******Select Players for Team******: ");
		System.out.println("******List of free agents******: ");
		for (Player freeAgent : freeAgents) {
			System.out.println("Press " + count + "for free agent: " + freeAgent.getPlayerName());
			System.out.println("******Free agent Skill Details******");
			System.out.println("Position :" + freeAgent.getPosition());
			System.out.println("Age :" + freeAgent.getAge());
			System.out.println("Skating :" + freeAgent.getSkating());
			System.out.println("Shooting :" + freeAgent.getShooting());
			System.out.println("Checking :" + freeAgent.getChecking());
			System.out.println("Saving :" + freeAgent.getSaving());
		}

	}

}
