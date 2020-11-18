package group11.Hockey.InputOutput;

import java.util.Scanner;

public class CommandLineInput implements ICommandLineInput {
	Scanner scanner = null;
	private static CommandLineInput commandLineInputInstance = null; 
	 
	private CommandLineInput() {
		
	}

	public static CommandLineInput getInstance() {
		if(commandLineInputInstance == null) {
			commandLineInputInstance = new CommandLineInput();
		}
		return commandLineInputInstance;
	}
	
	@Override
	public String getValueFromUser() {
		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public String getName() {
		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public int getInt() {
		scanner = new Scanner(System.in);
		return scanner.nextInt();
	}


}
