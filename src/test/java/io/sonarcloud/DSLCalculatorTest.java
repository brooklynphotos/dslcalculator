package io.sonarcloud;

import org.junit.Test;

import static org.junit.Assert.*;

public class DSLCalculatorTest {

    @Test
    public void givenEmptyString_returnEmptyArray() {
        assertArrayEquals(new float[]{}, new DSLCalculator().process(""), 0.001f);
    }

    @Test
    public void givenOneActionOneDisplay_returnArrayOneValue() {
        assertArrayEquals(new float[]{1}, new DSLCalculator().process("ADD 1\nDISPLAY"), 0.001f);
    }

    @Test
    public void givenActionWithNoDisplayAfter_returnEmptyArray() {
        assertArrayEquals(new float[]{}, new DSLCalculator().process("ADD 1"), 0.001f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenDivisionByZero_throwException() {
        assertArrayEquals(new float[]{}, new DSLCalculator().process("ADD 2\nDIVIDE BY 0\nDISPLAY"), 0.001f);
    }

    @Test
    public void givenFullInstruction_returnCorrectResult() {
        final String instr = "ADD 2\nDIVIDE BY 2\nDISPLAY\nMULTIPLY BY 5\nSUBTRACT 1\nDISPLAY";
        final float[] res = new DSLCalculator().process(instr);
        assertArrayEquals(new float[]{1, 4}, res, 0.001f);
    }


}