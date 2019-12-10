package com.github.mutare.adventcalendar2019.day1.fuelcalculator;

public class AllFuelCalculator implements FuelCalculator {
    @Override
    public int calculateFuel(int mass) {
        int fuel = 0;
        int sum = 0;
        while((fuel = internalCalculate(mass)) > 0) {
            sum += fuel;
            mass = fuel;
        }
        return sum;
    }

    private int internalCalculate(int mass) {
        return mass > 0 ? (Math.floorDiv(mass, 3) - 2) : 0;
    }
}
