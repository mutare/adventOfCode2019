package com.github.mutare.adventcalendar2019.day1.fuelcalculator;

public class MassFuelCalculator implements FuelCalculator {
    @Override
    public int calculateFuel(int mass) {
        return Math.floorDiv(mass, 3) - 2;
    }
}
