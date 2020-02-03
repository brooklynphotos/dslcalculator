package io.sonarcloud.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class Instructions {
    private Instructions(){}

    public static Stream<Instruction> parseAll(String fullInstructionDSL) {
        return Arrays.stream(fullInstructionDSL.split("\n")).map(Instructions::parse);
    }

    public static Instruction parse(String instructionDSL) {
        final int lastIdx = instructionDSL.lastIndexOf(" ");
        // TODO figure out how to get the index
        int index = 0;
        if (lastIdx <= 0) {
            final Instruction.Operation op = Instruction.Operation.parse(instructionDSL);
            return new Instruction(index, op, Optional.empty());
        }
        final Instruction.Operation op = Instruction.Operation.parse(instructionDSL.substring(0, lastIdx));
        return new Instruction(index, op, Optional.of(Integer.parseInt(instructionDSL.substring(lastIdx + 1))));
    }

}
