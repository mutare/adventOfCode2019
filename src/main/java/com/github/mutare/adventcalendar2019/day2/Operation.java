package com.github.mutare.adventcalendar2019.day2;

import java.util.stream.Stream;

public class Operation {
    public static Operation of(long operationValue) {
        Operation operation = new Operation();
        operation.type = OperationType.valueOf((int)operationValue % 100);
        String operationValueModes = String.format("%1$" + 3 + "s", Integer.toString((int)operationValue / 100)).replace(' ', '0');
        operation.parametersModes[0] = OperationMode.valueOf(Integer.parseInt(operationValueModes.substring(2, 3)));
        operation.parametersModes[1] = OperationMode.valueOf(Integer.parseInt(operationValueModes.substring(1, 2)));
        operation.parametersModes[2] = OperationMode.valueOf(Integer.parseInt(operationValueModes.substring(0, 1)));
        return operation;
    }


    enum OperationMode {
        position,
        immediate,
        relative;

        static OperationMode valueOf(int i) {
            switch (i) {
                case 0 : return OperationMode.position;
                case 1 : return OperationMode.immediate;
                case 2 : return OperationMode.relative;
                default:
                    throw new RuntimeException("No such mode");
            }
        }
    }

    enum OperationType {
        add(1, 3),
        multiply(2, 3),
        input(3, 1),
        output(4, 1),
        jump_if_true(5, 2),
        jump_if_false(6, 2),
        less(7, 3),
        eq(8, 3),
        adjusts_the_relative_base(9, 1),

        end(99, 0);

        OperationType(int code, int noOfParameters) {
            this.code = code;
            this.noOfParameters = noOfParameters;
        }

        final int code;
        final int noOfParameters;

        static OperationType valueOf(int i) {
            return Stream.of(values())
                    .filter(operation -> operation.code == i)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("No such (%d) OperationType", i)));
        }
    }

    OperationType type;
    final OperationMode[] parametersModes = new OperationMode[3];
    final long[] parameters = new long[3];
}
