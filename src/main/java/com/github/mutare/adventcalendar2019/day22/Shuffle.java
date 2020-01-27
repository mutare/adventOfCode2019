package com.github.mutare.adventcalendar2019.day22;

import java.util.*;
import java.util.stream.Collectors;

public class Shuffle {
    LinkedList<Integer> deck;

    Shuffle(List<Integer> deck) {
        this.deck = new LinkedList<>(deck);
    }

    public void dealIntoNewStack() {
        Collections.reverse(deck);
    }

    public void cutN(int n) {
        n = n < 0 ? this.deck.size() + n : n;
        List<Integer> l = new LinkedList<>();
        l.addAll(this.deck.subList(n, this.deck.size()));
        l.addAll(this.deck.subList(0, n));
        this.deck = new LinkedList<>(l);
    }

    public void dealWithIncrementN(int n) {
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

    public int getPositionOf(int n) {
        for (int i = 0 ; i < this.deck.size() ; i++) {
            if (this.deck.get(i).equals(n)) return i;
        }

        throw new RuntimeException("not found");
    }
}
