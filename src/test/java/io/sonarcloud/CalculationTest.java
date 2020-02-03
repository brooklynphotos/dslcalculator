package io.sonarcloud;

import io.sonarcloud.model.Instruction;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculationTest {

    @Test
    public void givenAdd_performOperationReturnsUpdatedValue() {
        Calculation calculation = new Calculation();
        calculation.performOperation(new Instruction(0, Instruction.Operation.ADD, 2));
        assertEquals(new Calculation(2, new float[]{}), calculation);
    }
}