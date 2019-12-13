package com.github.mutare.adventcalendar2019.day2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.System.arraycopy;

public class ShipComputer {

    public class InputOutput {
        private LinkedBlockingQueue<Long> buffer = new LinkedBlockingQueue<>();

        public void add(long l) {
            buffer.add(l);
        }

        public boolean isEmpty() {
            return buffer.isEmpty();
        }

        public long poll() {
            try {
                return buffer.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public int size() {
            return buffer.size();
        }
    }


    private final AtomicLong base = new AtomicLong(0);
    private final long[] memory = new long[40960];
    private int operaionCount = 0;
    private InputOutput input = new InputOutput();
    private InputOutput output = new InputOutput();

    public long[] proccess(long... program) throws InterruptedException {
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

    public void input(InputOutput input) {
        this.input = input;
    }

    public void input(long... input) {
        for (long value : input) {
            this.input.add(value);
        }
    }

    public InputOutput getOutput() {
        return output;
    }

    public InputOutput getInput() {
        return input;
    }
}
