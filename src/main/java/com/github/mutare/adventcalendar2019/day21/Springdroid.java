package com.github.mutare.adventcalendar2019.day21;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

public class Springdroid {
    private IntcodeComputer intcodeComputer;
    private int result;

    public Springdroid(long[] program) {
        this.intcodeComputer = new IntcodeComputer(program);
    }

    public String output() {
        StringBuilder output = new StringBuilder();
        IntcodeComputer.Result result;
        while((result = this.intcodeComputer.proccess()).type == IntcodeComputer.Result.Type.OUTPUT) {
            if (result.value.intValue() > 255) {
                this.result = result.value.intValue();
            } else {
                output.append((char) result.value.intValue());
            }
        }
        return output.toString();
    }

    public void input(String codeLine) {
        IntcodeComputer.Result result = null;
        for (char c : codeLine.toCharArray()) {
            result = this.intcodeComputer.proccess((long) c);
        }
    }

    public int getResult() {
        return result;
    }
}
