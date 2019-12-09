package com.github.mutare.adventcalendar2019.day2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static com.github.mutare.adventcalendar2019.day2.Operation.OperationMode.immediate;
import static com.github.mutare.adventcalendar2019.day2.Operation.OperationType.*;
import static java.util.Arrays.asList;

class OperationProcessor {
    private long[] memory;

    OperationProcessor(long[] memory) {
        this.memory = memory;
    }

    long process(Operation operation, long index, LinkedBlockingQueue<Long> input, LinkedBlockingQueue<Long> output, AtomicLong base) throws InterruptedException {
        if (operation.type == Operation.OperationType.add) {
            memory[(int) operation.parameters[2]] = operation.parameters[0] + operation.parameters[1];
        } else if (operation.type == multiply) {
            memory[(int) operation.parameters[2]] = operation.parameters[0] * operation.parameters[1];
        } else if (operation.type == Operation.OperationType.input) {
            memory[(int) operation.parameters[0]] = input.take();
        } else if (operation.type == Operation.OperationType.output) {
            output.add(operation.parametersModes[0] == Operation.OperationMode.position ? memory[(int) operation.parameters[0]] : operation.parameters[0]);
        } else if (operation.type == Operation.OperationType.jump_if_true) {
            if (operation.parameters[0] != 0) {
                return operation.parameters[1];
            }
        }
        if (operation.type == Operation.OperationType.jump_if_false) {
            if (operation.parameters[0] == 0) {
                return operation.parameters[1];
            }
        }
        if (operation.type == Operation.OperationType.less) {
            memory[(int) operation.parameters[2]] = operation.parameters[0] < operation.parameters[1] ? 1 : 0;
        }
        if (operation.type == Operation.OperationType.eq) {
            memory[(int) operation.parameters[2]] = operation.parameters[0] == operation.parameters[1] ? 1 : 0;
        }
        if (operation.type == adjusts_the_relative_base) {
            base.addAndGet(operation.parameters[0]);
        }
        return skipToNext(operation, index);
    }


    private long skipToNext(Operation operation, long i) {
        return i + operation.type.noOfParameters + 1;
    }

    Operation parseOperation(long i, AtomicLong base) {
        Operation operation = Operation.of(memory[(int) i]);
        getParameters(operation, i, base);
        return operation;
    }

    private void getParameters(Operation operation, long index, AtomicLong base) {

        switch (operation.type) {
            case add :
            case multiply:
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
            case less:
            case eq:
                operation.parameters[0] = readParameter(index + 1, operation.parametersModes[0], base);
                operation.parameters[1] = readParameter(index + 2, operation.parametersModes[1], base);
                operation.parameters[2] = readParameter(index + 3, operation.parametersModes[2], base, true);
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
        switch(parametersMode) {
            case immediate:
                return memory[(int) index];
            case relative:
                return indexing ? (int) memory[(int)index]+ base.get() : memory[(int) memory[(int) (index)] + (int)base.get()];
            case position:
                return indexing ? memory[(int) index] : memory[(int) memory[(int)index]];
            default:
                throw new RuntimeException();
        }
    }
}
