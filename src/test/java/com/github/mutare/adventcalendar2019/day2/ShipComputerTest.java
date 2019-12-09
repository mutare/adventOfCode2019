package com.github.mutare.adventcalendar2019.day2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;

import static java.util.Arrays.copyOf;
import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class ShipComputerTest {

    private ShipComputer shipComputer = new ShipComputer();

    @Test
    public void test() throws InterruptedException {
        assertArrayEquals(new long[]{0, 1, 2, 3}, new long[]{0, 1, 2, 3});
        assertArrayEquals(new long[]{2, 0, 0, 0, 99}, copyOf(shipComputer.proccess(1, 0, 0, 0, 99), 5));
        assertArrayEquals(new long[]{2, 3, 0, 6, 99}, copyOf(shipComputer.proccess(2, 3, 0, 3, 99), 5));
        assertArrayEquals(new long[]{2, 4, 4, 5, 99, 9801}, copyOf(shipComputer.proccess(2, 4, 4, 5, 99, 0), 6));
        assertArrayEquals(new long[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, copyOf(shipComputer.proccess(1, 1, 1, 4, 99, 5, 6, 0, 99), 9));
    }

    @Test
    public void one() throws IOException, InterruptedException {
        long[] program = new FileProgramInputSource().getProgram();
        program[1] = 12;
        program[2] = 2;
        long[] proccess = shipComputer.proccess(program);
        assertEquals(7210630, proccess[0]);
    }

    @Test
    public void two() throws IOException, InterruptedException {
        long[] program = new FileProgramInputSource().getProgram();
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                program[1] = noun;
                program[2] = verb;

                long result = shipComputer.proccess(program)[0];
                if (result == 19690720) {
                    assertEquals(3892, noun * 100 + verb);
                    return;
                }
            }
        }
        assertTrue(false);
    }
}
