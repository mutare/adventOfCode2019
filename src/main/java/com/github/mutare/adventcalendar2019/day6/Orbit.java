package com.github.mutare.adventcalendar2019.day6;

public class Orbit {
    String central;
    String orbiting;

    private Orbit() {

    }

    public static Orbit createOrbit(String line) {
        Orbit orbit = new Orbit();
        String[] objects = line.split("\\)");
        orbit.central = objects[0];
        orbit.orbiting = objects[1];
        return orbit;
    }
}
