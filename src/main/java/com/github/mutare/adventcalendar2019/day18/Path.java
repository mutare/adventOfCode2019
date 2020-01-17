package com.github.mutare.adventcalendar2019.day18;

import java.util.Objects;

class Path {
        Location location0;
        Location location1;

        static Path of(Location location0, Location location1) {
            Path path = new Path();
            path.location0 = location0;
            path.location1 = location1;
            return path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Path path = (Path) o;
            return location0.equals(path.location0) &&
                    location1.equals(path.location1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(location0, location1);
        }
    }