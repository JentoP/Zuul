package GameEngine;

/**
 * This class validates if the given command is correct.
 * Previously Class Command.
 */
public class Validate {

    private Command firstCommand;
    private String secondCommand;

    /**
     * Validates a given command
     *
     * @param command1 input for a command to check
     * @param command2 a String that can trigger a method
     */
    public Validate(Command command1, String command2) {
        firstCommand = command1;
        this.secondCommand = command2;
    }

    public Command getFirstcommand() {
        return firstCommand;
    }

    public String getSecondcommand() {
        return secondCommand;
    }


    public boolean isUnknown() {
        return firstCommand == Command.UNKNOWN;
    }


    public boolean hasSecondCommand() {
        return secondCommand != null;
    }
}
