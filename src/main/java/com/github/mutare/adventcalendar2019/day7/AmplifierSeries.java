package com.github.mutare.adventcalendar2019.day7;

import com.github.mutare.adventcalendar2019.day2.ShipComputer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class AmplifierSeries {

    private final List<Amplifier> amplifiers = new LinkedList<>();
    private final ShipComputer.InputOutput output;

    public AmplifierSeries(long[] program, boolean feedbackLoop, int... phases) {
        for (int phase : phases) {
            amplifiers.add(new Amplifier(program, phase));
        }

        amplifiers.get(0).setInput(0);

        for (int i = 0; i < amplifiers.size() - 1; i++) {
            amplifiers.get(i + 1).setInput(amplifiers.get(i).getOutput());
        }

        if (feedbackLoop) {
            amplifiers.get(0).setInput(amplifiers.get(amplifiers.size() - 1).getOutput());
        }

        output = amplifiers.get(amplifiers.size() - 1).getOutput();
    }

//    public LinkedBlockingQueue<Long> getOutput() {
//        return output;
//    }

    public void amplify() throws InterruptedException {
        for (Amplifier amplifier : amplifiers) {
            amplifier.amplify();
        }
        for (Amplifier amplifier : amplifiers) {
            amplifier.join();
        }
    }


    public ShipComputer.InputOutput getOutput() {
        return output;
    }
}
