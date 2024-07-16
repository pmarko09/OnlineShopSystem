package entity.products;

/**
 * This class represents product.
 * Contains product details like: id, name, price and quantity.
 * Product class is a parent class for other classes.
 *
 * @author JB
 */

public class Product {

    private final int id;
    private final String name;
    private final double price;
    private int productQuantity;

    /**
     * Constructs new Product with specified details.
     *
     * @param id              The id of the product.
     * @param name            entity.products.Product's name.
     * @param price           entity.products.Product's price.
     * @param productQuantity The quantity of the product.
     */

    public Product(int id, String name, double price, int productQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productQuantity = productQuantity;
    }

    //Getters and setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return name +
                " (" + price +
                "zł), quantity: " + productQuantity +
                ',';
    }
}
