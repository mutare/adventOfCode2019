package com.github.mutare.adventcalendar2019.day4.codeverifiers;

public class OnlyTwoAdjacentDigitsAreTheSameVerifier implements CodeVerifier {

    private final DigitGrouper digitGrouper = new DigitGrouper();

    @Override
    public boolean verify(int code) {
        return digitGrouper.group(Integer.toString(code)).contains(2);
    }
}
