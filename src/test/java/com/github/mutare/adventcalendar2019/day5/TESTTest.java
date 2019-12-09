package com.github.mutare.adventcalendar2019.day5;

import com.github.mutare.adventcalendar2019.day2.Operation;
import com.github.mutare.adventcalendar2019.day2.ShipComputer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class TESTTest {

    private ShipComputer shipComputer = new ShipComputer();

    @Test
    public void test() throws InterruptedException {
        assertArrayEquals(new int[]{1002, 4, 3, 4, 99}, shipComputer.proccess(1002, 4, 3, 4, 33));
    }

    @Test
    public void hand() throws IOException {
        int[] m = new FileProgramInputSource().getProgram();
        //0,  1 2   3 4 5    6 7   8   9  10 11  12 13 14 15  16 17 18   19   20  21  22  23 24 25
        //3,225,1,225,6,6,1100,1,238,225,104,0,1101,34,7,225,101,17,169,224,1001,224,-92,224,4,224,
        m[225] = 1;//3,225,
        m[5] = m[m[3]] + m[m[4]];//1,225,6,6,
        m[255] = 1 + 238;// 1101,1,238,225
        //m[104]// ,104,0,1101,34,7,225,101,17,169,224,1001,224,-92,224,4,224,

    }

    @Test
    public void operationTest() {
        Operation.of(1101);
    }

    @Test
    public void one() throws IOException, InterruptedException {
        int[] program = new FileProgramInputSource().getProgram();
        shipComputer.input(1);
        shipComputer.proccess(program);

        Collection<Integer> collect = shipComputer.output();
        assertEquals(10, collect.size());
        for(int i = 0 ; i < collect.size() - 1 ; i++) collect.iterator().next();
        assertEquals(13285749, collect.iterator().next().intValue());
        collect.forEach(System.out::println);
    }

    @Test
    public void two() throws IOException, InterruptedException {
        int[] program = new FileProgramInputSource().getProgram();
        shipComputer.input(5);
        shipComputer.proccess(program);

        Collection<Integer> collect = shipComputer.output();
        assertEquals(1, collect.size());
        assertEquals(5000972, collect.iterator().next().intValue());
        collect.forEach(System.out::println);
    }

    @Test
    public void testJumpImmediateMode() throws InterruptedException {
        shipComputer.input(0);
        shipComputer.proccess(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1);
        Collection<Integer> collect0 = shipComputer.output();
        assertEquals(0, collect0.iterator().next().intValue());
        shipComputer.input(1);
        shipComputer.proccess(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1);
        Collection<Integer> collect1 = shipComputer.output();
        assertEquals(1, collect1.iterator().next().intValue());
    }

    @Test
    public void testJumpPoisiotionMode() throws InterruptedException {
        shipComputer.input(0);
        shipComputer.proccess(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9);
        Collection<Integer> collect0 = shipComputer.output();
        assertEquals(0, collect0.iterator().next().intValue());
        shipComputer.input(1);
        shipComputer.proccess(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9);
        Collection<Integer> collect1 = shipComputer.output();
        assertEquals(1, collect1.iterator().next().intValue());
    }

    @Test
    public void testEqPositionMode() throws InterruptedException {
        shipComputer.input(8);
        shipComputer.proccess(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8);
        Collection<Integer> collect0 = shipComputer.output();
        assertEquals(1, collect0.iterator().next().intValue());
        shipComputer.input(7);
        shipComputer.proccess(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8);
        Collection<Integer> collect1 = shipComputer.output();
        assertEquals(0, collect1.iterator().next().intValue());
    }

    @Test
    public void testLessPositionMode() throws InterruptedException {
        shipComputer.input(7);
        shipComputer.proccess(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8);
        Collection<Integer> collect0 = shipComputer.output();
        assertEquals(1, collect0.iterator().next().intValue());
        shipComputer.input(8);
        shipComputer.proccess(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8);
        Collection<Integer> collect1 = shipComputer.output();
        assertEquals(0, collect1.iterator().next().intValue());
    }

    @Test
    public void testEqImmediateMode() throws InterruptedException {
        shipComputer.input(8);
        shipComputer.proccess(3, 3, 1108, -1, 8, 3, 4, 3, 99);
        Collection<Integer> collect0 = shipComputer.output();
        assertEquals(1, collect0.iterator().next().intValue());
        shipComputer.input(7);
        shipComputer.proccess(3, 3, 1108, -1, 8, 3, 4, 3, 99);
        Collection<Integer> collect1 = shipComputer.output();
        assertEquals(0, collect1.iterator().next().intValue());
    }

    @Test
    public void testLessImmediateMode() throws InterruptedException {
        shipComputer.input(7);
        shipComputer.proccess(3, 3, 1107, -1, 8, 3, 4, 3, 99);
        Collection<Integer> collect0 = shipComputer.output();
        assertEquals(1, collect0.iterator().next().intValue());
        shipComputer.input(8);
        shipComputer.proccess(3, 3, 1107, -1, 8, 3, 4, 3, 99);
        Collection<Integer> collect1 = shipComputer.output();
        assertEquals(0, collect1.iterator().next().intValue());
    }

    @Test
    public void testLarge() throws InterruptedException {
        int[] program = {3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99};

        shipComputer.input(7);
        shipComputer.proccess(program);
        Collection<Integer> collect0 = shipComputer.output();
        assertEquals(999, collect0.iterator().next().intValue());
        shipComputer.input(8);
        shipComputer.proccess(program);
        Collection<Integer> collect1 = shipComputer.output();
        assertEquals(1000, collect1.iterator().next().intValue());

        shipComputer.input(9);
        shipComputer.proccess(program);
        Collection<Integer> collect2 = shipComputer.output();
        assertEquals(1001, collect2.iterator().next().intValue());
    }
}
