package Items;

/**
 * Class for an item in the game
* @author Jento Pieters
 */

public class Item {
    private String name;
    private String description;
    private double weight;

    public Item(String name, String description, double weight) {
        this.name = name.toLowerCase();
        this.description = description;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getLongDescription() {
        return "Name: " + this.name.toUpperCase() + "\n" + "Description: " + this.description + "\n" + "Weight: " + this.weight + "\n";
    }
}
