package io.sonarcloud.model;

import java.util.Objects;
import java.util.Optional;

public class Instruction {
    public enum Operation{
        ADD("ADD"),
        SUBTRACT("SUBTRACT"),
        DIVIDE("DIVIDE BY"),
        MULTIPLY("MULTIPLY BY"),
        DISPLAY("DISPLAY");

        private final String label;

        Operation(final String label) {
            this.label = label;
        }

        public static Operation parse(final String strForm) {
            for (Operation op : values()) {
                if (op.label.equals(strForm)) {
                    return op;
                }
            }
            throw new IllegalArgumentException(strForm + " does not translate to any operation");
        }
    }

    public Instruction(final long index, final Operation operation, final int operand) {
        this(index, operation, Optional.of(operand));
    }
    public Instruction(final long index, final Operation operation, final Optional<Integer> operand) {
        this.operation = operation;
        this.operand = operand;
        this.index = index;
    }

    public long getIndex() {
        return index;
    }

    private Operation operation;
    private Optional<Integer> operand;
    private long index;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Instruction that = (Instruction) o;
        return index == that.index &&
                operation == that.operation &&
                Objects.equals(operand, that.operand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, operand, index);
    }

    public Optional<Integer> getOperand() {
        return operand;
    }

    public Operation getOperation() {
        return operation;
    }
}
