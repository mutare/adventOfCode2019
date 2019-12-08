package com.github.mutare.adventcalendar2019.day2;

import java.util.Arrays;
import java.util.stream.Stream;

public class ShipComputer {

    private int[] memory;
    private int input;
    private Stream.Builder<Integer> outputBuilder = Stream.builder();


    public int[] proccess(int... program) {
        outputBuilder = Stream.builder();
        OperationProcessor operationProcessor = new OperationProcessor(memory = Arrays.copyOf(program, program.length));

        int i = 0;
        Operation operation;
        while ((operation = operationProcessor.parseOperation(i)).type != Operation.OperationType.end) {
            i =  operationProcessor.process(operation, i, this.input, outputBuilder);
        }
        return memory;
    }

    public void input(int input) {
        outputBuilder = Stream.builder();
        this.input = input;
    }

    private void output(int out) {
        outputBuilder.add(out);
    }

    public Stream<Integer> output() {
        return outputBuilder.build();
    }
}
