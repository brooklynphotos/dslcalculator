package io.sonarcloud.model;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstructionsTest {

    @Test
    public void parseAll() {
        final List<Instruction> ins = Instructions
                .parseAll("ADD 1\nSUBTRACT 2\nDIVIDE BY 3\nDISPLAY\nMULTIPLY BY 10")
                .collect(Collectors.toList());
        assertEquals(5, ins.size());
        assertEquals(new Instruction(0, Instruction.Operation.SUBTRACT, 2), ins.get(1));
    }

    @Test
    public void givenValidInstruction_parseReturnsInstruction() {
        assertEquals(new Instruction(0, Instruction.Operation.ADD, 2), Instructions.parse("ADD 2"));
        assertEquals(new Instruction(0, Instruction.Operation.DIVIDE, 2), Instructions.parse("DIVIDE BY 2"));
    }
}