package com.github.mutare.adventcalendar2019.day18;

import com.google.common.hash.HashCode;

public class Way {
    Location location0;
    Location location1;

    private Way(Location location0, Location location1) {
        this.location0 = location0;
        this.location1 = location1;
    }

    static Way of(Location location0, Location location1) {
        return new Way(location0, location1);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash *= 31 * location0.hashCode();
        hash *= 31 * location1.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Way)) return false;
        Way way = (Way) obj;
        return ((way.location0.equals(this.location0) && way.location1.equals(this.location1))
                || (way.location0.equals(this.location1) && way.location1.equals(this.location0)));
    }
}
