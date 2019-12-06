package com.github.mutare.adventcalendar2019.day1;

import com.github.mutare.adventcalendar2019.day1.fuelcalculator.FuelCalculator;

import java.util.stream.Stream;

public class FuelSummator {
    private FuelCalculator fuelCalculator;

    public FuelSummator(FuelCalculator fuelCalculator) {
        this.fuelCalculator = fuelCalculator;
    }

    int sumForMasses(Stream<Integer> masses) {
        return masses
                .map(fuelCalculator::calculateFuel)
                .reduce(0, Integer::sum);
    }
}
