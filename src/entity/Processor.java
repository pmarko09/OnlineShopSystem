package entity;

import java.util.Arrays;
import java.util.List;

/**
 * This enum represents processors.
 *
 * @author JB
 */

public enum Processor {
    INTEL_I3,
    INTEL_I5,
    INTEL_I7,
    RYZEN3,
    RYZEN5,
    RYZEN7;

    public static List<Processor> getAllProcessors() {
        return Arrays.asList(Processor.values());
    }
}


