package com.github.mutare.adventcalendar2019.day19;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

public class TractorBeam {
    private final long[] program;

    public TractorBeam(long[] program) {
        this.program = program;
    }

    public int get(long x, long y) {
        IntcodeComputer intcodeComputer;
        intcodeComputer = new IntcodeComputer(program);
        intcodeComputer.proccess(x);
        IntcodeComputer.Result result = intcodeComputer.proccess(y);
        return result.value.intValue();
    }
}
