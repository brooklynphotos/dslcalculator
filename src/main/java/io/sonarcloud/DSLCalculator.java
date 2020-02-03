package io.sonarcloud;

import io.sonarcloud.model.Instruction;
import io.sonarcloud.model.Instructions;

import java.util.stream.Stream;

public class DSLCalculator {
    public float[] process(final String instruction) {
        if (instruction.trim().length() == 0) {
            return new float[]{};
        }
        return processInstructions(Instructions.parseAll(instruction));
    }

    private float[] processInstructions(final Stream<Instruction> instructions) {
        // starting with zero and no display
        Calculation result = instructions.reduce(
                new Calculation(),
                Calculation::performOperation,
                (calc1, calc2) -> calc2 // seems useless
        );
        return result.getDisplay();
    }
}
