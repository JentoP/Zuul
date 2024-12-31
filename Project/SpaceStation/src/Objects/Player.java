/**
 * @author Jento Pieters
 * Class to create Player / User
 * Player can move between rooms and can have items in his possession.
 * Gives the ability for players to take, drop and check items.
 */
package Objects;

import Items.Item;

import java.util.ArrayList;

public class Player {

    private String name;
    private Hub currentHub;
    private ArrayList<Item> items;
    private double maxWeight;

    public Player(String name, Hub currentHub) {
        this.name = name;
        this.currentHub = currentHub;
        items = new ArrayList<>();
        maxWeight = 20;
    }

// GETTERS

    /**
     * @return name of the player
     */
    public String getName() {
        return name;
    }


    public Hub getCurrentHub() {
        return currentHub;
    }


    //    SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentHub(Hub currentHub) {
        this.currentHub = currentHub;
    }

    /**
     * Sets the weight that the player can carry
     */
    public void setWeight(double weight) {
        maxWeight = weight;
    }

    /**
     * Moves the player to a random location that's close to the players current location
     * Gets a random exit, if conditions are met moves the player to a room that's linked to this exit.
     */
    public void triggerTrap() {
        if (currentHub != null) {
            String randomExit = currentHub.getRandomExit(); //returns random exit
            Hub nextHub = currentHub.getExit(randomExit); //retrieve the linked room to this exit
            if (!nextHub.hasTrap()) {
                System.out.println("You trigger a trap.");
                for (int i = 0; i <= 2; i++) {
                    System.out.println("...");
                    try {
                        Thread.sleep(1500); //delay for dramatic effect
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                currentHub = nextHub; //moves the player to random room
                System.out.println("You move away from the trap. You are now in the " + currentHub.getDescription() + ".");
            } else System.out.println("With some luck, you avoided the trap.");
        }

    }

    /**
     * Checks if there is item with the given name
     * Checks in the arraylist items
     *
     * @param nameItem item to check
     */

    public boolean hasItem(String nameItem) {
        for (Item item : items) {
            if (item.getName().equals(nameItem))
                return true;
        }
        return false;
    }

    /**
     * Allows the player to drop an item
     * Removes an item that's in the player's possession.
     * Checks if the items is in the arraylist and adds this item to the room the player is currently in.
     *
     * @param nameItem item to drop
     * @return boolean true if the item is dropped
     */
    public boolean drop(String nameItem) {
        if (this.hasItem(nameItem)) {
            for (Item item : items) {
                if (item.getName().equals(nameItem)) {
                    items.remove(item);
                    currentHub.addItem(item);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes an item from the room
     * Checks if the room has this item if so adds the item to the player's possession and removes it from the current room.
     *
     * @param itemName item to take
     * @return
     */
    public boolean take(String itemName) {
        if (currentHub.hasItem(itemName)) {
            items.add(currentHub.getItem(itemName));
            currentHub.takeItem(itemName);
            return true;
        }
        return false;
    }

    /**
     * Checks for items that are in the player's possession.
     * If there are no items, it returns a String.
     * If there are items, it returns a String that's been looped where a description of all items have been added.
     *
     * @return String with information about the items
     */
    public String getBag() {
        String descr = "You find ";
        if (items.isEmpty()) {
            descr += "nothing noteworthy in your possession.";
        } else {
            descr += "the following items: ";
            for (Item item : items) {
                descr += "\n" + item.getLongDescription();
            }
        }
        return descr;
    }

    /**
     * @return total weight of all items in the bag
     */
    public double calculateBagWeight() {
        double bagWeight = 0;
        for (Item item : items) {
            bagWeight += item.getWeight();
        }
        return bagWeight;
    }

    /**
     * Checks for the weight on a single item in the room
     *
     * @param itemName item weight to check
     * @return weight of an item
     */
    public double checkItemWeight(String itemName) {
        double itemWeight = 0;
        if (currentHub.hasItem(itemName)) {
            itemWeight = currentHub.getItem(itemName).getWeight();
        }
        return itemWeight;
    }

    /**
     * @return the maximum weight a player can carry
     */
    public double maxWeight() {
        return maxWeight;
    }

    /**
     * if it is the required item it tells the player what they should do
     *
     * @param item to get a full description of
     */
    public void examineItem(String item) {
        if (!item.equals("samples")) {
            System.out.println("You examine the item.");
            System.out.println(currentHub.getItem(item).getLongDescription());
        }
        if (item.equals("samples")) {
            System.out.println("You examine the samples and luckily it's still intact. You should bring it to a safe location.");
            System.out.println(currentHub.getItem(item).getLongDescription());
        }

    }
}
