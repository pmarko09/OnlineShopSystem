package entity.products;

/**
 * This class represent smartphone and extends Product class.
 * Contains variables like: id, name, price, quantity available, color, battery capacity and accessories assigned to each smartphone.
 *
 * @author JB
 */

public class Smartphone extends Product {

    private String color;
    private int batteryCapacity;
    private String accessories;

    /**
     * Constructs new Smartphone with specified details.
     *
     * @param id                Smartphone's ide.
     * @param name              Smartphone's name.
     * @param price             Smartphone's price.
     * @param quantityAvailable Quantity available of the smartphone.
     * @param color             Smartphone's color.
     * @param batteryCapacity   Smartphone's battery capacity.
     * @param accessories       Smartphone's assigned accessories.
     */

    public Smartphone(int id, String name, double price, int quantityAvailable, String color, int batteryCapacity, String accessories) {
        super(id, name, price, quantityAvailable);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = accessories;
    }

    // Getters and setters

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    @Override
    public String toString() {
        return "Smartphone{ " + getName() +
                ", color: " + getColor() +
                ", " + getPrice() + "zł" +
                ", quantity: " + getProductQuantity() + "},";
    }
}
