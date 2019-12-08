package com.github.mutare.adventcalendar2019.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Operation {
    public static Operation of(int operationValue) {
        Operation operation = new Operation();
        operation.type = OperationType.valueOf(operationValue % 100);
        String operationValueModes = String.format("%1$" + 3 + "s", Integer.toString(operationValue / 100)).replace(' ', '0');
        operation.parametersModes[0] = OperationMode.valueOf(Integer.parseInt(operationValueModes.substring(2,3)));
        operation.parametersModes[1] = OperationMode.valueOf(Integer.parseInt(operationValueModes.substring(1,2)));
        operation.parametersModes[2] = OperationMode.valueOf(Integer.parseInt(operationValueModes.substring(0,1)));
        return operation;
    }


    enum OperationMode {
        position,
        immediate;

        public static OperationMode valueOf(int i) {
            return i == 1 ? OperationMode.immediate : OperationMode.position;
        }
    }

    enum OperationType {
        add(1, 3),
        multiply(2, 3),
        input(3, 1),
        output(4, 1),
        jump_if_true(5,2),
        jump_if_false(6,2),
        less(7, 3),
        eq(8, 3),

        end(99, 0);

        OperationType(int code, int noOfParameters) {
            this.code = code;
            this.noOfParameters = noOfParameters;
        }

        int code;
        int noOfParameters;

        public static OperationType valueOf(int i) {
            return Stream.of(values())
                    .filter(operation -> operation.code == i)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("No such (%d) OperationType", i)));
        }
    }

    OperationType type;
    OperationMode[] parametersModes = new OperationMode[3];
    int[] parameters = new int[3];
}
