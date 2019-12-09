package com.github.mutare.adventcalendar2019.day2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class ShipComputer {

    LinkedBlockingQueue<Long> input = new LinkedBlockingQueue<>();
    LinkedBlockingQueue<Long> output = new LinkedBlockingQueue<>();

    private AtomicLong base = new AtomicLong(0);
    private long[] memory = new long[40960];


    public long[] proccess(long ... program) throws InterruptedException {
        System.arraycopy(program, 0, memory, 0, program.length);
        OperationProcessor operationProcessor = new OperationProcessor(memory);

        long i = 0;
        Operation operation;
        while ((operation = operationProcessor.parseOperation(i, base)).type != Operation.OperationType.end) {
            i = operationProcessor.process(operation, i, input, output, base);
        }
        return memory;
    }

    public void input(LinkedBlockingQueue<Long> blockingQueue) {
        this.input = blockingQueue;
    }

    public void input(long ... input) {
        for (long value : input) {
            this.input.add(value);
        }
    }

    public LinkedBlockingQueue<Long> output() {
        return output;
    }

    public LinkedBlockingQueue<Long> getInput() {
        return input;
    }
}
