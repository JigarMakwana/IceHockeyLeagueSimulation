package group11.Hockey.InputOutput;

public class UserInputValidation implements IUserInputValidation {
    IDisplay display = new Display();
    private ICommandLineInput userInputMode = new CommandLineInput();
    @Override
    public int userIterativeIntegerSelection(int listSize)
    {
        boolean isValidInput = false;
        int userInput = 0;
        do {
            while(userInput <= 0)
            {
                try {
                    userInput = userInputMode.getInt();
                } catch (Exception e) {
                    display.showMessageOnConsole("Please select valid value");
                }
            }
            if(((userInput >= 1) && (userInput <= listSize)))
            {
                isValidInput = true;
                break;
            }
            else
            {
                display.showMessageOnConsole("Please select valid value");
                isValidInput = false;
                userInput = 0;
            }
        } while (isValidInput == false);
        return userInput;
    }

    @Override
    public int validateUserTradeInput()
    {
        boolean isValidInput = false;
        int userInput = -1;
        do {
            while(userInput == -1)
            {
                try {
                    userInput = userInputMode.getInt();
                } catch (Exception e) {
                    display.showMessageOnConsole("Please enter 0 or 1");
                }
            }
            if(((userInput == 1) || (userInput == 0)))
            {
                isValidInput = true;
                break;
            }
            else
            {
                display.showMessageOnConsole("Please enter 0 or 1");
                isValidInput = false;
                userInput = -1;
            }
        } while (isValidInput == false);
        return userInput;
    }
}
