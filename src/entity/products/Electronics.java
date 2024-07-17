package entity.products;

/**
 * This class extends entity.products.Product class and represent entity.products.Electronics.
 * It contains electronics details like: id, name, price and available quantity.
 *
 * @author JB
 */

public class Electronics extends Product {
    public Electronics(int id, String name, double price, int numberAvailable) {
        super(id, name, price, numberAvailable);
    }

    @Override
    public String toString() {
        return "Electronics{ "
                + getName() +
                ", price: " + getPrice() +
                "zł, quantity: " + getProductQuantity() +
                "},";
    }
}
