package factories;

import entity.Processor;
import entity.products.Computer;
import entity.products.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class represents ready list of Computers with details.
 *
 * @author JB
 */

public class ComputerFactory {

    /**
     * This method constructs 10 computers and gather them in one list.
     *
     * @return The list of computers.
     */
    public static List<Product> produceComputers() {

        Computer computer1 = new Computer(1, "MAD DOG PBM", 10900.99, 12, Processor.INTEL_I7, 32000);
        Computer computer2 = new Computer(2, "MSI MAG Inifite", 5399.99, 10, Processor.INTEL_I3, 16000);
        Computer computer3 = new Computer(3, "LENOVO Legion", 5799.99, 8, Processor.INTEL_I5, 32000);
        Computer computer4 = new Computer(4, "HP Omen", 4999.99, 15, Processor.INTEL_I3, 16000);
        Computer computer5 = new Computer(5, "ACTINA ENDORFY", 5559.99, 3, Processor.INTEL_I3, 32000);
        Computer computer6 = new Computer(6, "MAD DOG ENDORFY", 6000.99, 6, Processor.RYZEN7, 16000);
        Computer computer7 = new Computer(7, "OPTIMUS E-SPORT", 8999.99, 4, Processor.RYZEN5, 32000);
        Computer computer8 = new Computer(8, "VICTUS 15L", 7999.99, 3, Processor.RYZEN7, 16000);
        Computer computer9 = new Computer(9, "TURBO GRR", 3999.99, 20, Processor.INTEL_I5, 8000);
        Computer computer10 = new Computer(10, "BEAUTY W321", 3899.99, 17, Processor.RYZEN3, 16000);

        return new ArrayList<>(Arrays.asList(computer1, computer2, computer3, computer4, computer5,
                computer6, computer7, computer8, computer9, computer10));
    }
}
