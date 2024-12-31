import GameEngine.*;
import Items.Item;
import Objects.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Jento Pieters
 * Makes use of all the classes and their methods and adds information to the game.
 * Creates rooms, items and players.
 * Has many methods that make the game possible.
 * Has methods that provide actions for the player.
 * @version 1.1
 */
public class Game {
    private final Input input;
    private final Player player;
    private final Support support;
    private final ArrayList<Hub> hubs = new ArrayList<>();

    /**
     * Creates all objects for the game
     * Add here all the rooms/items/attributes for the game
     * Adds a new player to the game
     * Adds all rooms to a hashmap
     */
    public Game() {
        input = new Input();
        support = new Support();

        //CREATE ROOMS HERE
        Hub CenterHub = new Hub("Central Hub of the Space Station");
        Hub Cockpit = new Hub("Cockpit of the Space Station");
        Hub MedBay = new Hub("Medical Room");
        Hub CouplingBay = new Hub("Coupling Bay");
        Hub Space = new Hub("Outer Space");
        Hub Bathroom = new Hub("Bathroom");

        Hub LowerHub = new Hub("Lower Hub, underneath the Central Hub");
        Hub QuarantineZone = new Hub("Quarantine Zone");
        Hub SecurityRoom = new Hub("Security Room");
        Hub ServerRoom = new Hub("Server Room");
        Hub EngineeringRoom = new Hub("Engineering Room");

        Hub UpperHub = new Hub("Upper Hub, above the Central Hub");
        Hub SpaceVista = new Hub("Space Vista");
        Hub Showers = new Hub("Showers");
        Hub CrewCabin = new Hub("Crew Cabin");
        Hub Gym = new Hub("Fitness Room");
        Hub InsideShower = new Hub("private shower");

        //CREATE EXITS HERE
        //Center Hub
        CenterHub.setExit("up", UpperHub);
        CenterHub.setExit("down", LowerHub);
        CenterHub.setExit("forward", Cockpit);
        CenterHub.setExit("backwards", CouplingBay);
        CenterHub.setExit("right", MedBay);
        CenterHub.setExit("left", Bathroom);
        Cockpit.setExit("back", CenterHub);
        MedBay.setExit("back", CenterHub);
        Bathroom.setExit("back", CenterHub);
        CouplingBay.setExit("back", CenterHub);
        CouplingBay.setExit("forward", Space);
        Space.setExit("back", CouplingBay);

        //Lower Hub
        LowerHub.setExit("up", CenterHub);
        LowerHub.setExit("forward", QuarantineZone);
        LowerHub.setExit("backwards", ServerRoom);
        LowerHub.setExit("right", SecurityRoom);
        LowerHub.setExit("left", EngineeringRoom);
        QuarantineZone.setExit("back", LowerHub);
        SecurityRoom.setExit("back", LowerHub);
        ServerRoom.setExit("back", LowerHub);
        EngineeringRoom.setExit("back", LowerHub);

        //Upper Hub
        UpperHub.setExit("down", CenterHub);
        UpperHub.setExit("forward", SpaceVista);
        UpperHub.setExit("backwards", CrewCabin);
        UpperHub.setExit("right", Showers);
        UpperHub.setExit("left", Gym);
        SpaceVista.setExit("back", UpperHub);
        Showers.setExit("back", UpperHub);
        Showers.setExit("forward", InsideShower);
        CrewCabin.setExit("back", UpperHub);
        Gym.setExit("back", UpperHub);
        InsideShower.setExit("back", Showers);

        //EXTRA ROOM DETAILS
        Cockpit.setRoomDetails("a lot of flight controls and four pilot seats");
        MedBay.setRoomDetails("a comfortable bed with straps and some medical kits");
        Bathroom.setRoomDetails("a toilet and a washing table");
        CouplingBay.setRoomDetails("a big hatch");
        Space.setRoomDetails("a spectacular view of millions of stars all around you");
        QuarantineZone.setRoomDetails("a big box with a label \" FOR SAMPLES \" on it");
        SecurityRoom.setRoomDetails("a lot of monitors and one of your colleagues looking at them closely");
        EngineeringRoom.setRoomDetails("a bunch of sophisticated apparatus and one of your colleagues hard at work");
        CrewCabin.setRoomDetails("four lockers and four sleep stations");
        ServerRoom.setRoomDetails("a few computer server racks and some very loud fans");
        Gym.setRoomDetails("a bicycle, a treadmill and weightlifting machine");
        SpaceVista.setRoomDetails("a big window with an amazing view of the planet Mars");
        Showers.setRoomDetails("a wet floor and a private shower in use");

        //ROOM ATTRIBUTES
        Cockpit.setClosed();
        InsideShower.setTrap();
        Space.setClosed();
        Space.setNoReturn();
        ServerRoom.setClosed();
        QuarantineZone.setClosed();

        //CREATE & ADD ITEMS HERE
        Item sample = new Item("Samples", "This sample contains evidence of life on Mars.", 5);
        Item key = new Item("Badge", "A security card you can use to open any hatch.", 0.1);
        Item gun = new Item("Plasmagun", "A two-handed heavy gun that shoots plasma rays, safe to use in space vessels, not so safe towards others though.", 20);
        Item suit = new Item("Suit", "A space suit worn to keep a human alive in the harsh environment of outer space.", 12);
        Item pc = new Item("Notebook", "A portable computer that's quite powerful and has a touch screen", 3);


        //Creates player
        this.player = new Player(null, CenterHub);

        //Adds rooms to the collection
        hubs.add(CenterHub);
        hubs.add(Cockpit);
        hubs.add(MedBay);
        hubs.add(CouplingBay);
        hubs.add(Space);
        hubs.add(Bathroom);
        hubs.add(LowerHub);
        hubs.add(QuarantineZone);
        hubs.add(SecurityRoom);
        hubs.add(ServerRoom);
        hubs.add(EngineeringRoom);
        hubs.add(UpperHub);
        hubs.add(SpaceVista);
        hubs.add(Showers);
        hubs.add(CrewCabin);
        hubs.add(Gym);
        hubs.add(InsideShower);

        Hub randomRoom = getRandomRoom();

        randomRoom.addItem(sample);
        SecurityRoom.addItem(gun);
        SecurityRoom.addItem(key);
        CrewCabin.addItem(suit);
        ServerRoom.addItem(pc);
    }

