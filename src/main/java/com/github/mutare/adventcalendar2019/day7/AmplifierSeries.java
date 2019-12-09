package com.github.mutare.adventcalendar2019.day7;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class AmplifierSeries {

    List<Amplifier> amplifiers = new LinkedList<>();
    private LinkedBlockingQueue<Integer> output;

    public AmplifierSeries(int[] program, boolean feedbackLoop, int... phases) {
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

    public LinkedBlockingQueue<Integer> getOutput() {
        return output;
    }

    public void amplify() throws InterruptedException {
        for (Amplifier amplifier : amplifiers) {
            amplifier.amplify();
        }
        for (Amplifier amplifier : amplifiers) {
            amplifier.join();
        }
    }


}
