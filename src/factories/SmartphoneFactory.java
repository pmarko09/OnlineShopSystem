package factories;

import entity.products.Product;
import entity.products.Smartphone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains smartphones and gathers them in a one list.
 */

public class SmartphoneFactory {

    /**
     * This method constructs 10 smartphones and gathers them in one list.
     *
     * @return The list of smartphones.
     */
    public static List<Product> produceSmartphone() {

        Smartphone smartphone1 = new Smartphone(11, "Samsung G10", 2999.99, 10, "Freh blue", 100,
                "Słuchawki kablowe");
        Smartphone smartphone2 = new Smartphone(22, "Huawei 5S 1000", 1999.99, 10, "Flash red", 90,
                "Ładowarka bezprzewodowa");
        Smartphone smartphone3 = new Smartphone(33, "Samsung S15", 3499.99, 10, "Pink", 100,
                "Słuchawki bluetooth");
        Smartphone smartphone4 = new Smartphone(44, "Samsung Demo X7", 3299.99, 10, "Dark green", 75,
                "Słuchawki bezprzewodowe");
        Smartphone smartphone5 = new Smartphone(55, "Samsung D55", 1499.99, 10, "Classy black", 85,
                "Słuchawki kablowe");
        Smartphone smartphone6 = new Smartphone(66, "Samsung FF11", 999.99, 10, "Total white", 98,
                "Galaxy Pods");
        Smartphone smartphone7 = new Smartphone(77, "Nokia 9911", 299.99, 10, "Pink", 97,
                "Etui");
        Smartphone smartphone8 = new Smartphone(88, "Iphone X", 2229.99, 10, "Orange", 100,
                "Airpods 2");
        Smartphone smartphone9 = new Smartphone(99, "Iphone 15 Pro", 7500.99, 10, "Total white", 89,
                "Airpods maxpro");
        Smartphone smartphone10 = new Smartphone(1010, "Iphone 14 mini", 3999.99, 10, "Freh blue", 91,
                "Ładowarka indukcyjna");

        return new ArrayList<>(Arrays.asList(smartphone1, smartphone2, smartphone3, smartphone4,
                smartphone5, smartphone6, smartphone7, smartphone8, smartphone9, smartphone10));
    }
}
