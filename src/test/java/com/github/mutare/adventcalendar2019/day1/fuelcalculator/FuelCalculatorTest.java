package com.github.mutare.adventcalendar2019.day1.fuelcalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class FuelCalculatorTest {
    private final MassFuelCalculator fuelCalculator = new MassFuelCalculator();

    @Test
    public void test() {
        assertEquals(2, fuelCalculator.calculateFuel(12));
        assertEquals(2, fuelCalculator.calculateFuel(14));
        assertEquals(654, fuelCalculator.calculateFuel(1969));
        assertEquals(33583, fuelCalculator.calculateFuel(100756));
    }
}
