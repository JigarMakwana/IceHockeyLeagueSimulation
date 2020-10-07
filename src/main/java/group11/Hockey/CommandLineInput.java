package group11.Hockey;
import java.util.Scanner;

public class CommandLineInput implements IUserInputMode {
	Scanner sc = null;
	

	
	public String getName() {
		sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	public int getInt() {
		sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	
	public void displayMessage(String message) {
		sc = new Scanner(System.in);
		System.out.println(message);
	}

}
