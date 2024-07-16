package entity;

import entity.products.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This class represents order in the online shop.
 * It contains order details like: id, customer, list of selected products, total order cost and the time order has been created.
 *
 * @author JB
 */

public class Order {

    private final int id;
    private final Customer customer;
    private final List<Product> productList;
    private final double orderTotalPrice;
    private final LocalDateTime orderTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter formatterDelivery = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a new entity.Order with the specified details.
     *
     * @param orderId         entity.Order's id.
     * @param customer        entity.Customer on the order.
     * @param productList     Products on the order.
     * @param orderTotalPrice entity.Order's final cost.
     */

    public Order(int orderId, Customer customer, List<Product> productList, double orderTotalPrice) {
        this.id = orderId;
        this.customer = customer;
        this.productList = productList;
        this.orderTotalPrice = orderTotalPrice;
        this.orderTime = LocalDateTime.now();
    }


    //Getters
    public int getOrderId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }
    public LocalDateTime getOrderTime () {
        return orderTime;
    }

    public String getOrderTimeFormatted() {
        return orderTime.format(formatter);
    }

    public String deliveryOrderTime() {
        LocalDateTime deliveryTime = orderTime.plusDays(7);
        return deliveryTime.format(formatterDelivery);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", customer=" + customer +
                ", productList=" + productList +
                ", orderTotalPrice=" + orderTotalPrice +
                ", orderTime=" + orderTime +
                '}';
    }
}