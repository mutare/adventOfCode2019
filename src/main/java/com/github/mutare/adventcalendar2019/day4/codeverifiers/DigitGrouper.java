package com.github.mutare.adventcalendar2019.day4.codeverifiers;

import java.util.HashSet;
import java.util.Set;

class DigitGrouper {
    public Set<Integer> group(String str) {
        Set<Integer> counts = new HashSet<>();
        char lastChar = str.toCharArray()[0];
        int count = 1;
        for (int i = 1; i < str.toCharArray().length; i++) {
            char c = str.toCharArray()[i];
            if (lastChar == c) {
                count++;
            } else {
                counts.add(count);
                count = 1;
            }
            lastChar = c;
        }
        counts.add(count);
        return counts;
    }
}
