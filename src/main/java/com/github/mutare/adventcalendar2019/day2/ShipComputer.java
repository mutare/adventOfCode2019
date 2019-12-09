package com.github.mutare.adventcalendar2019.day2;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

public class ShipComputer {

    LinkedBlockingQueue<Integer> input = new LinkedBlockingQueue<>();
    LinkedBlockingQueue<Integer> output = new LinkedBlockingQueue<>();


    private int[] memory;


    public int[] proccess(int... program) throws InterruptedException {
        OperationProcessor operationProcessor = new OperationProcessor(memory = Arrays.copyOf(program, program.length));

        int i = 0;
        Operation operation;
        while ((operation = operationProcessor.parseOperation(i)).type != Operation.OperationType.end) {
            i = operationProcessor.process(operation, i, input, output);
        }
        return memory;
    }

    public void input(LinkedBlockingQueue<Integer> blockingQueue) {
        this.input = blockingQueue;
    }

    public void input(int... input) {
        for (int value : input) {
            this.input.add(value);
        }
    }

    public LinkedBlockingQueue<Integer> output() {
        return output;
    }

    public LinkedBlockingQueue<Integer> getInput() {
        return input;
    }
}