    //PLAYER ACTIONS
    private void look() {
        System.out.println(player.getCurrentHub().getRoomDescription());
    }

    private void examine(Validate command) {
        if (!command.hasSecondCommand()) {
            System.out.println("Examine what?");
        }
        String itemName = command.getSecondcommand().toLowerCase();
        if (player.getCurrentHub().hasItem(itemName)) {
            player.examineItem(itemName);
        } else {
            System.out.println("Item not found.");
        }

    }

    /**
     * Moves the player to the next room if they put in the right direction
     * If the room has a trap, it teleports the player to a random room.
     * If the door is closed they don't move but if they have the right item they do move.
     *
     * @param command room where the player wants to go
     */
    private void goRoom(Validate command) {
        if (!command.hasSecondCommand()) {
            System.out.println("Go which way?");
        }
        String direction = command.getSecondcommand();
        Hub nextRoom = player.getCurrentHub().getExit(direction);

        if (nextRoom == null) {
            //if there is no room:
            System.out.println("There's nothing there!");
        } else if (!nextRoom.isLocked()) {
            //if the room isn't locked but has a trap:
            if (nextRoom.hasTrap()) {
                moveToRandomHub();
            } else {
                //if the room isn't locked:
                player.setCurrentHub(nextRoom);
                System.out.println("You arrive in the " + player.getCurrentHub().getDescription() + ".");
                winningScenario();
            }
        }
        //if the room is locked but the player has the key:
        else if (player.hasItem("badge")) {
            nextRoom.setOpen();
            System.out.println("You use the key to open the hatch.");
            player.setCurrentHub(nextRoom);
            System.out.println("You arrive in the " + player.getCurrentHub().getDescription() + ".");
            winningScenario();

            if (player.getCurrentHub().isPointOfNoReturn() && player.hasItem("suit")) {
                System.out.println("You open the hatch and all air sucks out of the room, luckily you had your space suit.");
                System.out.println("You arrive in the " + player.getCurrentHub().getDescription() + ".");
            } else {
                if (player.getCurrentHub().isPointOfNoReturn() && !player.hasItem("suit")) {
                    System.out.println("You open the hatch and all air sucks out of the room and also your lungs. You are dead.");
                    gameOver();
                }
            }
        } else System.out.println("The hatch is closed.");
    }


