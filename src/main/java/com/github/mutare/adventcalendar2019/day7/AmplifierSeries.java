package com.github.mutare.adventcalendar2019.day7;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

import java.util.ArrayList;
import java.util.List;

import static com.github.mutare.adventcalendar2019.day2.IntcodeComputer.Result.Type.END;
import static com.github.mutare.adventcalendar2019.day2.IntcodeComputer.Result.Type.OUTPUT;

class AmplifierSeries {
    List<IntcodeComputer> computers = new ArrayList<>();
    int exit;
    Long value = 0L;

    public AmplifierSeries(long[] program, boolean feedbackLoop, int... phases) {
        for (long i : phases) {
            IntcodeComputer intcodeComputer = new IntcodeComputer(program);
            intcodeComputer.proccess(i);
            computers.add(intcodeComputer);
        }
        exit = feedbackLoop ? 0 : phases.length;
    }

    public void amplify() {
        do {
            for (IntcodeComputer intcodeComputer : computers) {
                IntcodeComputer.Result proccess = intcodeComputer.proccess(value);
                if (proccess.type == END) {
                    exit++;
                } else if (proccess.type == OUTPUT) {
                    value = proccess.value;
                }
            }
        } while (exit < computers.size());
    }

    public Long getValue() {
        return value;
    }
}
