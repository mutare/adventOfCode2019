package com.github.mutare.adventcalendar2019.day18;

class Location {
    int x;
    int y;


    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Location of(int x, int y) {
        return new Location(x, y);
    }

    public Location clone() {
        return new Location(this.x, this.y);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash+= 31 * x;
        hash+= 31 * y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Location)) return false;
        Location other = ((Location)obj);
        return this.x == other.x && this.y == other.y;
    }
}
