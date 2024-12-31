package GameEngine;


import java.util.HashMap;
/**
 * @author Jento Pieters
 * Keeps track of all enum commands
 * Checks if the given command is equal to the constants
 * Previously Class CommandWords
 */
public class Commands {

    private HashMap<String, Command> validCommands;
    private String currentCommand;

    public Commands() {
        validCommands = new HashMap<String, Command>();
        validCommands.put("go", Command.GO);
        validCommands.put("help", Command.HELP);
        validCommands.put("quit", Command.QUIT);
        validCommands.put("take", Command.TAKE);
        validCommands.put("drop", Command.DROP);
        validCommands.put("manual", Command.MANUAL);
        validCommands.put("look", Command.LOOK);
        validCommands.put("i'm", Command.NAME);
        validCommands.put("examine", Command.EXAMINE);
        validCommands.put("check", Command.CHECK);
    }

    /**
     * Checks if the given word is a constant
     */
    public boolean isValid(String word) {
        String normalizedWord = word.toLowerCase();
        return validCommands.containsKey(normalizedWord);
    }


    /**
     * Return constant that is the same as the given string
     */

    public Command getCommand(String word) {
        String normalizedWord = word.toLowerCase();
        if (validCommands.containsKey(normalizedWord)) {
            return validCommands.get(normalizedWord);
        }
        return Command.UNKNOWN;
    }

    public String getCurrentCommand() {
        return currentCommand;
    }

    /**
     * Gives a string of all commands
     *
     * @return shows all commands
     */
    public String showAll() {
        String show = "";
        for (String command : validCommands.keySet()) {
            show += " -" + command;
        }
        return show;
    }

    public void setCommand(String command) {
        this.currentCommand = command;
    }


}
