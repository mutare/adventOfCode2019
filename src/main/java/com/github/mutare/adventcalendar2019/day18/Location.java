package com.github.mutare.adventcalendar2019.day18;

import java.util.Objects;

class Location {
        static Location of(byte x, byte y) {
            Location location = new Location();
            location.x = x;
            location.y = y;
            return location;
        }

        byte x;
        byte y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x &&
                    y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }