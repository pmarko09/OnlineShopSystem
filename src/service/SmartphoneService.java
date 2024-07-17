package service;

import entity.products.Product;
import entity.products.Smartphone;

/**
 * This class represent methods relate to smartphones.
 *
 * @author JB
 */

public class SmartphoneService {

    /**
     * This method checks if product is instance of Smartphone, and if so, sets smartphone's color to the one selected.
     *
     * @param product The Product object of which color is to be set.
     * @param color   The color selected for updating product's current color.
     */

    public void setPhoneColor(Product product, String color) {
        if (product instanceof Smartphone) {
            ((Smartphone) product).setColor(color);
        } else {
            productIsNotSmartphone();
        }
    }

    /**
     * This method checks if product is instance of Smartphone, and if so, sets smartphone's battery to the one selected.
     *
     * @param product The Product object of which battery is to be set.
     * @param battery The battery selected for updating product's current battery.
     */

    public void setPhoneMemory(Product product, int battery) {
        if (product instanceof Smartphone) {
            ((Smartphone) product).setBatteryCapacity(battery);
        } else {
            productIsNotSmartphone();
        }
    }

    /**
     * This method checks if product is instance of Smartphone, and if so, sets smartphone's accessories to the one selected.
     *
     * @param product     The Product object of which accessories are to be set.
     * @param accessories The accessories selected for updating product's current accessories.
     */

    public void addPhoneAccessories(Product product, String accessories) {
        if (product instanceof Smartphone) {
            ((Smartphone) product).setAccessories(accessories);
        } else {
            productIsNotSmartphone();
        }
    }

    private void productIsNotSmartphone() {
        throw new IllegalArgumentException("Product is not a Smartphone.");
    }
}
