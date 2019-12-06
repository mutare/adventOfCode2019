package com.github.mutare.adventcalendar2019.day4.codeverifiers;

public class DigitsFromLeftToRightNeverDecreaseVerifier implements CodeVerifier {
    @Override
    public boolean verify(int code) {
        String str = Integer.toString(code);
        int x = 0;

        for(int i = 0 ; i < str.length() ; i++ ) {
            int parseInt = Integer.parseInt(str, i, i + 1, Character.MAX_RADIX);
            if (parseInt < x) return false;
            x = parseInt;
        }
        return true;
    }
}
