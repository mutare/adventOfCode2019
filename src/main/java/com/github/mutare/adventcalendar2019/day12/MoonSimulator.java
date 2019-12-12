package com.github.mutare.adventcalendar2019.day12;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MoonSimulator {
    private final List<Moon> moons;
    private final List<Moon> initialMoonStates = new ArrayList<>();
    private long a = -1;
    private long b = -1;
    private long c = -1;
    private int stepCounter;


    public MoonSimulator(List<Moon> moons) {
        this.moons = moons;
        for (Moon moon : moons) {
            initialMoonStates.add(new Moon(moon));
        }
    }

    public List<Moon> makeSteps(int noOfSteps) {
        for (int i = 0; i < noOfSteps; i++) {
            step();
        }
        return moons;
    }

    private void step() {
        applyGravity();
        applyVelocity();
        stepCounter++;
    }

    private void applyGravity() {
        for (int i = 0; i < moons.size(); i++) {
            for (int j = i + 1; j < moons.size(); j++) {
                applyGravity(moons.get(i), moons.get(j));
            }
        }
    }

    private void applyVelocity() {
        for (Moon moon : moons) {
            moon.position.x += moon.velocity.x;
            moon.position.y += moon.velocity.y;
            moon.position.z += moon.velocity.z;
        }
    }

    private void applyGravity(Moon moon0, Moon moon1) {
        moon0.velocity.x -= Integer.signum(moon0.position.x - moon1.position.x);
        moon1.velocity.x += Integer.signum(moon0.position.x - moon1.position.x);

        moon0.velocity.y -= Integer.signum(moon0.position.y - moon1.position.y);
        moon1.velocity.y += Integer.signum(moon0.position.y - moon1.position.y);

        moon0.velocity.z -= Integer.signum(moon0.position.z - moon1.position.z);
        moon1.velocity.z += Integer.signum(moon0.position.z - moon1.position.z);
    }

    public int getTotalEnergy() {
        return moons.stream().map(Moon::getEnergy).reduce(Integer::sum).orElseThrow();
    }

    public long getNumberOfStepsToBackToInitialState() {
        while (a == -1 || b == -1 || c == -1) {
            step();
            eqX();
            eqY();
            eqZ();
        }
        return lcm(a, b, c);
    }

    private void eqX() {
        for (int i = 0; i < moons.size(); i++) {
            if (moons.get(i).position.x != initialMoonStates.get(i).position.x || moons.get(i).velocity.x != initialMoonStates.get(i).velocity.x) {
                return;
            }
        }
        if (a == -1) {
            a = stepCounter;
        }
    }

    private void eqY() {
        for (int i = 0; i < moons.size(); i++) {
            if (moons.get(i).position.y != initialMoonStates.get(i).position.y || moons.get(i).velocity.y != initialMoonStates.get(i).velocity.y) {
                return;
            }
        }
        if (b == -1) {
            b = stepCounter;
        }
    }

    private void eqZ() {
        for (int i = 0; i < moons.size(); i++) {
            if (moons.get(i).position.z != initialMoonStates.get(i).position.z || moons.get(i).velocity.z != initialMoonStates.get(i).velocity.z) {
                return;
            }
        }
        if (c == -1) {
            c = stepCounter;
        }
    }

    private long lcm(long x, long y, long z) {
        long xyLcm = (x * y) / gcd(x, y);
        return (xyLcm * z) / gcd(xyLcm, z);
    }

    private long gcd(long x, long y) {
        if (x != 0 && y != 0) {
            return gcd(max(x, y) % min(x, y), min(x, y));
        }
        return x + y;
    }

}
