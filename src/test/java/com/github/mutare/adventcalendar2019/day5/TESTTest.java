package com.github.mutare.adventcalendar2019.day5;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;
import com.github.mutare.adventcalendar2019.day2.Operation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.github.mutare.adventcalendar2019.day2.IntcodeComputer.Result.Type.END;
import static com.github.mutare.adventcalendar2019.day2.IntcodeComputer.Result.Type.OUTPUT;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class TESTTest {

    @Test
    public void test() throws InterruptedException {
        IntcodeComputer intcodeComputer = new IntcodeComputer(1002, 4, 3, 4, 33);
        intcodeComputer.proccess();
        assertArrayEquals(new long[]{1002, 4, 3, 4, 99}, copyOf(intcodeComputer.getMemory(), 5));
    }

    @Test
    public void hand() throws IOException {
        long[] m = new FileProgramInputSource().getProgram();
        //0,  1 2   3 4 5    6 7   8   9  10 11  12 13 14 15  16 17 18   19   20  21  22  23 24 25
        //3,225,1,225,6,6,1100,1,238,225,104,0,1101,34,7,225,101,17,169,224,1001,224,-92,224,4,224,
        m[225] = 1;//3,225,
        m[5] = m[(int) m[3]] + m[(int) m[4]];//1,225,6,6,
        m[255] = 1 + 238;// 1101,1,238,225
        //m[104]// ,104,0,1101,34,7,225,101,17,169,224,1001,224,-92,224,4,224,

    }

    @Test
    public void operationTest() {
        Operation.of(1101);
    }

    @Test
    public void one() throws IOException, InterruptedException {
        IntcodeComputer intcodeComputer = new IntcodeComputer(new FileProgramInputSource().getProgram());
        intcodeComputer.proccess(1L);
        IntcodeComputer.Result result;
        List<Long> collect = new LinkedList<>();
        while (asList(OUTPUT).contains((result = intcodeComputer.proccess()).type)) {
            collect.add(result.value);
        }

        assertEquals(13285749, collect.get(collect.size() - 1).longValue());
    }

    @Test
    public void two() throws IOException, InterruptedException {
        IntcodeComputer intcodeComputer = new IntcodeComputer(new FileProgramInputSource().getProgram());
        assertEquals(5000972, intcodeComputer.proccess(5L).value.longValue());
    }

    @Test
    public void testJumpImmediateMode() throws InterruptedException {
        IntcodeComputer shipComputer = new IntcodeComputer(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1);
        assertEquals(0, shipComputer.proccess(0L).value.longValue());

        shipComputer = new IntcodeComputer(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1);
        assertEquals(1, shipComputer.proccess(1L).value.longValue());
    }

    @Test
    public void testJumpPoisiotionMode() throws InterruptedException {
        IntcodeComputer shipComputer = new IntcodeComputer(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9);
        assertEquals(0, shipComputer.proccess(0L).value.longValue());

        shipComputer = new IntcodeComputer(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9);
        assertEquals(1, shipComputer.proccess(1L).value.longValue());
    }

    @Test
    public void testEqPositionMode() throws InterruptedException {
        IntcodeComputer shipComputer = new IntcodeComputer(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8);
        assertEquals(1, shipComputer.proccess(8L).value.longValue());

        shipComputer = new IntcodeComputer(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8);
        assertEquals(0, shipComputer.proccess(7L).value.longValue());
    }

    @Test
    public void testLessPositionMode() throws InterruptedException {
        IntcodeComputer shipComputer = new IntcodeComputer(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8);
        assertEquals(1, shipComputer.proccess(7L).value.longValue());

        shipComputer = new IntcodeComputer(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8);
        assertEquals(0, shipComputer.proccess(8L).value.longValue());
    }

    @Test
    public void testEqImmediateMode() throws InterruptedException {
        IntcodeComputer shipComputer = new IntcodeComputer(3, 3, 1108, -1, 8, 3, 4, 3, 99);
        assertEquals(1, shipComputer.proccess(8L).value.longValue());

        shipComputer = new IntcodeComputer(3, 3, 1108, -1, 8, 3, 4, 3, 99);
        assertEquals(0, shipComputer.proccess(7L).value.longValue());
    }

    @Test
    public void testLessImmediateMode() throws InterruptedException {
        IntcodeComputer shipComputer = new IntcodeComputer(3, 3, 1107, -1, 8, 3, 4, 3, 99);
        assertEquals(1, shipComputer.proccess(7L).value.longValue());

        shipComputer = new IntcodeComputer(3, 3, 1107, -1, 8, 3, 4, 3, 99);
        assertEquals(0, shipComputer.proccess(8L).value.longValue());
    }

    @Test
    public void testLarge() throws InterruptedException {
        IntcodeComputer shipComputer = new IntcodeComputer(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99);
        assertEquals(999, shipComputer.proccess(7L).value.longValue());

        shipComputer = new IntcodeComputer(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99);
        assertEquals(1000, shipComputer.proccess(8L).value.longValue());

        shipComputer = new IntcodeComputer(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99);
        assertEquals(1001, shipComputer.proccess(9L).value.longValue());
    }
}
