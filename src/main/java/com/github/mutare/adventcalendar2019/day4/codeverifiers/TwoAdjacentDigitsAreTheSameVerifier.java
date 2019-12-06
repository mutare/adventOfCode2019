package com.github.mutare.adventcalendar2019.day4.codeverifiers;

import java.util.Set;

public class TwoAdjacentDigitsAreTheSameVerifier implements CodeVerifier {

    private DigitGroupper digitGroupper = new DigitGroupper();

    @Override
    public boolean verify(int code) {
        Set<Integer> group = digitGroupper.group(Integer.toString(code));

        for (Integer count : group) {
            if (count >= 2) {
                return true;
            }
        }
        return false;
    }
}
