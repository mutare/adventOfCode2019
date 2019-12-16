package com.github.mutare.adventcalendar2019.day7;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.mutare.adventcalendar2019.day2.IntcodeComputer.Result.Type.END;
import static com.github.mutare.adventcalendar2019.day2.IntcodeComputer.Result.Type.OUTPUT;
import static org.junit.Assert.assertEquals;

public class AmplificationCircuitTest {

    private final List<int[]> permutations = new ArrayList<>();

    @Test
    public void testSimpleAmplifier2() {
        //43210   4,3,2,1,0
        IntcodeComputer intcodeComputer = new IntcodeComputer(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0);

        intcodeComputer.proccess(4L);
        IntcodeComputer.Result r = intcodeComputer.proccess(0L);
        assertEquals(4L, r.value.longValue());
    }

    @Test
    public void testSingleAmplifierSeries() throws InterruptedException {
        long[] program = new long[]{3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0};

        AmplifierSeries amplifierSeries = new AmplifierSeries(program, false, 4);
        amplifierSeries.amplify();
        assertEquals(4, amplifierSeries.getValue().longValue());
    }

    @Test
    public void testAmplifierSeries2_2() throws InterruptedException {

        long[] input = new long[]{4, 3, 2, 1, 0};

        Long value = 0L;
        for (long i : input) {
            IntcodeComputer intcodeComputer = new IntcodeComputer(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0);
            intcodeComputer.proccess(i);
            value = intcodeComputer.proccess(value).value;
        }

        assertEquals(43210, value.longValue());
    }

    @Test
    public void testAmplifierSeries2() throws InterruptedException {
        long[] program = new long[]{3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23,
                101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0};
        AmplifierSeries amplifierSeries = new AmplifierSeries(program, false, 0, 1, 2, 3, 4);
        amplifierSeries.amplify();
        assertEquals(54321, amplifierSeries.getValue().longValue());
    }

    @Test
    public void testAmplifierSeries3() throws InterruptedException {
        long[] program = new long[]{3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
                1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0};
        AmplifierSeries amplifierSeries = new AmplifierSeries(program, false, 1, 0, 4, 3, 2);
        amplifierSeries.amplify();
        assertEquals(65210, amplifierSeries.getValue().longValue());
    }

    @Test
    public void one() throws InterruptedException {
        int[] a = {0, 1, 2, 3, 4};
        permutations.clear();
        generatePermutations(0, a);

        long max = -1;

        for (int[] phases : permutations) {
            long[] program = new long[]{3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 46, 67, 76, 97, 118, 199, 280, 361, 442, 99999, 3, 9, 1002, 9, 3, 9, 101, 4, 9, 9, 102, 3, 9, 9, 1001, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 101, 5, 9, 9, 1002, 9, 2, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 101, 4, 9, 9, 4, 9, 99, 3, 9, 1001, 9, 4, 9, 102, 2, 9, 9, 1001, 9, 4, 9, 1002, 9, 5, 9, 4, 9, 99, 3, 9, 102, 3, 9, 9, 1001, 9, 2, 9, 1002, 9, 3, 9, 1001, 9, 3, 9, 4, 9, 99, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99};
            AmplifierSeries amplifierSeries = new AmplifierSeries(program, false, phases);
            amplifierSeries.amplify();
            long r = amplifierSeries.getValue().longValue();
            if (r > max) {
                max = r;
            }
        }

        assertEquals(67023, max);
    }

    private void generatePermutations(int k, int[] a) {
        if (k == a.length) {
            //Arrays.stream(a).forEach(System.out::print);System.out.println();
            permutations.add(a);
        }
        for (int i = k; i < a.length; i++) {
            int[] c = Arrays.copyOf(a, a.length);
            int x = c[i];
            c[i] = c[k];
            c[k] = x;
            generatePermutations(k + 1, c);
        }
    }

    @Test
    public void test2() {
        long[] program = new long[]{3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5};

        int exit = 0;
        long[] phases = new long[]{9, 8, 7, 6, 5};
        Long value = 0L;

        List<IntcodeComputer> computers = new ArrayList<>();

        for (long i : phases) {
            IntcodeComputer intcodeComputer = new IntcodeComputer(program);
            intcodeComputer.proccess(i);
            computers.add(intcodeComputer);
        }

        do {
            for (IntcodeComputer intcodeComputer : computers) {
                IntcodeComputer.Result proccess = intcodeComputer.proccess(value);
                if (proccess.type == END) {
                    exit++;
                } else if (proccess.type == OUTPUT) {
                    value = proccess.value;
                }
            }
        } while (exit < phases.length);

        assertEquals(139629729, value.longValue());
    }

    @Test
    public void test3() throws InterruptedException {
        int[] a = {9, 7, 8, 5, 6};

        long[] program = new long[]{3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54,
                -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4,
                53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10};
        AmplifierSeries amplifierSeries = new AmplifierSeries(program, true, a);
        amplifierSeries.amplify();
        long r = amplifierSeries.getValue().longValue();

        assertEquals(18216, r);
    }

    @Test
    public void two() throws InterruptedException {
        int[] a = {9, 8, 7, 6, 5};
        permutations.clear();
        generatePermutations(0, a);

        long max = -1;

        for (int[] phases : permutations) {
            long[] program = new long[]{3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 46, 67, 76, 97, 118, 199, 280, 361, 442, 99999, 3, 9, 1002, 9, 3, 9, 101, 4, 9, 9, 102, 3, 9, 9, 1001, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 101, 5, 9, 9, 1002, 9, 2, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 101, 4, 9, 9, 4, 9, 99, 3, 9, 1001, 9, 4, 9, 102, 2, 9, 9, 1001, 9, 4, 9, 1002, 9, 5, 9, 4, 9, 99, 3, 9, 102, 3, 9, 9, 1001, 9, 2, 9, 1002, 9, 3, 9, 1001, 9, 3, 9, 4, 9, 99, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99};
            AmplifierSeries amplifierSeries = new AmplifierSeries(program, true, phases);
            amplifierSeries.amplify();
            long r = amplifierSeries.getValue().longValue();
            if (r > max) {
                max = r;
            }
        }

        assertEquals(7818398, max);
    }
}
