package factories;

import entity.products.Electronics;
import entity.products.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains electronics and gathers them in a one list.
 *
 * @author JB
 */

public class ElectronicFactory {

    /**
     * This method constructs 6 electronics and gather them in one list.
     *
     * @return The list of electronics.
     */
    public static List<Product> produceElectronics() {

        Electronics electronics1 = new Electronics(111, "Fridge", 5000, 100);
        Electronics electronics2 = new Electronics(222, "Washing mashine", 3000, 50);
        Electronics electronics3 = new Electronics(333, "Oven", 2000, 40);
        Electronics electronics4 = new Electronics(444, "Microwave", 500, 55);
        Electronics electronics5 = new Electronics(555, "Kettle", 150, 200);
        Electronics electronics6 = new Electronics(666, "Induction Hob", 1300, 125);

        return new ArrayList<>(Arrays.asList(electronics1, electronics2, electronics3,
                electronics4, electronics5, electronics6));
    }
}
