package com.github.mutare.adventcalendar2019.day12;

import static java.lang.Math.abs;
import static java.lang.String.format;

public class Moon {
    static class Position {
        int x;
        int y;
        int z;

        Position(Position position) {
            this.x = position.x;
            this.y = position.y;
            this.z = position.z;
        }

        Position() {}
    }

    static class Velocity {
        int x;
        int y;
        int z;

        Velocity(Velocity velocity) {
            this.x = velocity.x;
            this.y = velocity.y;
            this.z = velocity.z;
        }

        Velocity() {}
    }

    Position position = new Position();
    Velocity velocity = new Velocity();

    public Moon() {

    }

    Moon(Moon moon) {
        this.position = new Position(moon.position);
        this.velocity = new Velocity(moon.velocity);
    }

    int getEnergy() {
        return getPotentialEnergy() * getKineticEnergy();
    }

    private int getKineticEnergy() {
        return abs(velocity.x) + abs(velocity.y) + abs(velocity.z);
    }

    private int getPotentialEnergy() {
        return abs(position.x) + abs(position.y) + abs(position.z);
    }

    @Override
    public String toString() {
        return format("pos=<x=%d, y=%d, z=%d>, vel=<x=%d, y=%d, z=%d>", position.x, position.y, position.z, velocity.x, velocity.y, velocity.z);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash *31 + position.x;
        hash = hash *31 + position.y;
        hash = hash *31 + position.z;
        hash = hash *31 + velocity.x;
        hash = hash *31 + velocity.y;
        hash = hash *31 + velocity.z;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Moon)) {
            return false;
        }
        return this.position.x == ((Moon) obj).position.x &&
                this.position.y == ((Moon) obj).position.y &&
                this.position.z == ((Moon) obj).position.z &&
                this.velocity.x == ((Moon) obj).velocity.x &&
                this.velocity.y == ((Moon) obj).velocity.y &&
                this.velocity.z == ((Moon) obj).velocity.z;
    }
}
