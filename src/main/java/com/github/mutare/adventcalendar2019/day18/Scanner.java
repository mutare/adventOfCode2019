package com.github.mutare.adventcalendar2019.day18;

import java.util.HashSet;
import java.util.Set;

class Scanner {
            Location location;
            Set<Character> doors = new HashSet<>();

            public Scanner(Location location) {
                this.location = location;
            }

            public Scanner(Scanner scanner) {
                this.location = Location.of(scanner.location.x, scanner.location.y);
                this.doors = new HashSet<>(scanner.doors);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Scanner scanner = (Scanner) o;
                return location.equals(scanner.location);
            }

            @Override
            public int hashCode() {
                int hash = 17;
                hash *= 31 * location.hashCode();
                return hash;
            }
        }