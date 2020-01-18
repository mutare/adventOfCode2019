package com.github.mutare.adventcalendar2019.day19;

import com.github.mutare.adventcalendar2019.day15.FileProgramInputSource;
import org.junit.Test;

import java.io.IOException;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class TractorBeamTest {

    @Test
    public void one() throws IOException {
        TractorBeam tractorBeam = new TractorBeam(new FileProgramInputSource().getProgram("/day19/data"));
        int SIZE = 50;
        int count = 0;
        int[][] result = new int[SIZE][SIZE];
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++)
                if (1 == (result[j][i] = tractorBeam.get(i, j))) count++;
        }

        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                System.out.print(result[j][i]);
            }
            System.out.println();
        }

        assertEquals(166, count);
    }

    @Test
    public void two() throws IOException {
        assertEquals(3790981, getSantaShipCoordinate(new TractorBeam(new FileProgramInputSource().getProgram("/day19/data"))));
    }

    private int getSantaShipCoordinate(TractorBeam tractorBeam) {
        int size = 99;
        int y = size;
        int x = 0;
        while (true) {
            while (tractorBeam.get(x, y) == 0) x++;
            if (checkPoint(x, y - size, size, tractorBeam)) {
                return 10000 * x + y - size;
            }
            y++;
        }
    }

    private boolean checkPoint(int x, int y, int size, TractorBeam tractorBeam) {
        int p0 = tractorBeam.get(x, y);
        int p1 = tractorBeam.get(x + size, y);
        int p2 = tractorBeam.get(x, y + size);
        int p3 = tractorBeam.get(x + size, y + size);
        if (y % 100 == 0 || p0 * p1 * p2 * p3 == 1) {
            System.out.println(format("(%d, %d - %d%d%d%d)", x, y, p0, p1, p2, p3));
        }
        return p0 * p1 * p2 * p3 == 1;
    }
}
