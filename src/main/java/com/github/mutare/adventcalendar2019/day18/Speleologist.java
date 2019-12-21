package com.github.mutare.adventcalendar2019.day18;

public class Speleologist {

    enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    Location location;
    Direction direction;

    private Speleologist(int x, int y) {
        location = Location.of(x, y);
        direction = Direction.NORTH;
    }

    public static Speleologist of(Location location) {
        return new Speleologist(location.x, location.y);
    }

    public void moveAhead() {
        switch (direction) {
            case NORTH: location.y--; break;
            case SOUTH: location.y++; break;
            case EAST: location.x++; break;
            case WEST: location.x--; break;
        }
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash *= 31 * location.hashCode();
        hash *= 31 * direction.hashCode();
        return hash;
    }

    public Speleologist clone() {
        Speleologist speleologist = new Speleologist(location.x, location.y);
        speleologist.direction = this.direction;
        return speleologist;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Speleologist)) return false;
        Speleologist speleologist = (Speleologist) obj;
        return this.location.equals(speleologist.location) && this.direction == speleologist.direction;
    }
}
