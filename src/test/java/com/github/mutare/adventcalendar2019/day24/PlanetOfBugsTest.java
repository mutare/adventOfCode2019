package com.github.mutare.adventcalendar2019.day24;

import com.github.mutare.adventcalendar2019.day17.FileViewInputSource;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PlanetOfBugsTest {

    @Test
    public void test0() throws IOException {
        PlanetOfBugs planetOfBugs = new PlanetOfBugs(new FileViewInputSource().getView("/day24/test0"));
        planetOfBugs.nextMinute();
        assertArrayEquals(planetOfBugs.getMap(), new FileViewInputSource().getView("/day24/test1"));
        planetOfBugs.nextMinute();
        assertArrayEquals(planetOfBugs.getMap(), new FileViewInputSource().getView("/day24/test2"));
        planetOfBugs.nextMinute();
        assertArrayEquals(planetOfBugs.getMap(), new FileViewInputSource().getView("/day24/test3"));
        planetOfBugs.nextMinute();
        assertArrayEquals(planetOfBugs.getMap(), new FileViewInputSource().getView("/day24/test4"));
    }

    @Test
    public void test1() throws IOException {
        PlanetOfBugs planetOfBugs = new PlanetOfBugs(new FileViewInputSource().getView("/day24/test5"));
        assertEquals(2129920, planetOfBugs.getBiodiversity());
    }

    @Test
    public void test2() throws IOException {
        Set<Integer> biodiversities = new HashSet<>();
        PlanetOfBugs planetOfBugs = new PlanetOfBugs(new FileViewInputSource().getView("/day24/test0"));
        int biodiversity;
        while (!biodiversities.contains(biodiversity = planetOfBugs.getBiodiversity())) {
            biodiversities.add(biodiversity);
            planetOfBugs.nextMinute();
        }
        assertEquals(2129920, planetOfBugs.getBiodiversity());

    }

    @Test
    public void one() throws IOException {
        Set<Integer> biodiversities = new HashSet<>();
        PlanetOfBugs planetOfBugs = new PlanetOfBugs(new FileViewInputSource().getView("/day24/data"));
        int biodiversity;
        while (!biodiversities.contains(biodiversity = planetOfBugs.getBiodiversity())) {
            biodiversities.add(biodiversity);
            planetOfBugs.nextMinute();
        }
        assertEquals(32506764, planetOfBugs.getBiodiversity());
    }

    @Test
    public void test6() throws IOException {
        PlanetOfBugs planetOfBugs = new PlanetOfBugs(new FileViewInputSource().getView("/day24/test6"), true);
        for (int i = 0; i < 10; i++) {
            planetOfBugs.nextMinute();
        }
        assertEquals(99, planetOfBugs.getNoOfBugs());
    }

    @Test
    public void two() throws IOException {

        PlanetOfBugs planetOfBugs = new PlanetOfBugs(new FileViewInputSource().getView("/day24/data"), true);
        for (int i = 0; i < 200; i++) {
            planetOfBugs.nextMinute();
        }
        assertEquals(1963, planetOfBugs.getNoOfBugs());
    }

}
