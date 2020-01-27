package com.github.mutare.adventcalendar2019.day22;

import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static org.junit.Assert.assertEquals;

public class ShuffleTest {

    @Test
    public void test0_dealIntoNewStack() {
        Shuffle shuffle = new Shuffle(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        shuffle.dealIntoNewStack();
        assertEquals(asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0), shuffle.getDeck());
    }

    @Test
    public void test0_cutNPositive() {
        Shuffle shuffle = new Shuffle(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        shuffle.cutN(3);
        assertEquals(asList(3, 4, 5, 6, 7, 8, 9, 0, 1, 2), shuffle.getDeck());
    }

    @Test
    public void test0_cutNNegative() {
        Shuffle shuffle = new Shuffle(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        shuffle.cutN(-4);
        assertEquals(asList(6, 7, 8, 9, 0, 1, 2, 3, 4, 5), shuffle.getDeck());
    }

    @Test
    public void test0_dealWithIncrementN() {
        Shuffle shuffle = new Shuffle(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        shuffle.dealWithIncrementN(3);
        assertEquals(asList(0, 7, 4, 1, 8, 5, 2, 9, 6, 3), shuffle.getDeck());
    }


    @Test
    public void test1() {
        Shuffle shuffle = new Shuffle(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        shuffle.dealWithIncrementN(7);
        shuffle.dealIntoNewStack();
        shuffle.dealIntoNewStack();
        assertEquals(asList(0, 3, 6, 9, 2, 5, 8, 1, 4, 7), shuffle.getDeck());
    }

    @Test
    public void test2() {
        Shuffle shuffle = new Shuffle(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        shuffle.cutN(6);
        shuffle.dealWithIncrementN(7);
        shuffle.dealIntoNewStack();
        assertEquals(asList(3, 0, 7, 4, 1, 8, 5, 2, 9, 6), shuffle.getDeck());
    }

    @Test
    public void test3() {
        Shuffle shuffle = new Shuffle(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        shuffle.dealWithIncrementN(7);
        shuffle.dealWithIncrementN(9);
        shuffle.cutN(-2);
        assertEquals(asList(6, 3, 0, 7, 4, 1, 8, 5, 2, 9), shuffle.getDeck());
    }

    @Test
    public void test4() {
        Shuffle shuffle = new Shuffle(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        shuffle.dealIntoNewStack();
        shuffle.cutN(-2);
        shuffle.dealWithIncrementN(7);
        shuffle.cutN(8);
        shuffle.cutN(-4);
        shuffle.dealWithIncrementN(7);
        shuffle.cutN(3);
        shuffle.dealWithIncrementN(9);
        shuffle.dealWithIncrementN(3);
        shuffle.cutN(-1);
        assertEquals(asList(9, 2, 5, 8, 1, 4, 7, 0, 3, 6), shuffle.getDeck());
    }

    @Test
    public void one() throws IOException {
        Shuffle shuffle = new Shuffle(generateFactoryDeck());
        for (String command : new CommandReader().getCommands("/day22/data")) {
            if (command.startsWith("deal with increment ")) {
                shuffle.dealWithIncrementN(Integer.parseInt(command.substring("deal with increment ".length())));
            } else if (command.equals("deal into new stack")) {
                shuffle.dealIntoNewStack();
            } else if (command.startsWith("cut ")) {
                shuffle.cutN(Integer.parseInt(command.substring("cut ".length())));
            }
        }
        assertEquals(8191, shuffle.getPositionOf(2019));
    }

    private List<Integer> generateFactoryDeck() {
        List<Integer> deck = new LinkedList<>();
        for (int i  = 0 ; i < 10007 ; i++) deck.add(i);
        return deck;
    }
}
