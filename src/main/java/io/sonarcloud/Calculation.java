package io.sonarcloud;

import io.sonarcloud.model.Instruction;

import java.util.Arrays;
import java.util.Objects;

/**
 * holds the current sum and the display information
 */
public class Calculation {
    public Calculation(){
    }
    public Calculation(final float value, final float[] display) {
        this.value = value;
        this.display = display;
    }

    private float value;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Calculation that = (Calculation) o;
        return value == that.value &&
                Arrays.equals(display, that.display);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(value);
        result = 31 * result + Arrays.hashCode(display);
        return result;
    }

    public float getValue() {
        return value;
    }

    public float[] getDisplay() {
        return display;
    }

    private float[] display = new float[]{};

    public Calculation performOperation(final Instruction instruction) {
        switch (instruction.getOperation()) {
            case ADD:
                return Calculation.withNewValue(this, this.value + (float)instruction.getOperand().get());
            case SUBTRACT:
                return Calculation.withNewValue(this, this.value - instruction.getOperand().get());
            case DIVIDE:
                if (instruction.getOperand().get() == 0) {
                    throw new IllegalArgumentException("Can't divide by zero in line " + instruction.getIndex());
                }
                return Calculation.withNewValue(this, this.value / instruction.getOperand().get());
            case MULTIPLY:
                return Calculation.withNewValue(this, this.value * instruction.getOperand().get());
            case DISPLAY:
                return Calculation.withAdditionalDisplay(this);
        }
        throw new IllegalArgumentException("We failed to handle a legitimate operation: " + instruction.getOperation());
    }

    private static Calculation withAdditionalDisplay(final Calculation calculation) {
        final float[] newDisplay = new float[calculation.display.length + 1];
        System.arraycopy(calculation.display, 0, newDisplay, 0, calculation.display.length);
        newDisplay[newDisplay.length - 1] = calculation.value;
        return new Calculation(calculation.value, newDisplay);
    }

    private static Calculation withNewValue(final Calculation calculation, final float v) {
        return new Calculation(v, calculation.display);
    }
}
