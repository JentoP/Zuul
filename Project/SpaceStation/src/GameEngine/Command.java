package GameEngine;

/**
 * @author Jento Pieters
 * enum class that declares constants
 * previously CommandWord
 */
public enum Command {
    GO("go"),
    LOOK("look"),
    TAKE("take"),
    DROP("drop"),
    EXAMINE("examine"),
    QUIT("exit"),
    HELP("help"),
    UNKNOWN("?"),
    MANUAL("manual"),
    CHECK("check"),
    NAME("I'm");


    private String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }


}
