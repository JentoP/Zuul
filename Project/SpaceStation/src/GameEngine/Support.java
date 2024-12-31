package GameEngine;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Jento Pieters
 * The class represents a response generator.
 * It is used to generate a random response.
 */
public class Support {
    private Random randomGenerator;
    private ArrayList<String> defaultResponses;

    public Support() {
        randomGenerator = new Random();
        defaultResponses = new ArrayList<String>();
        fillDefaultResponses();
    }

    private void fillDefaultResponses() {
        defaultResponses.add("You called for help, but no one is answering...");
        defaultResponses.add("When you called for help, you saw something move, creepy...");
        defaultResponses.add("In space no one can hear you scream.");
        defaultResponses.add("You called for help, but it goes unanswered...");
        defaultResponses.add("You remember you have a manual.");
        defaultResponses.add("There is a manual in your pocket, maybe you should've checked that before you cried for help.");
        defaultResponses.add("No one was around you when asked for help, but you did anyway.");
        defaultResponses.add("Your call for help wasn't loud enough, try in CAPS!");
        defaultResponses.add("You check your communication device, for some reason it's not working.");
        defaultResponses.add("You hear your cry for help echo around the space station.");

    }

    public String generateResponse() {
        return pickDefaultResponse();
    }

    private String pickDefaultResponse() {
        int index = randomGenerator.nextInt(defaultResponses.size());
        return defaultResponses.get(index);
    }
}
