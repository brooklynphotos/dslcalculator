package io.sonarcloud;

import io.sonarcloud.model.Instruction;
import io.sonarcloud.model.Instructions;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class DSLCalculator {
    public float[] process(final String instruction) {
        if (instruction.trim().length() == 0) {
            return new float[]{};
        }
        return processInstructions(Instructions.parseAll(instruction));
    }

    private float[] processInstructions(final Stream<Instruction> instructions) {
        final List<Integer> result = new LinkedList<>();
        // starting with zero and no display
        // TODO prefer to do this more functional way to avoid mutation
        Calculation calculation = new Calculation();
        instructions.forEach(instruction ->{
            calculation.performOperation(instruction);
        });
        return calculation.getDisplay();
    }
}
