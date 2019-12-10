package com.github.mutare.adventcalendar2019.day4.codeverifiers;

public class RangeVerifier implements CodeVerifier {
    private final int min;
    private final int max;

    public RangeVerifier(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean verify(int code) {
        return code >= min && code <= max;
    }
}
