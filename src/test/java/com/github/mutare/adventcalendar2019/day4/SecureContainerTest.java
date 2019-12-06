package com.github.mutare.adventcalendar2019.day4;

import com.github.mutare.adventcalendar2019.day4.codeverifiers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class SecureContainerTest {
    private CodeVerifier sixDigitsVerifier = new SixDigitsVerifier();
    private CodeVerifier digitsFromLeftToRightNeverDecreaseVerifier = new DigitsFromLeftToRightNeverDecreaseVerifier();
    private CodeVerifier twoAdjacentDigitsAreTheSameVerifier = new TwoAdjacentDigitsAreTheSameVerifier();
    private CodeVerifier onlyTwoAdjacentDigitsAreTheSameVerifier = new OnlyTwoAdjacentDigitsAreTheSameVerifier();
    private CodeVerifier rangeVerifier = new RangeVerifier(372304, 847060);

    @Test
    public void test() {
        SecurityContainer securityContainer = new SecurityContainerImpl(sixDigitsVerifier, digitsFromLeftToRightNeverDecreaseVerifier, twoAdjacentDigitsAreTheSameVerifier);
        assertTrue(securityContainer.check(111111));
        assertFalse(securityContainer.check(223450));
        assertFalse(securityContainer.check(123789));
    }

    @Test
    public void one() {
        SecurityContainer securityContainer = new SecurityContainerImpl(sixDigitsVerifier, digitsFromLeftToRightNeverDecreaseVerifier, twoAdjacentDigitsAreTheSameVerifier);
        int c = 0;
        for (int i = 372304 ; i <= 847060 ; i++) {
            if (securityContainer.check(i)) c++;
        }
        assertEquals(475, c);
    }

    @Test
    public void two() {
        SecurityContainer securityContainer = new SecurityContainerImpl(sixDigitsVerifier, digitsFromLeftToRightNeverDecreaseVerifier, onlyTwoAdjacentDigitsAreTheSameVerifier);

        assertTrue(securityContainer.check(112233));
        assertFalse(securityContainer.check(123444));
        assertTrue(securityContainer.check(111122));

        int c = 0;
        for (int i = 372304 ; i <= 847060 ; i++) {
            if (securityContainer.check(i)) c++;
        }
        assertEquals(297, c);
    }
}
