package manager;

import entity.products.Product;
import factories.ComputerFactory;
import factories.ElectronicFactory;
import factories.SmartphoneFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class gathers methods related to the products. Operates on the list of products too.
 *
 * @author JB
 */

public class ProductManager {

    private List<Product> products;

    /**
     * Constructs new product's list and adds to it all objects from ComputerFactory, SmartphoneFactory and ElectronicFactory.
     */
    public ProductManager() {
        products = new ArrayList<>();
        products.addAll(ComputerFactory.produceComputers());
        products.addAll(SmartphoneFactory.produceSmartphone());
        products.addAll(ElectronicFactory.produceElectronics());
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int productId) {
        products.removeIf(product -> product.getId() == productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Optional<Product> getProductById(int productId) {
        return products.stream()
                .filter(product -> product.getId() == productId)
                .findFirst();
    }

    public Optional<Product> getProductByName(String productName) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(productName))
                .findFirst();
    }

    public List<String> getProductsNames() {
        return products.stream()
                .map(product -> product.getName().toLowerCase())
                .toList();
    }

    @Override
    public String toString() {
        return "manager.ProductManager{" +
                "products=" + products +
                '}';
    }
}
