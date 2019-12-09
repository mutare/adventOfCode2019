package com.github.mutare.adventcalendar2019.day7;

import com.github.mutare.adventcalendar2019.day2.ShipComputer;

import java.util.concurrent.LinkedBlockingQueue;

public class Amplifier extends Thread {
    private ShipComputer shipComputer;
    private int phase;
    private long[] program;

    public Amplifier(long[] program, int phase) {
        this.phase = phase;
        this.program = program;
        this.shipComputer = new ShipComputer();
        this.shipComputer.input(phase);
    }

    @Override
    public void run() {
        try {
            shipComputer.proccess(program);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void amplify() throws InterruptedException {
        this.start();
    }

    void setInput(LinkedBlockingQueue<Long> input) {
        if (this.shipComputer.getInput().size() > 0) {
            while(!input.isEmpty()) {
                this.shipComputer.getInput().add(input.poll());
            }
            while(!this.shipComputer.getInput().isEmpty()) {
                input.add(this.shipComputer.getInput().poll());
            }
        }
        shipComputer.input(input);
    }

    void setInput(int input) {
        shipComputer.input(input);
    }

    public LinkedBlockingQueue<Long> getOutput() {
        return shipComputer.output();
    }
}
