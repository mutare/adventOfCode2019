package com.github.mutare.adventcalendar2019.day2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.System.arraycopy;

public class ShipComputer {

    private LinkedBlockingQueue<Long> input = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<Long> output = new LinkedBlockingQueue<>();

    private final AtomicLong base = new AtomicLong(0);
    private final long[] memory = new long[40960];
    private int operaionCount = 0;

    public long[] proccess(long ... program) throws InterruptedException {
        arraycopy(program, 0, memory, 0, program.length);
        OperationProcessor operationProcessor = new OperationProcessor(memory);

        long i = 0;
        Operation operation;
        while ((operation = operationProcessor.parseOperation(i, base)).type != Operation.OperationType.end) {
            i = operationProcessor.process(operation, i, input, output, base);
            operaionCount++;
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

    public LinkedBlockingQueue<Long> getOutput() {
        return output;
    }

    public LinkedBlockingQueue<Long> getInput() {
        return input;
    }
}
