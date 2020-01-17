package com.github.mutare.adventcalendar2019.day18;

class Robot {
        Location location;
        short distance;

        public Robot(byte c, byte r) {
            this.location = Location.of(c, r);
        }

        public Robot(Robot robot) {
            this.location = Location.of(robot.location.x, robot.location.y);
            this.distance = robot.distance;
        }

        public Robot(Location location) {
            this.location = location;
        }

        public static Robot of(Location location, short distance) {
            Robot robot = new Robot(location);
            robot.distance = distance;
            return robot;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Robot robot = (Robot) o;
            return this.location.equals(robot.location);
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash *= 31 * location.hashCode();
            return hash;
        }

    }