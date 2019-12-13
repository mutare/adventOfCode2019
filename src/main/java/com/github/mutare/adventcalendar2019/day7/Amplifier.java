package com.github.mutare.adventcalendar2019.day7;

import com.github.mutare.adventcalendar2019.day2.ShipComputer;

class Amplifier extends Thread {
    private final ShipComputer shipComputer;
    private final long[] program;

    public Amplifier(long[] program, int phase) {
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

    public void amplify() {
        this.start();
    }

    void setInput(ShipComputer.InputOutput output) {
        if (this.shipComputer.getInput().size() > 0) {
            while (!output.isEmpty()) {
                this.shipComputer.getInput().add(output.poll());
            }
            while (!this.shipComputer.getInput().isEmpty()) {
                output.add(this.shipComputer.getInput().poll());
            }
        }
        shipComputer.input(output);
    }

    void setInput(int input) {
        shipComputer.input(input);
    }

    public ShipComputer.InputOutput getOutput() {
        return shipComputer.getOutput();
    }
}
