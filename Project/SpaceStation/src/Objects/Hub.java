package Objects;

import Items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author Jento Pieters
 * Creates a room/hub/location
 * Previously Class Room
 */
public class Hub {
    private final String description;
    private final HashMap<String, Hub> exits;
    private final ArrayList<Item> items;
    private boolean closed;
    private boolean trap;
    private boolean noReturn;
    private String details;

    public Hub(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        closed = false;
        trap = false;
        noReturn = false;
        details = "nothing special about the room";
    }

    //SETTERS

    /**
     * Adds an item to the room
     *
     * @param item item to be added
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Sets the exit direction and it's corresponding room
     *
     * @param direction adds the direction
     * @param previous  links an exit to an existing room
     */
    public void setExit(String direction, Hub previous) {
        exits.put(direction, previous);
    }

    public void setClosed() {
        this.closed = true;
    }

    public void setOpen() {
        this.closed = false;
    }

    public void setTrap() {
        this.trap = true;
    }

    public void setNoReturn() {
        this.noReturn = true;
    }

    /**
     * Adds unnecessary more details to the room.
     *
     * @param detail immovable objects
     */
    public void setRoomDetails(String detail) {
        details = detail;
    }


    //GETTERS
    public String getDescription() {
        return description;
    }

    public Hub getExit(String direction) {
        return exits.get(direction);
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return item;
        }
        return null;
    }

    /**
     * Checks if the room is locked/closed
     *
     * @return true if locked
     */
    public boolean isLocked() {
        return closed;
    }

    /**
     * Checks if the room has a trap
     *
     * @return true if it's a trap
     */
    public boolean hasTrap() {
        return trap;
    }

    public boolean isPointOfNoReturn() {
        return noReturn;
    }

    /**
     * Creates a new Arraylist and adds the exits from the hashmap
     * Declares an integer variable that chooses a random index number from the range of the index of the arraylist
     *
     * @return a random Exit
     */
    public String getRandomExit() {
        List<String> randomDirection = new ArrayList<>(exits.keySet());
        int randomIndex = new Random().nextInt(randomDirection.size());
        return randomDirection.get(randomIndex);
    }

    /**
     * @return alle aanwezige uitgangen van de locatie
     */
    public String getAllExit() {
        String exitString = "";
        for (String direction : exits.keySet()) {
            exitString += ", " + direction;
        }
        return exitString;
    }

    /**
     * Shows all the items that were added to the room using a for loop
     *
     * @return all items in the room
     */

    public String getAllItems() {
        if (!items.isEmpty()) {
            String itemString = "";
            for (Item item : items) {
                itemString += "\n - " + item.getName();
            }
            return itemString;
        }
        return "None.";
    }

    /**
     * Gives a description of a room
     *
     * @return all the information about the room
     */
    public String getRoomDescription() {
        return "You are in the " + description + "." + "\n You can see that you can go" + getAllExit() + "\n You can see the following items in the room: " + getAllItems() + "\n You look around more closely and notice " + details + ".";
    }

//Methods

    /**
     * Checks if the given item is present in the room
     *
     * @param itemName add name of the items here
     * @return true in case the item is in the room
     */
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) return true;
        }
        return false;
    }

    /**
     * deletes the  item from the room if the item is present in the room
     *
     * @param name name of the item
     * @return null in case the item isn't in the room
     */
    public Item takeItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                if (items.remove(item)) {
                    return item;
                }
            }
        }
        return null;
    }
}

