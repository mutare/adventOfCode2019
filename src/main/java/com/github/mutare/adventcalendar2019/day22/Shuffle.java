package com.github.mutare.adventcalendar2019.day22;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://codeforces.com/blog/entry/72593
 */
public class Shuffle {
    private final List<String> commands;
    private final long deckSize;
    LinkedList<Integer> deck;

    Shuffle(long deckSize, List<String> commands) {
        this.deckSize = deckSize;
        this.commands = commands;
    }

    private void dealIntoNewStack() {
        Collections.reverse(deck);
    }

    private void cutN(int n) {
        n = n < 0 ? this.deck.size() + n : n;
        List<Integer> l = new LinkedList<>();
        l.addAll(this.deck.subList(n, this.deck.size()));
        l.addAll(this.deck.subList(0, n));
        this.deck = new LinkedList<>(l);
    }

    private void dealWithIncrementN(int n) {
        int size = this.deck.size();
        int[] l = new int[size];
        int i = 0;
        while (!this.deck.isEmpty()) {
            l[i] = this.deck.pollFirst();
            i = (i + n) % size;
        }
        this.deck = new LinkedList<>(Arrays.stream(l).boxed().collect(Collectors.toList()));
    }

    public List<Integer> getDeck() {
        return this.deck;
    }

    public void shuffle() {
        this.deck = this.deck == null ? generateFactoryDeck(this.deckSize) : this.deck;
        for (String command : commands) {
            if (command.startsWith("deal with increment ")) {
                dealWithIncrementN(Integer.parseInt(command.substring("deal with increment ".length())));
            } else if (command.equals("deal into new stack")) {
                dealIntoNewStack();
            } else if (command.startsWith("cut ")) {
                cutN(Integer.parseInt(command.substring("cut ".length())));
            }
        }
    }

    public long getPositionOf(long n, long shuffles) {
        for (String command : commands) {
            if (command.startsWith("deal with increment ")) {
                long i = Integer.parseInt(command.substring("deal with increment ".length()));
                n = (i * n) % deckSize;
            } else if (command.equals("deal into new stack")) {
                n = deckSize - n - 1;
            } else if (command.startsWith("cut ")) {
                int i = Integer.parseInt(command.substring("cut ".length()));
                if (i > 0) {
                    n = n >= i ? n - i : (int) (deckSize - i + n);
                } else {
                    n = deckSize + i > n ? n - i : (int) (n - (deckSize + i));
                }
            }
        }
        return n;
    }

    private LinkedList<Integer> generateFactoryDeck(long n) {
        LinkedList<Integer> deck = new LinkedList<>();
        for (int i = 0; i < n; i++) deck.add(i);
        return deck;
    }

    public long getNumberAt(long n, long shuffles) {
        BigInteger x = BigInteger.valueOf(n);
        BigInteger m = BigInteger.valueOf(deckSize);
        BigInteger s = BigInteger.valueOf(shuffles);
        BigInteger one = BigInteger.valueOf(1);

        BigInteger a = BigInteger.valueOf(1);
        BigInteger b = BigInteger.valueOf(0);

        for (String command : commands) {
            BigInteger c = null;
            BigInteger d = null;
            if (command.equals("deal into new stack")) {
                c = BigInteger.valueOf(-1);
                d = BigInteger.valueOf(-1);
            } else if (command.startsWith("cut ")) {
                int i = Integer.parseInt(command.substring("cut ".length()));
                c = BigInteger.valueOf(1);
                d = BigInteger.valueOf(i);
            } else if (command.startsWith("deal with increment ")) {
                long i = Integer.parseInt(command.substring("deal with increment ".length()));
                c = BigInteger.valueOf(i).modInverse(m);
                d = BigInteger.valueOf(0);
            }
            //ac mod m,bc+d  mod m
            a = a.multiply(c);
            b = b.multiply(c).add(d.mod(m));
            a = a.add(m).mod(m);
            b = b.add(m).mod(m);
        }

        if (shuffles == 1) {
            return (a.multiply(x).add(b.mod(m))).mod(m).longValue();
        }

        return a.longValue() == 1 ?
                x.add(b.multiply(s)).mod(m).longValue() :
                x.multiply(a.modPow(s, m)).add(a.modPow(s, m).subtract(one).multiply(b).multiply(a.subtract(one).modInverse(m))).mod(m).longValue();
    }

    public long getPositionOf2(long x, int shuffles) {
        long a = 1, b = 0;
        long m = deckSize;
        for (String command : commands) {
            long c = 0, d = 0;
            if (command.equals("deal into new stack")) {
                c = -1;
                d = -1;
            } else if (command.startsWith("cut ")) {
                int i = Integer.parseInt(command.substring("cut ".length()));
                c = 1;
                d = -i;
            } else if (command.startsWith("deal with increment ")) {
                long i = Integer.parseInt(command.substring("deal with increment ".length()));
                c = i;
                d = 0;
            }

            //ac mod m,bc+d  mod m
            a = (a * c);
            b = b * c + d % m;
            a += m;
            a %= m;
            b += m;
            b %= m;
        }
        return (a * x + b % m) % m;
    }
}
