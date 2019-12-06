package com.github.mutare.adventcalendar2019.day2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

public class TwelveOTwoProgramAlarm {

    private int[] memory;

    public int[] proccess(int ... input) {
        memory = Arrays.copyOf(input, input.length);

        int i = 0;
        while(memory[i] != 99) {
            if (memory[i] == 1) {
                memory[memory[i+3]] = memory[memory[i+1]] + memory[memory[i+2]];
            } else if (memory[i] == 2) {
                memory[memory[i+3]] = memory[memory[i+1]] * memory[memory[i+2]];
            }
            i+=4;
        }
        return memory;
    }
}
