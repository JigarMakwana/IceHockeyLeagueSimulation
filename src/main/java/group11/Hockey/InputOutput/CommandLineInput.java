package group11.Hockey.InputOutput;

import java.util.Scanner;

public class CommandLineInput implements ICommandLineInput {
	Scanner scanner = null;

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
