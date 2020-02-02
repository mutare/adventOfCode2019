package com.github.mutare.adventcalendar2019.day2;

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.System.arraycopy;

public class IntcodeComputer {

    private final AtomicLong base = new AtomicLong(0);
    private final long[] memory = new long[40960];
    private long index = 0;
    private Long input;

    public IntcodeComputer(long... program) {
        arraycopy(program, 0, memory, 0, program.length);
    }

    public IntcodeComputer(IntcodeComputer computer) {
        this.base.set(computer.base.intValue());
        arraycopy(computer.memory, 0, memory, 0, computer.memory.length);
        this.index = computer.index;
        this.input = computer.input;
    }

    public long[] getMemory() {
        return memory;
    }

    public static class Result {
        public enum Type {END, INPUT, OUTPUT, OK}

        public Result(Type type) {
            this(type, null);
        }

        Result(Type type, Long value) {
            this.value = value;
            this.type = type;
        }

        public Type type;
        public Long value;

        @Override
        public String toString() {
            return "Result{" +
                    "type=" + type +
                    ", value=" + value +
                    '}';
        }
    }

    public Result proccess() {
        return proccess(null);
    }

    public Result proccess(Long input) {
        this.input = input;
        Operation operation;
        while ((operation = parseOperation()).type != Operation.OperationType.end) {
            Result result = process(operation, base);
            if (result.type != Result.Type.OK) {
                return result;
            }
        }
        return new Result(Result.Type.END);
    }

    Operation parseOperation() {
        Operation operation = Operation.of(memory[(int) index]);
        getParameters(operation, index, base);
        return operation;
    }

    Result process(Operation operation, AtomicLong base) {
        switch (operation.type) {
            case add:
                memory[(int) operation.parameters[2]] = operation.parameters[0] + operation.parameters[1];
                break;
            case multiply:
                memory[(int) operation.parameters[2]] = operation.parameters[0] * operation.parameters[1];
                break;
            case input:
                if (input != null) {
                    memory[(int) operation.parameters[0]] = input;
                    input = null;
                } else {
                    return new Result(Result.Type.INPUT);
                }
                break;
            case output:
                skipToNext(operation);
                return new Result(Result.Type.OUTPUT, operation.parametersModes[0] == Operation.OperationMode.position ? memory[(int) operation.parameters[0]] : Operation.OperationMode.relative == operation.parametersModes[0] ? memory[(int) operation.parameters[0]] : operation.parameters[0]);
            case jump_if_true:
                if (operation.parameters[0] != 0) {
                    index = operation.parameters[1];
                    return new Result(Result.Type.OK);
                }
                break;
            case jump_if_false:
                if (operation.parameters[0] == 0) {
                    index = operation.parameters[1];
                    return new Result(Result.Type.OK);
                }
                break;
            case less:
                memory[(int) operation.parameters[2]] = operation.parameters[0] < operation.parameters[1] ? 1 : 0;
                break;
            case eq:
                memory[(int) operation.parameters[2]] = operation.parameters[0] == operation.parameters[1] ? 1 : 0;
                break;
            case adjusts_the_relative_base:
                base.addAndGet(operation.parameters[0]);
                break;
        }
        skipToNext(operation);
        return new Result(Result.Type.OK);
    }


    private void skipToNext(Operation operation) {
        index += operation.type.noOfParameters + 1;
    }


    private void getParameters(Operation operation, long index, AtomicLong base) {

        switch (operation.type) {
            case add:
            case multiply:
            case less:
            case eq:
                operation.parameters[0] = readParameter(index + 1, operation.parametersModes[0], base);
                operation.parameters[1] = readParameter(index + 2, operation.parametersModes[1], base);
                operation.parameters[2] = readParameter(index + 3, operation.parametersModes[2], base, true);
                break;
            case input:
            case output:
                operation.parameters[0] = readParameter(index + 1, operation.parametersModes[0], base, true);
                break;
            case jump_if_true:
            case jump_if_false:
                operation.parameters[0] = readParameter(index + 1, operation.parametersModes[0], base);
                operation.parameters[1] = readParameter(index + 2, operation.parametersModes[1], base);
                break;
            case adjusts_the_relative_base:
                operation.parameters[0] = readParameter(index + 1, operation.parametersModes[0], base);
                break;
        }
    }

    private long readParameter(long index, Operation.OperationMode parametersMode, AtomicLong base) {
        return readParameter(index, parametersMode, base, false);
    }

    private long readParameter(long index, Operation.OperationMode parametersMode, AtomicLong base, boolean indexing) {
        switch (parametersMode) {
            case immediate:
                return memory[(int) index];
            case relative:
                return indexing ? ((int) memory[(int) index] + (int) base.get()) : memory[(int) memory[(int) index] + (int) base.get()];
            case position:
                return indexing ? memory[(int) index] : memory[(int) memory[(int) index]];
            default:
                throw new RuntimeException();
        }
    }

    public void reset() {
        index = 0;
    }
}
