package service;

import entity.Order;
import entity.products.Product;

/**
 * Class represents order related methods.
 *
 * @author JB
 */

public class OrderService {

    public double calculateTotalOrderPrice(Order order) {
        return order.getProductList().stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
