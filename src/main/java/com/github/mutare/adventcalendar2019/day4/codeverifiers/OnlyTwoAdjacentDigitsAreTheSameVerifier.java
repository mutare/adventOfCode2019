package com.github.mutare.adventcalendar2019.day4.codeverifiers;

public class OnlyTwoAdjacentDigitsAreTheSameVerifier implements CodeVerifier {

    private DigitGroupper digitGroupper = new DigitGroupper();

    @Override
    public boolean verify(int code) {
        return digitGroupper.group(Integer.toString(code)).contains(2);
    }
}
