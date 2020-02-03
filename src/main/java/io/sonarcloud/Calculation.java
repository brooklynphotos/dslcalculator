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
    public Calculation(final int value, final float[] display) {
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

    public void performOperation(final Instruction instruction) {
        switch (instruction.getOperation()) {
            case ADD:
                this.value += instruction.getOperand().get();
                break;
            case SUBTRACT:
                this.value -= instruction.getOperand().get();
                break;
            case DIVIDE:
                if (instruction.getOperand().get() == 0) {
                    throw new IllegalArgumentException("Can't divide by zero in line " + instruction.getIndex());
                }
                this.value /= instruction.getOperand().get();
                break;
            case MULTIPLY:
                this.value *= instruction.getOperand().get();
                break;
            case DISPLAY:
                final float[] newDisplay = new float[this.display.length + 1];
                System.arraycopy(this.display, 0, newDisplay, 0, this.display.length);
                newDisplay[newDisplay.length - 1] = this.value;
                this.display = newDisplay;
                break;

        }
    }
}
