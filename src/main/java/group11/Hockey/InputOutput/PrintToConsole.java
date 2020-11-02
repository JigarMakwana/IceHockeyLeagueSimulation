package group11.Hockey.InputOutput;

public class PrintToConsole implements IPrintToConsole {
	
	@Override
	public void print(String message) {
		System.out.println(message);
	}

}
