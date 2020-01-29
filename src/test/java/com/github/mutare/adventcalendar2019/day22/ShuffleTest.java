package com.github.mutare.adventcalendar2019.day22;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ShuffleTest {

    @Test
    public void test0_dealIntoNewStack() {
        Shuffle shuffle = new Shuffle(10, asList("deal into new stack"));
        shuffle.shuffle();
        assertEquals(asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0), shuffle.getDeck());
    }

    @Test
    public void test0_cutNPositive() {
        Shuffle shuffle = new Shuffle(10, asList("cut 3"));
        shuffle.shuffle();
        assertEquals(asList(3, 4, 5, 6, 7, 8, 9, 0, 1, 2), shuffle.getDeck());
    }

    @Test
    public void test0_cutNNegative() {
        Shuffle shuffle = new Shuffle(10, asList("cut -4"));
        shuffle.shuffle();
        assertEquals(asList(6, 7, 8, 9, 0, 1, 2, 3, 4, 5), shuffle.getDeck());
    }

    @Test
    public void test0_dealWithIncrementN() {
        Shuffle shuffle = new Shuffle(10, asList("deal with increment 3"));
        shuffle.shuffle();
        assertEquals(asList(0, 7, 4, 1, 8, 5, 2, 9, 6, 3), shuffle.getDeck());
    }


    @Test
    public void test1() {
        Shuffle shuffle = new Shuffle(10, asList("deal with increment 7", "deal into new stack", "deal into new stack"));
        shuffle.shuffle();
        assertEquals(asList(0, 3, 6, 9, 2, 5, 8, 1, 4, 7), shuffle.getDeck());
    }

    @Test
    public void test2() {
        Shuffle shuffle = new Shuffle(10, asList("cut 6", "deal with increment 7", "deal into new stack"));
        shuffle.shuffle();
        assertEquals(asList(3, 0, 7, 4, 1, 8, 5, 2, 9, 6), shuffle.getDeck());
    }

    @Test
    public void test3() {
        Shuffle shuffle = new Shuffle(10, asList("deal with increment 7", "deal with increment 9", "cut -2"));
        shuffle.shuffle();
        assertEquals(asList(6, 3, 0, 7, 4, 1, 8, 5, 2, 9), shuffle.getDeck());
    }

    @Test
    public void test4() {
        Shuffle shuffle = new Shuffle(10, asList(
                "deal into new stack",
                "cut -2",
                "deal with increment 7",
                "cut 8",
                "cut -4",
                "deal with increment 7",
                "cut 3",
                "deal with increment 9",
                "deal with increment 3",
                "cut -1"

        ));
        shuffle.shuffle();
        assertEquals(asList(9, 2, 5, 8, 1, 4, 7, 0, 3, 6), shuffle.getDeck());
    }

    @Test
    public void test5_dealIntoNewStack() {
        Shuffle shuffle = new Shuffle(10, asList("deal into new stack"));
        //9, 8, 7, 6, 5, 4, 3, 2, 1, 0
        assertEquals(9, shuffle.getPositionOf(0, 1));
        assertEquals(3, shuffle.getPositionOf(6, 1));
    }

    @Test
    public void test5_cutNPositive() {
        Shuffle shuffle = new Shuffle(10, asList("cut 3"));
        //3, 4, 5, 6, 7, 8, 9, 0, 1, 2
        assertEquals(0, shuffle.getPositionOf(3, 1));
        assertEquals(1, shuffle.getPositionOf(4, 1));
        assertEquals(2, shuffle.getPositionOf(5, 1));
        assertEquals(3, shuffle.getPositionOf(6, 1));
        assertEquals(4, shuffle.getPositionOf(7, 1));
        assertEquals(5, shuffle.getPositionOf(8, 1));
        assertEquals(6, shuffle.getPositionOf(9, 1));
        assertEquals(7, shuffle.getPositionOf(0, 1));
        assertEquals(8, shuffle.getPositionOf(1, 1));
        assertEquals(9, shuffle.getPositionOf(2, 1));

    }

    @Test
    public void test5_cutNNegative() {
        Shuffle shuffle = new Shuffle(10, asList("cut -4"));
        //6, 7, 8, 9, 0, 1, 2, 3, 4, 5
        //assertEquals(1, shuffle.getPositionOf(6, 1));
        assertEquals(0, shuffle.getPositionOf(6, 1));
        assertEquals(1, shuffle.getPositionOf(7, 1));
        assertEquals(2, shuffle.getPositionOf(8, 1));
        assertEquals(3, shuffle.getPositionOf(9, 1));
        assertEquals(4, shuffle.getPositionOf(0, 1));
        assertEquals(5, shuffle.getPositionOf(1, 1));
        assertEquals(6, shuffle.getPositionOf(2, 1));
        assertEquals(7, shuffle.getPositionOf(3, 1));
        assertEquals(8, shuffle.getPositionOf(4, 1));
        assertEquals(9, shuffle.getPositionOf(5, 1));
    }

    @Test
    public void test5_dealWithIncrementN() {
        Shuffle shuffle = new Shuffle(10, asList("deal with increment 3"));
        //0, 7, 4, 1, 8, 5, 2, 9, 6, 3
        assertEquals(2, shuffle.getPositionOf(4, 1));
    }

    @Test
    public void test6_0() {
        Shuffle shuffle = new Shuffle(10, asList("cut 6"));
        assertEquals(0, shuffle.getPositionOf(6, 1));
        assertEquals(1, shuffle.getPositionOf(7, 1));
        assertEquals(2, shuffle.getPositionOf(8, 1));
        assertEquals(3, shuffle.getPositionOf(9, 1));
        assertEquals(4, shuffle.getPositionOf(0, 1));
        assertEquals(5, shuffle.getPositionOf(1, 1));
        assertEquals(6, shuffle.getPositionOf(2, 1));
        assertEquals(7, shuffle.getPositionOf(3, 1));
        assertEquals(8, shuffle.getPositionOf(4, 1));
        assertEquals(9, shuffle.getPositionOf(5, 1));
    }

    @Test
    public void test6() {
        Shuffle shuffle = new Shuffle(10, asList("cut 6", "deal with increment 7", "deal into new stack"));
        //3, 0, 7, 4, 1, 8, 5, 2, 9, 6

        //0// 0 1 2 3 4 5 6 7 8 9
        //1// 6 7 8 9 0 1 2 3 4 5
        //2// 6 9 2 5 8 1 4 7 0 3
        //3// 3 0 7 4 1 8 5 2 9 6

        assertEquals(0, shuffle.getPositionOf(3, 1));
        assertEquals(1, shuffle.getPositionOf(0, 1));
        assertEquals(2, shuffle.getPositionOf(7, 1));
        assertEquals(3, shuffle.getPositionOf(4, 1));
        assertEquals(4, shuffle.getPositionOf(1, 1));
        assertEquals(5, shuffle.getPositionOf(8, 1));
        assertEquals(6, shuffle.getPositionOf(5, 1));
        assertEquals(7, shuffle.getPositionOf(2, 1));
        assertEquals(8, shuffle.getPositionOf(9, 1));
        assertEquals(9, shuffle.getPositionOf(6, 1));
    }

    @Test
    public void one() throws IOException {
        Shuffle shuffle = new Shuffle(10007, new CommandReader().getCommands("/day22/data"));
        assertEquals(8191, shuffle.getPositionOf(2019, 1));
    }

    @Test
    public void test7_dealIntoNewStack() {
        Shuffle shuffle = new Shuffle(10, asList("deal into new stack"));
        //9, 8, 7, 6, 5, 4, 3, 2, 1, 0
        assertEquals(0, shuffle.getPositionOf2(9, 1));
        assertEquals(1, shuffle.getPositionOf2(8, 1));
        assertEquals(2, shuffle.getPositionOf2(7, 1));
        assertEquals(3, shuffle.getPositionOf2(6, 1));
        assertEquals(4, shuffle.getPositionOf2(5, 1));
        assertEquals(5, shuffle.getPositionOf2(4, 1));
        assertEquals(6, shuffle.getPositionOf2(3, 1));
        assertEquals(7, shuffle.getPositionOf2(2, 1));
        assertEquals(8, shuffle.getPositionOf2(1, 1));
        assertEquals(9, shuffle.getPositionOf2(0, 1));
    }

    @Test
    public void test7_cutNPositive() {
        Shuffle shuffle = new Shuffle(10, asList("cut 3"));
        //3, 4, 5, 6, 7, 8, 9, 0, 1, 2
        assertEquals(0, shuffle.getPositionOf2(3, 1));
        assertEquals(1, shuffle.getPositionOf2(4, 1));
        assertEquals(2, shuffle.getPositionOf2(5, 1));
        assertEquals(3, shuffle.getPositionOf2(6, 1));
        assertEquals(4, shuffle.getPositionOf2(7, 1));
        assertEquals(5, shuffle.getPositionOf2(8, 1));
        assertEquals(6, shuffle.getPositionOf2(9, 1));
        assertEquals(7, shuffle.getPositionOf2(0, 1));
        assertEquals(8, shuffle.getPositionOf2(1, 1));
        assertEquals(9, shuffle.getPositionOf2(2, 1));

    }

    @Test
    public void test7_cutNNegative() {
        Shuffle shuffle = new Shuffle(10, asList("cut -4"));
        //6, 7, 8, 9, 0, 1, 2, 3, 4, 5
        //assertEquals(1, shuffle.getPositionOf(6, 1));
        assertEquals(0, shuffle.getPositionOf2(6, 1));
        assertEquals(1, shuffle.getPositionOf2(7, 1));
        assertEquals(2, shuffle.getPositionOf2(8, 1));
        assertEquals(3, shuffle.getPositionOf2(9, 1));
        assertEquals(4, shuffle.getPositionOf2(0, 1));
        assertEquals(5, shuffle.getPositionOf2(1, 1));
        assertEquals(6, shuffle.getPositionOf2(2, 1));
        assertEquals(7, shuffle.getPositionOf2(3, 1));
        assertEquals(8, shuffle.getPositionOf2(4, 1));
        assertEquals(9, shuffle.getPositionOf2(5, 1));
    }

    @Test
    public void test7_dealWithIncrementN() {
        Shuffle shuffle = new Shuffle(10, asList("deal with increment 3"));
        //0, 7, 4, 1, 8, 5, 2, 9, 6, 3
        assertEquals(0, shuffle.getPositionOf2(0, 1));
        assertEquals(1, shuffle.getPositionOf2(7, 1));
        assertEquals(2, shuffle.getPositionOf2(4, 1));
        assertEquals(3, shuffle.getPositionOf2(1, 1));
        assertEquals(4, shuffle.getPositionOf2(8, 1));
        assertEquals(5, shuffle.getPositionOf2(5, 1));
        assertEquals(6, shuffle.getPositionOf2(2, 1));
        assertEquals(7, shuffle.getPositionOf2(9, 1));
        assertEquals(8, shuffle.getPositionOf2(6, 1));
        assertEquals(9, shuffle.getPositionOf2(3, 1));
    }

    @Test
    public void test8() {
        Shuffle shuffle = new Shuffle(10, asList("deal with increment 7", "deal into new stack", "deal into new stack"));
        //0, 3, 6, 9, 2, 5, 8, 1, 4, 7
        assertEquals(0, shuffle.getPositionOf2(0, 1));
        assertEquals(1, shuffle.getPositionOf2(3, 1));
        assertEquals(2, shuffle.getPositionOf2(6, 1));
        assertEquals(3, shuffle.getPositionOf2(9, 1));
        assertEquals(4, shuffle.getPositionOf2(2, 1));
        assertEquals(5, shuffle.getPositionOf2(5, 1));
        assertEquals(6, shuffle.getPositionOf2(8, 1));
        assertEquals(7, shuffle.getPositionOf2(1, 1));
        assertEquals(8, shuffle.getPositionOf2(4, 1));
        assertEquals(9, shuffle.getPositionOf2(7, 1));
    }

    @Test
    public void test9() {
        Shuffle shuffle = new Shuffle(10, asList("cut 6", "deal with increment 7", "deal into new stack"));
        //3, 0, 7, 4, 1, 8, 5, 2, 9, 6
        assertEquals(0, shuffle.getPositionOf2(3, 1));
        assertEquals(1, shuffle.getPositionOf2(0, 1));
        assertEquals(2, shuffle.getPositionOf2(7, 1));
        assertEquals(3, shuffle.getPositionOf2(4, 1));
        assertEquals(4, shuffle.getPositionOf2(1, 1));
        assertEquals(5, shuffle.getPositionOf2(8, 1));
        assertEquals(6, shuffle.getPositionOf2(5, 1));
        assertEquals(7, shuffle.getPositionOf2(2, 1));
        assertEquals(8, shuffle.getPositionOf2(9, 1));
        assertEquals(9, shuffle.getPositionOf2(6, 1));
    }

    @Test
    public void test10() {
        Shuffle shuffle = new Shuffle(10, asList("deal with increment 7", "deal with increment 9", "cut -2"));
        shuffle.shuffle();
        //6, 3, 0, 7, 4, 1, 8, 5, 2, 9
        assertEquals(0, shuffle.getPositionOf2(6, 1));
        assertEquals(1, shuffle.getPositionOf2(3, 1));
        assertEquals(2, shuffle.getPositionOf2(0, 1));
        assertEquals(3, shuffle.getPositionOf2(7, 1));
        assertEquals(4, shuffle.getPositionOf2(4, 1));
        assertEquals(5, shuffle.getPositionOf2(1, 1));
        assertEquals(6, shuffle.getPositionOf2(8, 1));
        assertEquals(7, shuffle.getPositionOf2(5, 1));
        assertEquals(8, shuffle.getPositionOf2(2, 1));
        assertEquals(9, shuffle.getPositionOf2(9, 1));
    }


    @Test
    public void one2() throws IOException {
        Shuffle shuffle = new Shuffle(10007, new CommandReader().getCommands("/day22/data"));
        assertEquals(8191, shuffle.getPositionOf2(2019, 1));
    }

    @Test
    public void test11_dealIntoNewStack() {
        Shuffle shuffle = new Shuffle(10, asList("deal into new stack"));
        //9, 8, 7, 6, 5, 4, 3, 2, 1, 0
        assertEquals(0, shuffle.getNumberAt(9, 1));
        assertEquals(1, shuffle.getNumberAt(8, 1));
        assertEquals(2, shuffle.getNumberAt(7, 1));
        assertEquals(3, shuffle.getNumberAt(6, 1));
        assertEquals(4, shuffle.getNumberAt(5, 1));
        assertEquals(5, shuffle.getNumberAt(4, 1));
        assertEquals(6, shuffle.getNumberAt(3, 1));
        assertEquals(7, shuffle.getNumberAt(2, 1));
        assertEquals(8, shuffle.getNumberAt(1, 1));
        assertEquals(9, shuffle.getNumberAt(0, 1));
    }


    @Test
    public void test11_cutNPositive() {
        Shuffle shuffle = new Shuffle(10, asList("cut 3"));
        //0  1  2  3  4  5  6  7  8  9
        //3, 4, 5, 6, 7, 8, 9, 0, 1, 2
        assertEquals(3, shuffle.getNumberAt(0, 1));
        assertEquals(4, shuffle.getNumberAt(1, 1));
        assertEquals(5, shuffle.getNumberAt(2, 1));
        assertEquals(6, shuffle.getNumberAt(3, 1));
        assertEquals(7, shuffle.getNumberAt(4, 1));
        assertEquals(8, shuffle.getNumberAt(5, 1));
        assertEquals(9, shuffle.getNumberAt(6, 1));
        assertEquals(0, shuffle.getNumberAt(7, 1));
        assertEquals(1, shuffle.getNumberAt(8, 1));
        assertEquals(2, shuffle.getNumberAt(9, 1));
    }

    @Test
    public void test11_cutNNegative() {
        Shuffle shuffle = new Shuffle(10, asList("cut -4"));
        //0  1  2  3  4  5  6  7  8  9
        //6, 7, 8, 9, 0, 1, 2, 3, 4, 5
        assertEquals(6, shuffle.getNumberAt(0, 1));
        assertEquals(7, shuffle.getNumberAt(1, 1));
        assertEquals(8, shuffle.getNumberAt(2, 1));
        assertEquals(9, shuffle.getNumberAt(3, 1));
        assertEquals(0, shuffle.getNumberAt(4, 1));
        assertEquals(1, shuffle.getNumberAt(5, 1));
        assertEquals(2, shuffle.getNumberAt(6, 1));
        assertEquals(3, shuffle.getNumberAt(7, 1));
        assertEquals(4, shuffle.getNumberAt(8, 1));
        assertEquals(5, shuffle.getNumberAt(9, 1));
    }

    @Test
    public void test11_dealWithIncrementN() {
        Shuffle shuffle = new Shuffle(10, asList("deal with increment 3"));
        assertEquals(0, shuffle.getNumberAt(0, 1));
        assertEquals(7, shuffle.getNumberAt(1, 1));
        assertEquals(4, shuffle.getNumberAt(2, 1));
        assertEquals(1, shuffle.getNumberAt(3, 1));
        assertEquals(8, shuffle.getNumberAt(4, 1));
        assertEquals(5, shuffle.getNumberAt(5, 1));
        assertEquals(2, shuffle.getNumberAt(6, 1));
        assertEquals(9, shuffle.getNumberAt(7, 1));
        assertEquals(6, shuffle.getNumberAt(8, 1));
        assertEquals(3, shuffle.getNumberAt(9, 1));
    }

    @Test
    public void test12() {
        List<String> commands = new LinkedList<>(asList(
                "deal into new stack",
                "cut -2",
                "deal with increment 7",
                "cut 8",
                "cut -4",
                "deal with increment 7",
                "cut 3",
                "deal with increment 9",
                "deal with increment 3",
                "cut -1"
        ));
        Collections.reverse(commands);
        Shuffle shuffle = new Shuffle(10, commands);
        //9, 2, 5, 8, 1, 4, 7, 0, 3, 6
        assertEquals(9, shuffle.getNumberAt(0, 1));
        assertEquals(2, shuffle.getNumberAt(1, 1));
        assertEquals(5, shuffle.getNumberAt(2, 1));
        assertEquals(8, shuffle.getNumberAt(3, 1));
        assertEquals(1, shuffle.getNumberAt(4, 1));
        assertEquals(4, shuffle.getNumberAt(5, 1));
        assertEquals(7, shuffle.getNumberAt(6, 1));
        assertEquals(0, shuffle.getNumberAt(7, 1));
        assertEquals(3, shuffle.getNumberAt(8, 1));
        assertEquals(6, shuffle.getNumberAt(9, 1));
    }


    @Test
    public void test13_dealIntoNewStack() {
        Shuffle shuffle = new Shuffle(7, asList("deal into new stack"));
        shuffle.shuffle();
        shuffle.shuffle();
        shuffle.shuffle();
        assertEquals(asList(6, 5, 4, 3, 2, 1, 0), shuffle.getDeck());

        List<String> commands = new LinkedList<>(asList("deal into new stack"));
        Collections.reverse(commands);
        shuffle = new Shuffle(7, commands);
        //assertEquals(6, shuffle.getNumberAt(0, 3));
        assertEquals(5, shuffle.getNumberAt(1, 3));
        assertEquals(4, shuffle.getNumberAt(2, 3));
        assertEquals(3, shuffle.getNumberAt(3, 3));
        assertEquals(2, shuffle.getNumberAt(4, 3));
        assertEquals(1, shuffle.getNumberAt(5, 3));
        assertEquals(0, shuffle.getNumberAt(6, 3));
    }

    @Test
    public void test13_cutNPositive() {
        Shuffle shuffle = new Shuffle(7, asList("cut 3"));
        shuffle.shuffle();
        shuffle.shuffle();
        shuffle.shuffle();
        assertEquals(asList(2, 3, 4, 5, 6, 0, 1), shuffle.getDeck());

        List<String> commands = new LinkedList<>(asList("cut 3"));
        Collections.reverse(commands);
        shuffle = new Shuffle(7, commands);
        int shuffles = 3;
        assertEquals(2, shuffle.getNumberAt(0, shuffles));
        assertEquals(3, shuffle.getNumberAt(1, shuffles));
        assertEquals(4, shuffle.getNumberAt(2, shuffles));
        assertEquals(5, shuffle.getNumberAt(3, shuffles));
        assertEquals(6, shuffle.getNumberAt(4, shuffles));
        assertEquals(0, shuffle.getNumberAt(5, shuffles));
        assertEquals(1, shuffle.getNumberAt(6, shuffles));
    }


    @Test
    public void test13_cutNNegative() {
        Shuffle shuffle = new Shuffle(11, asList("cut -4"));
        shuffle.shuffle();
        shuffle.shuffle();
        shuffle.shuffle();
        assertEquals(asList(10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9), shuffle.getDeck());

        List<String> commands = new LinkedList<>(asList("cut -4"));
        Collections.reverse(commands);
        shuffle = new Shuffle(11, commands);
        int shuffles = 3;
        assertEquals(10, shuffle.getNumberAt(0, shuffles));
        assertEquals(0, shuffle.getNumberAt(1, shuffles));
        assertEquals(1, shuffle.getNumberAt(2, shuffles));
        assertEquals(2, shuffle.getNumberAt(3, shuffles));
        assertEquals(3, shuffle.getNumberAt(4, shuffles));
        assertEquals(4, shuffle.getNumberAt(5, shuffles));
        assertEquals(5, shuffle.getNumberAt(6, shuffles));
        assertEquals(6, shuffle.getNumberAt(7, shuffles));
        assertEquals(7, shuffle.getNumberAt(8, shuffles));
        assertEquals(8, shuffle.getNumberAt(9, shuffles));
        assertEquals(9, shuffle.getNumberAt(10, shuffles));
    }

    @Test
    public void test13_dealWithIncrementN() {
        Shuffle shuffle = new Shuffle(7, asList("deal with increment 3"));
        shuffle.shuffle();
        shuffle.shuffle();
        shuffle.shuffle();
        assertEquals(asList(0, 6, 5, 4, 3, 2, 1), shuffle.getDeck());

        List<String> commands = new LinkedList<>(asList("deal with increment 3"));
        Collections.reverse(commands);
        shuffle = new Shuffle(7, commands);
        int shuffles = 3;

        assertEquals(0, shuffle.getNumberAt(0, shuffles));
        assertEquals(6, shuffle.getNumberAt(1, shuffles));
        assertEquals(5, shuffle.getNumberAt(2, shuffles));
        assertEquals(4, shuffle.getNumberAt(3, shuffles));
        assertEquals(3, shuffle.getNumberAt(4, shuffles));
        assertEquals(2, shuffle.getNumberAt(5, shuffles));
        assertEquals(1, shuffle.getNumberAt(6, shuffles));
    }

    @Test
    public void test14() {
        Shuffle shuffle = new Shuffle(11, asList("cut 6", "deal with increment 7", "deal into new stack"));
        shuffle.shuffle();
        shuffle.shuffle();
        shuffle.shuffle();
        assertEquals(asList(7, 1, 6, 0, 5, 10, 4, 9, 3, 8, 2), shuffle.getDeck());

        List<String> commands = new LinkedList<>(asList("cut 6", "deal with increment 7", "deal into new stack"));
        Collections.reverse(commands);
        shuffle = new Shuffle(11, commands);
        int shuffles = 3;

        assertEquals(7, shuffle.getNumberAt(0, shuffles));
        assertEquals(1, shuffle.getNumberAt(1, shuffles));
        assertEquals(6, shuffle.getNumberAt(2, shuffles));
        assertEquals(0, shuffle.getNumberAt(3, shuffles));
        assertEquals(5, shuffle.getNumberAt(4, shuffles));
        assertEquals(10, shuffle.getNumberAt(5, shuffles));
        assertEquals(4, shuffle.getNumberAt(6, shuffles));
        assertEquals(9, shuffle.getNumberAt(7, shuffles));
        assertEquals(3, shuffle.getNumberAt(8, shuffles));
        assertEquals(8, shuffle.getNumberAt(9, shuffles));
        assertEquals(2, shuffle.getNumberAt(10, shuffles));
    }

    @Test
    public void test15() {
        List<String> commands = new LinkedList<>(asList(
                "deal into new stack",
                "cut -2",
                "deal with increment 7",
                "cut 8",
                "cut -4",
                "deal with increment 7",
                "cut 3",
                "deal with increment 9",
                "deal with increment 3",
                "cut -1"
        ));
        Shuffle shuffle = new Shuffle(11, commands);
        shuffle.shuffle();
        shuffle.shuffle();
        shuffle.shuffle();
        assertEquals(asList(2, 4, 6, 8, 10, 1, 3, 5, 7, 9, 0), shuffle.getDeck());

        Collections.reverse(commands);
        shuffle = new Shuffle(11, commands);
        int shuffles = 3;

        assertEquals(2, shuffle.getNumberAt(0, shuffles));
        assertEquals(4, shuffle.getNumberAt(1, shuffles));
        assertEquals(6, shuffle.getNumberAt(2, shuffles));
        assertEquals(8, shuffle.getNumberAt(3, shuffles));
        assertEquals(10, shuffle.getNumberAt(4, shuffles));
        assertEquals(1, shuffle.getNumberAt(5, shuffles));
        assertEquals(3, shuffle.getNumberAt(6, shuffles));
        assertEquals(5, shuffle.getNumberAt(7, shuffles));
        assertEquals(7, shuffle.getNumberAt(8, shuffles));
        assertEquals(9, shuffle.getNumberAt(9, shuffles));
        assertEquals(0, shuffle.getNumberAt(10, shuffles));
    }


    @Test
    public void two0() throws IOException {
        long shuffles = 5;
        List<String> commands = new CommandReader().getCommands("/day22/data");
        Shuffle shuffle = new Shuffle(10007, commands);
        for (int i = 0 ; i < shuffles ; i++)shuffle.shuffle();
        List<Integer> deck = new ArrayList<>(shuffle.getDeck());

        Collections.reverse(commands);
        shuffle = new Shuffle(10007, commands);
        for (int i = 0 ; i < 10007 ; i++) {
            assertEquals(deck.get(i).longValue(), shuffle.getNumberAt(i, shuffles));
        }
    }


    @Test
    public void two() throws IOException {
        List<String> commands = new CommandReader().getCommands("/day22/data");
        Collections.reverse(commands);
        Shuffle shuffle = new Shuffle(119315717514047L, commands);
        assertEquals(1644352419829L, shuffle.getNumberAt(2020, 101741582076661L));
    }
}
