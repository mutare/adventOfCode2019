package com.github.mutare.adventcalendar2019.day12;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NBodyProblemTest {

    @Test
    public void moonTest() {
        Moon moon0 = new Moon();
        Moon moon1 = new Moon();
        assertEquals(moon0, moon1);

        moon0.position.x = 1;
        moon1.position.x = 1;
        assertEquals(moon0, moon1);
    }

    @Test
    public void test1() throws IOException {
        List<Moon> moons;

        moons = new MoonSimulator(new FileMoonsInputSource().getMoons("/day12/test1")).makeSteps(0);
        assertEquals("pos=<x=-1, y=0, z=2>, vel=<x=0, y=0, z=0>", moons.get(0).toString());
        assertEquals("pos=<x=2, y=-10, z=-7>, vel=<x=0, y=0, z=0>", moons.get(1).toString());
        assertEquals("pos=<x=4, y=-8, z=8>, vel=<x=0, y=0, z=0>", moons.get(2).toString());
        assertEquals("pos=<x=3, y=5, z=-1>, vel=<x=0, y=0, z=0>", moons.get(3).toString());

        moons = new MoonSimulator(new FileMoonsInputSource().getMoons("/day12/test1")).makeSteps(1);
        assertEquals("pos=<x=2, y=-1, z=1>, vel=<x=3, y=-1, z=-1>", moons.get(0).toString());
        assertEquals("pos=<x=3, y=-7, z=-4>, vel=<x=1, y=3, z=3>", moons.get(1).toString());
        assertEquals("pos=<x=1, y=-7, z=5>, vel=<x=-3, y=1, z=-3>", moons.get(2).toString());
        assertEquals("pos=<x=2, y=2, z=0>, vel=<x=-1, y=-3, z=1>", moons.get(3).toString());

        MoonSimulator moonSimulator = new MoonSimulator(new FileMoonsInputSource().getMoons("/day12/test1"));
        moonSimulator.makeSteps(10);
        assertEquals(179, moonSimulator.getTotalEnergy());
    }

    @Test
    public void one() throws IOException {
        MoonSimulator moonSimulator = new MoonSimulator(new FileMoonsInputSource().getMoons("/day12/data"));
        moonSimulator.makeSteps(1000);
        assertEquals(9876, moonSimulator.getTotalEnergy());
    }

    @Test
    public void test2() throws IOException {
        assertEquals(2772, new MoonSimulator(new FileMoonsInputSource().getMoons("/day12/test1")).getNumberOfStepsToBackToInitialState());
    }

    @Test
    public void test3() throws IOException {
        MoonSimulator moonSimulator = new MoonSimulator(new FileMoonsInputSource().getMoons("/day12/test2"));
        assertEquals(4686774924L, moonSimulator.getNumberOfStepsToBackToInitialState());
    }


    @Test
    public void two() throws IOException {
        MoonSimulator moonSimulator = new MoonSimulator(new FileMoonsInputSource().getMoons("/day12/data"));
        assertEquals(307043147758488L, moonSimulator.getNumberOfStepsToBackToInitialState());
    }

}
