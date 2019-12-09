package com.github.mutare.adventcalendar2019.day2;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

import static com.github.mutare.adventcalendar2019.day2.Operation.OperationType.*;
import static java.util.Arrays.asList;

class OperationProcessor {
    private int[] memory;

    OperationProcessor(int[] memory) {
        this.memory = memory;
    }

    int process(Operation operation, int index, LinkedBlockingQueue<Integer> input, LinkedBlockingQueue<Integer> output) throws InterruptedException {
        if (operation.type == Operation.OperationType.add) {
            memory[operation.parameters[2]] = operation.parameters[0] + operation.parameters[1];
        } else if (operation.type == multiply) {
            memory[operation.parameters[2]] = operation.parameters[0] * operation.parameters[1];
        } else if (operation.type == Operation.OperationType.input) {
            memory[operation.parameters[0]] = input.take();
        } else if (operation.type == Operation.OperationType.output) {
            output.add(operation.parametersModes[0] == Operation.OperationMode.position ? memory[operation.parameters[0]] : operation.parameters[0]);
        } else if (operation.type == Operation.OperationType.jump_if_true) {
            if (/*operation.parametersModes[0] == Operation.OperationMode.immediate && */operation.parameters[0] != 0) {
                return operation.parameters[1];
            }
        }
        if (operation.type == Operation.OperationType.jump_if_false) {
            if (/*operation.parametersModes[0] == Operation.OperationMode.immediate && */operation.parameters[0] == 0) {
                return operation.parameters[1];
            }
        }
        if (operation.type == Operation.OperationType.less) {
            memory[operation.parameters[2]] = operation.parameters[0] < operation.parameters[1] ? 1 : 0;
        }
        if (operation.type == Operation.OperationType.eq) {
            memory[operation.parameters[2]] = operation.parameters[0] == operation.parameters[1] ? 1 : 0;
        }
        return skipToNext(operation, index);
    }




    private int skipToNext(Operation operation, int i) {
        return i + operation.type.noOfParameters + 1;
    }

    Operation parseOperation(int i) {
        Operation operation = Operation.of(memory[i]);
        getParameters(operation, i);
        return operation;
    }

    private void getParameters(Operation operation, int index) {
        for (int i = 0; i < operation.type.noOfParameters - 1; i++) {
            int parameterValue;
            if (operation.parametersModes[i] == Operation.OperationMode.position && operation.type != Operation.OperationType.input) {
                parameterValue = memory[memory[index + i + 1]];
            } else {
                parameterValue = memory[index + i + 1];
            }
            operation.parameters[i] = parameterValue;
        }
        if (asList(add, multiply, input, output, eq, less, jump_if_false, jump_if_true).contains(operation.type)) {
            operation.parameters[operation.type.noOfParameters - 1] = memory[index + operation.type.noOfParameters];
        }
        if (asList(jump_if_false, jump_if_true).contains(operation.type) && operation.parametersModes[operation.type.noOfParameters -1] == Operation.OperationMode.position) {
            operation.parameters[operation.type.noOfParameters - 1] = memory[memory[index + operation.type.noOfParameters]];
        }
    }
}
