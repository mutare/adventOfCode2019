package com.github.mutare.adventcalendar2019.day6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class OrbitsTest {

    @Test
    public void one() throws IOException {
        Collection<Orbit> orbits = new FileOrbitInputSource().getOrbits("/day6/data");
        assertEquals(2603, orbits.size());
        OrbitsMap orbitsMap = new OrbitsMap(orbits);
        assertEquals(2604, orbitsMap.orbitingObjects.size());
        assertEquals(621125, orbitsMap.getOrbitsNo());
    }

    @Test
    public void test() throws IOException {
        Collection<Orbit> orbits = new FileOrbitInputSource().getOrbits("/day6/test");
        assertEquals(11, orbits.size());
        OrbitsMap orbitsMap = new OrbitsMap(orbits);
        assertEquals(12, orbitsMap.orbitingObjects.size());

        assertEquals(42, orbitsMap.getOrbitsNo());
    }
}
