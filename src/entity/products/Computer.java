package entity.products;

import entity.Processor;

/**
 * This class extends Product class and represent a computer.
 * It contains computer details like: id, name, price, quantity available, processor and memory.
 *
 * @author JB
 */

public class Computer extends Product {

    private Processor processor;
    private int memoryRAM;

    /**
     * Constructs a new Computer with specified details.
     *
     * @param id                Computer's id.
     * @param name              Computer's name.
     * @param price             Computer's price.
     * @param quantityAvailable Computer's available quantity.
     * @param processor         Computer's processor.
     * @param memory            Computer's memory.
     */

    public Computer(int id, String name, double price, int quantityAvailable, Processor processor, int memory) {
        super(id, name, price, quantityAvailable);
        this.processor = processor;
        this.memoryRAM = memory;
    }

    //Getters and setters

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public int getMemory() {
        return memoryRAM;
    }

    public void setMemory(int memory) {
        this.memoryRAM = memory;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "processor: " + processor +
                ", memoryRAM: " + memoryRAM +
                "} " + super.toString();
    }
}
