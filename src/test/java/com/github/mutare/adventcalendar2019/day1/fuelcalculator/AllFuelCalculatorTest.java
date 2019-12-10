package com.github.mutare.adventcalendar2019.day1.fuelcalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class AllFuelCalculatorTest {
    private final FuelCalculator fuelCalculator = new AllFuelCalculator();

    @Test
    public void test() {
        assertEquals(966, fuelCalculator.calculateFuel(1969));
        assertEquals(50346, fuelCalculator.calculateFuel(100756));
    }
}
