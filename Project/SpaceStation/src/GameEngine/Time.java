package GameEngine;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jento Pieters
 * Class to keep track of time elapsed during the game.
 * Has an instance of the toolkit class and the timer class
 * Toolkit provides access to system specific resources
 * Timer allows for scheduling tasks to run at specific intervals
 */
public class Time {
    Toolkit toolkit;
    Timer timer;

    public Time(int seconds) {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new Reminder(), seconds * 1000);
    }

    class Reminder extends TimerTask {
        public void run() {
            System.out.println("The mother ship has arrived for the samples,you did not retrieve the item in time. \n GAME OVER!");
            toolkit.beep();
            System.exit(0);
        }
    }
//    public void getTimeRemaining(){
//    }
}