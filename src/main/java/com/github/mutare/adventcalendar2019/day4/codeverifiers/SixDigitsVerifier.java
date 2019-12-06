package com.github.mutare.adventcalendar2019.day4.codeverifiers;

public class SixDigitsVerifier implements CodeVerifier {
    @Override
    public boolean verify(int code) {
        return code >= 100000 && code <= 999999;
    }
}
