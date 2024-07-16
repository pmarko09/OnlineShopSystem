package entity;

import entity.products.Product;

import java.util.List;

/**
 * This class represents an invoice.
 * Has invoice id, customer with his all personal details, the products list and price.
 *
 * @author JB
 */

public class Invoice {

    private int id;
    private Customer customer;
    private List<Product> products;
    private double price;

    /**
     * Constructs new Invoice with details.
     *
     * @param id       Represents invoice's id.
     * @param customer Customer with personal details.
     * @param products The list of products on the invoice.
     * @param price    Summed price on invoice.
     */
    public Invoice(int id, Customer customer, List<Product> products, double price) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.price = price;
    }

    //Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Invoice " + id +
                ", Customer: " + customer +
                ", products:" + products +
                ", price: " + price + "zł.";
    }
}
