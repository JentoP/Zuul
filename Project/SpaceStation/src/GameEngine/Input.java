package GameEngine;

import java.util.Scanner;

/**
 * Generates an input for the user.
 * Previously Class Parser
 *
 * @author Jento Pieters
 */
public class Input {
    private Commands commands;
    private Scanner scanner;


    public Input() {
        commands = new Commands();
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the input and checks if it's a valid command.
     *
     * @return returns a valid command
     */
    public Validate getCommands() {
        //declarations
        String input;
        String command1 = null;
        String command2 = null;
        System.out.print(">");
        input = scanner.nextLine();
        //read input
        Scanner sc = new Scanner(input);
        if (sc.hasNext()) {
            command1 = sc.next();
            if (sc.hasNext()) {
                command2 = sc.next();
            }
        }
        if (commands.isValid(command1)) {
            return new Validate(commands.getCommand(command1), command2);
        } else {
            return new Validate(Command.UNKNOWN, command2);
        }
    }

    /**
     * @return all valid commands
     */
    public String showCommands() {
        return commands.showAll();
    }
}

