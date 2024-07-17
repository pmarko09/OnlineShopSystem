package entity;

import entity.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a shopping cart.
 * It contains the list of products chosen by the customer.
 *
 * @author JB
 */

public class Cart {

    /**
     * The list of products in the customer's cart.
     */
    private List<Product> productsInCart = new ArrayList<>();

    /**
     * This method ads chosen product, and it's quantity to the cart. Also, update the available quantity of the product.
     *
     * @param product  Product object from class entity.products.Product to be added to the cart.
     * @param quantity Quantity of chosen product to be added to the cart.
     * @throws IllegalArgumentException if the chosen quantity is bigger than available.
     */
    public boolean addProductToCart(Product product, int quantity) {
        if (product.getProductQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient quantity available.");
        }

        Optional<Product> existingProductOpt = productsInCart.stream()
                .filter(p -> p.getName().equals(product.getName()))
                .findFirst();

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setProductQuantity(existingProduct.getProductQuantity() + quantity);
            product.setProductQuantity(product.getProductQuantity() - quantity);
            return false; // Produkt zaktualizowany
        } else {
            productsInCart.add(new Product(product.getId(), product.getName(), product.getPrice(), quantity));
            product.setProductQuantity(product.getProductQuantity() - quantity);
            return true; // Nowy produkt dodany
        }
    }

    /**
     * This method is for removing product from cart.
     *
     * @param product Product object from class Product to be removed from the cart.
     */
    public void removeProductFromCart(Product product) {
        productsInCart.removeIf(p -> p.getName().equals(product.getName()));
    }

    /**
     * This method is for removing product from cart by chosen quantity.
     *
     * @param product  Product object from class Product.
     * @param quantity Quantity of chosen product to be removed.
     */
    public void removeProductsFromCartByQuantity(Product product, int quantity) {
        if (product.getProductQuantity() > 1) {
            product.setProductQuantity(product.getProductQuantity() - quantity);
        } else {
            productsInCart.removeIf(p -> p.getName().equals(product.getName()));
        }
    }

    public void clearProductCart() {
        productsInCart.clear();
    }

    public void viewCart() {
        productsInCart.forEach(System.out::println);
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public Optional<Product> getProductByNameFromCart(String productName) {
        return productsInCart.stream()
                .filter(product -> product.getName().equalsIgnoreCase(productName))
                .findFirst();
    }

    public double getTotalAmount() {
        return productsInCart
                .stream()
                .mapToDouble(product -> product.getPrice() * product.getProductQuantity())
                .sum();
    }

    public double applyDiscount() {
        return productsInCart
                .stream()
                .mapToDouble(product -> product.getPrice() * 0.95 * product.getProductQuantity())
                .sum();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productsInCart=" + productsInCart +
                '}';
    }
}
