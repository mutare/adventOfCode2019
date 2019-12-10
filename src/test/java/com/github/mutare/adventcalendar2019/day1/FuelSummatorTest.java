package com.github.mutare.adventcalendar2019.day1;

import com.github.mutare.adventcalendar2019.day1.fuelcalculator.AllFuelCalculator;
import com.github.mutare.adventcalendar2019.day1.fuelcalculator.MassFuelCalculator;
import com.github.mutare.adventcalendar2019.day1.inputsource.FileMassInputSource;
import com.github.mutare.adventcalendar2019.day1.inputsource.MassInputSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FuelSummatorTest {

    @Mock
    private MassFuelCalculator fuelCalculator;

    @InjectMocks
    private FuelSummator fuelSummator;

    @Before
    public void setUp() {
        fuelSummator = new FuelSummator(fuelCalculator);
    }


    @Test
    public void test() {
        when(fuelCalculator.calculateFuel(anyInt())).thenReturn(2, 4, 6, 8, 10, 12, 14, 16, 18, 20);
        assertEquals(110, fuelSummator.sumForMasses(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }

    @Test
    public void one() throws IOException {
        FuelSummator fuelSummator = new FuelSummator(new MassFuelCalculator());
        MassInputSource massInputSource = new FileMassInputSource();
        int sumForMasses = fuelSummator.sumForMasses(massInputSource.getMasses());

        assertEquals(3325156, sumForMasses);
    }

    @Test
    public void two() throws IOException {
        FuelSummator fuelSummator = new FuelSummator(new AllFuelCalculator());
        MassInputSource massInputSource = new FileMassInputSource();
        int sumForMasses = fuelSummator.sumForMasses(massInputSource.getMasses());

        assertEquals(4984866, sumForMasses);
    }
}