    /**
     * Checks if it's a valid item
     * Then checks if the item isn't too heavy for the player
     * Makes it possible for the player to pick up an item and add it to their bag.
     *
     * @param command item name
     */
    private void take(Validate command) {
        if (!command.hasSecondCommand()) {
            System.out.println("Take what?");
        }
        if (command.hasSecondCommand() && command.getSecondcommand() != null) { //fixes the exception error for lowercase method

            String itemName = command.getSecondcommand().toLowerCase();
            if ((player.calculateBagWeight() + player.checkItemWeight(itemName)) <= player.maxWeight()) { //checks if the players weight won't be too high if they take the item
                if (player.getCurrentHub().hasItem(itemName)) {
                    player.take(itemName);
                    System.out.println("You take the " + itemName.toLowerCase() + " and put it in your bag.");
                } else {
                    System.out.println("Item not found.");
                }
            } else System.out.println("You can't carry any more items, drop something to pick this item up.");
        }
    }

    private void drop(Validate command) {
        if (!command.hasSecondCommand()) {
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondcommand().toLowerCase();
        if (player.drop(itemName)) {
            System.out.println("You drop the " + itemName.toLowerCase() + ", but there's no gravity so it just floats around.");
        } else {
            System.out.println("You don't have a " + itemName.toLowerCase() + " in your possession.");
        }
    }

    public Hub getRandomRoom() {
        Random random = new Random();
        int randomIndex = random.nextInt(hubs.size());
        return hubs.get(randomIndex);
    }

    /**
     * Moves the player to a random location
     * Gets a random exit, if conditions are met moves the player to a room that's linked to this exit.
     */
    public void moveToRandomHub() {
//        if (player.getCurrentHub() != null) {
////            String randomExit = getRandomRoom();
////            Hub nextHub = player.getCurrentHub().getExit(randomExit); //retrieve the linked room to this exit
        Hub nextHub = getRandomRoom();
        if (!nextHub.hasTrap()) {
            System.out.println("You get hit in the face by someone and you blackout.");
            for (int i = 0; i <= 2; i++) {
                System.out.println("...");
                try {
                    Thread.sleep(1500); //delay for dramatic effect
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            player.setCurrentHub(nextHub); //moves the player to random room
            System.out.println("You wake up and look around, you notice you are now in the " + player.getCurrentHub().getDescription() + ".");
        } else System.out.println("With some luck, you avoid being hit in the face.");
    }

//    PLAYER CHECK

    /**
     * Sets the player's name before the game starts.
     *
     * @param command Input for the player's name.
     */
    private void setPlayerName(Validate command) {
        if (!command.hasSecondCommand()) {
            System.out.println("I'm sorry, I didn't get your name.");
            return;
        }
        String playerName = command.getSecondcommand();
        player.setName(playerName);
    }

    /**
     * Adds the player's name before the game starts
     *
     * @param command give valid command
     * @return
     */
    public boolean processPlayer(Validate command) {
        boolean wantsToQuit = false;
        Command commando = command.getFirstcommand();
        switch (commando) {
            case UNKNOWN:
                System.out.println("Unknown command, type 'manual' to see all valid commands.");
                break;
            case NAME:
                setPlayerName(command);
                break;
            case QUIT:
                quit(command);
                break;
        }
        return wantsToQuit;
    }


    public void winningScenario() {
        if (player.hasItem("samples") && player.getCurrentHub().getDescription().equals("Quarantine Zone")) {
            gameWon();
        }
    }

    /**
     * If there is no player name, it prints only a welcome message, if the player has given a name then it processes the player.
     */
    public void checkPlayer() {
        if (player.getName() == null) {
            printWelcome();

            boolean finished = false;
            while (!finished) {
                Validate command = input.getCommands();
                processPlayer(command);
                if (player.getName() != null) {
                    finished = true;
                }
            }
        }
    }

//  PRINT METHODS

    /**
     * Displays a welcome message with information about the game to the user when starting the game
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Fermi Paradox!");
        System.out.println("Fermi Paradox is a simple text-based adventure game where you have to solve the Fermi Paradox by finding evidence of life and returning it back to Earth!");
        System.out.println();
        System.out.println("If you need information about all possible commands type: \"" + Command.MANUAL + "\". If you need any help, just type \"" + Command.HELP + "\"");
        System.out.println("Before we continue, what is your name? (Type \"" + Command.NAME + "\" before your name)");
    }

    private void printIntroduction() {
        System.out.println("Greetings " + player.getName() + "! You are an astronaut on a space station in orbit around Mars.");
        System.out.println("Your team has successfully found evidence of life on Mars. But somehow one of your colleagues lost the samples somewhere on the station.");
        System.out.println("Your mission is to find the samples and bring all of them back to the quarantine zone where they can be safely stored.");
        System.out.println("You have to be quick because in 5 minutes the mother ship will arrive to bring back the samples to Earth.");
        System.out.println("Good luck!");
        System.out.println();
        System.out.println();
        System.out.println("You are now in the " + player.getCurrentHub().getDescription() + ".");

    }

    private void printHelp() {
        System.out.println(support.generateResponse());
    }

    private void printManual() {
        System.out.println("You check your manual and find the following information:");
        System.out.println("Possible commands are: " + input.showCommands());
        System.out.println();
    }

    private void printBagInfo(Validate command) {
        if (!command.hasSecondCommand()) {
            System.out.println("Check what?");
            return;
        }
        if (command.getSecondcommand().equalsIgnoreCase("bag")) {
            System.out.println("You check your bag.");
            System.out.println(player.getBag());
//            if (command.getSecondcommand().equalsIgnoreCase("time")) {
//                System.out.println(Time.getTimeRemaining);
//            }
        }
    }

    private void printSuspense(int howMany) {
        for (int i = 0; i < howMany; i++) {
            System.out.println("...");
            try {
                Thread.sleep(1500); //delay for dramatic effect
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

//GAME METHODS

    /**
     * Method that enables the quit command.
     *
     * @param command QUIT commando
     * @return if the command is right, return true
     */
    private void quit(Validate command) {
        if (command.hasSecondCommand()) {
            System.out.println("Type \"" + Command.QUIT + "\" once to exit the game.");
        } else
            System.out.println("Thank you for playing! Quitting the game... ");
        System.exit(1);
    }

    /**
     * Processes the first command that was given.
     * Validate command checks if the command is a command from the enum class.
     * If the command is wrong, it prints a line.
     * If the command is valid, it performs a method that's present in the current class.
     *
     * @param command give valid command
     * @return if true the player wants to quit
     */
    public boolean processCommand(Validate command) {
        boolean wantsToQuit = false;
        Command commando = command.getFirstcommand();
        switch (commando) {
            case UNKNOWN:
                System.out.println("Unknown command, type \"" + Command.MANUAL + "\" to see all valid commands.");
                break;
            case HELP:
                printHelp();
                break;
            case LOOK:
                look();
                break;
            case EXAMINE:
                examine(command);
                break;
            case DROP:
                drop(command);
                break;
            case TAKE:
                take(command);
                break;
            case GO:
                goRoom(command);
                break;
            case CHECK:
                printBagInfo(command);
                break;
            case QUIT:
                wantsToQuit = true;
                quit(command);
                break;
            case MANUAL:
                printManual();
                break;
        }
        return wantsToQuit;
    }

    /**
     * Print een welkom bericht
     * Prints a welcome message
     * Initiate a loop that keeps running and asking for commands until a certain specified command has been given.
     */
    public void start() {
        printIntroduction();
        new Time(300);
        boolean finished = false;
        while (!finished) {
            Validate command = input.getCommands();
            finished = processCommand(command);
        }
    }

    public void gameOver() {
        printSuspense(2);
        System.out.println("Astronaut " + player.getName() + " failed the mission!");
        System.out.println("GAME OVER");
        System.exit(1);
    }

    public void gameWon() {
        printSuspense(1);
        System.out.println("Congratulations!");
        System.out.println("Astronaut " + player.getName() + " has successfully completed the mission!");
        System.out.println("The world thanks you for service. You will be remembered as the discoverer of extraterrestrial life.");
        System.out.println("Goodbye!");
        System.exit(1);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.checkPlayer();
        game.start();
    }
}






