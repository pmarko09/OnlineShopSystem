package service;

import entity.Processor;
import entity.products.Computer;
import entity.products.Product;

/**
 * This class represent methods related to Computer.
 *
 * @author JB
 */

public class ComputerService {

    /**
     * This method checks if product is instance of Computer, and if so, sets computer's processor to the one selected.
     * If not then throws IllegalArgumentException.
     *
     * @param product   The Product object of which processor is to be set.
     * @param processor The processor selected to be set.
     * @throws IllegalArgumentException The exception is thrown once chosen product is not instance of the Computer.
     */

    public void choseCompProcessor(Product product, Processor processor) {
        if (product instanceof Computer) {
            ((Computer) product).setProcessor(processor);
        } else {
            throw new IllegalArgumentException("Product is not a Computer.");
        }
    }

    /**
     * This method sets memory of selected product. Also, checks if chosen product is instance of the Computer.
     * If it is not then throws IllegalArgumentException.
     *
     * @param product The Product object of which memory is to be set.
     * @param memory  The memory selected to be set.
     * @throws IllegalArgumentException The exception is thrown once chosen product is not instance of the Computer.
     */

    public void setCompMemory(Product product, int memory) {
        if (product instanceof Computer) {
            ((Computer) product).setMemory(memory);
        } else {
            throw new IllegalArgumentException("Product is not a Computer.");
        }
    }
}

