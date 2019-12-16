package com.github.mutare.adventcalendar2019.day2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;

import static java.util.Arrays.copyOf;
import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class ShipComputerTest {

    @Test
    public void test() throws InterruptedException {
        assertArrayEquals(new long[]{0, 1, 2, 3}, new long[]{0, 1, 2, 3});
        IntcodeComputer intcodeComputer = new IntcodeComputer(1, 0, 0, 0, 99);
        intcodeComputer.proccess();
        assertArrayEquals(new long[]{2, 0, 0, 0, 99}, copyOf(intcodeComputer.getMemory(), 5));

        intcodeComputer = new IntcodeComputer(2, 3, 0, 3, 99);
        intcodeComputer.proccess();
        assertArrayEquals(new long[]{2, 3, 0, 6, 99}, copyOf(intcodeComputer.getMemory(), 5));

        intcodeComputer = new IntcodeComputer(2, 4, 4, 5, 99, 0);
        intcodeComputer.proccess();
        assertArrayEquals(new long[]{2, 4, 4, 5, 99, 9801}, copyOf(intcodeComputer.getMemory(), 6));

        intcodeComputer = new IntcodeComputer(1, 1, 1, 4, 99, 5, 6, 0, 99);
        intcodeComputer.proccess();
        assertArrayEquals(new long[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, copyOf(intcodeComputer.getMemory(), 9));
    }

    @Test
    public void test2() {
        assertArrayEquals(new long[]{0, 1, 2, 3}, new long[]{0, 1, 2, 3});

        IntcodeComputer intcodeComputer = new IntcodeComputer(1, 0, 0, 0, 99);
        intcodeComputer.proccess();
        assertArrayEquals(new long[]{2, 0, 0, 0, 99}, copyOf(intcodeComputer.getMemory(), 5));

        intcodeComputer = new IntcodeComputer(2, 3, 0, 3, 99);
        intcodeComputer.proccess();
        assertArrayEquals(new long[]{2, 3, 0, 6, 99}, copyOf(intcodeComputer.getMemory(), 5));

        intcodeComputer = new IntcodeComputer(2, 4, 4, 5, 99, 0);
        intcodeComputer.proccess();
        assertArrayEquals(new long[]{2, 4, 4, 5, 99, 9801}, copyOf(intcodeComputer.getMemory(), 6));

        intcodeComputer = new IntcodeComputer(1, 1, 1, 4, 99, 5, 6, 0, 99);
        intcodeComputer.proccess();
        assertArrayEquals(new long[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, copyOf(intcodeComputer.getMemory(), 9));
    }

    @Test
    public void one() throws IOException, InterruptedException {
        long[] program = new FileProgramInputSource().getProgram();
        program[1] = 12;
        program[2] = 2;
        IntcodeComputer intcodeComputer = new IntcodeComputer(program);
        intcodeComputer.proccess();
        long[] proccess = intcodeComputer.getMemory();
        assertEquals(7210630, proccess[0]);
    }

    @Test
    public void two() throws IOException {
        long[] program = new FileProgramInputSource().getProgram();
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                program[1] = noun;
                program[2] = verb;

                IntcodeComputer intcodeComputer = new IntcodeComputer(program);
                intcodeComputer.proccess();
                long result = intcodeComputer.getMemory()[0];
                if (result == 19690720) {
                    assertEquals(3892, noun * 100 + verb);
                    return;
                }
            }
        }
        fail();
    }
}
