package com.github.mutare.adventcalendar2019.day10;

import java.util.*;
import java.util.stream.Collectors;

class MonitoringStation {
    final char[][] map;
    char[][] visibleAsteroidsMap;
    final Asteroid monitorStationLocation;

    MonitoringStation(char[][] map) {
        this.map = map;
        monitorStationLocation = findBestAsteroid();
    }

    private Asteroid findBestAsteroid() {
        Asteroid bestAsteroid = null;
        int max = 0;
        for (Asteroid asteroid : createVisibleAsteroidsMap()) {
            if (max < asteroid.monitoringAsteroids) {
                max = asteroid.monitoringAsteroids;
                bestAsteroid = asteroid;
            }
        }

        return bestAsteroid;
    }

    private List<Asteroid> createVisibleAsteroidsMap() {
        List<Asteroid> asteroids = new LinkedList<>();
        visibleAsteroidsMap = new char[map.length][map[0].length];
        for (int j = 0; j < map.length; j++)
            for (int i = 0; i < map[0].length; i++) {
                if (isAsteroid(map[j][i])) {
                    Asteroid asteroid = new Asteroid(i, j);
                    asteroid.monitoringAsteroids = findNumberOfVisibleAsteroidsFor(asteroid);
                    visibleAsteroidsMap[j][i] = Integer.toString(asteroid.monitoringAsteroids).charAt(0);
                    asteroids.add(asteroid);
                } else {
                    visibleAsteroidsMap[j][i] = '.';
                }
            }
        return asteroids;
    }

    int findNumberOfVisibleAsteroidsFor(Asteroid asteroid) {
        return getDirectionsToOtherAsteroidsFor(asteroid).stream().map(asteroid1 -> asteroid1.directionFromMonitoringStation.angle).collect(Collectors.toSet()).size();
    }

    private List<Asteroid> getDirectionsToOtherAsteroidsFor(Asteroid asteroid) {
        List<Asteroid> directions = new ArrayList<>();
        int x = asteroid.x;
        int y = asteroid.y;
        for (int j = 0; j < map.length; j++)
            for (int i = 0; i < map[0].length; i++) {
                if (!isAsteroid(map[j][i])) continue;
                if (x == i && y == j) continue;
                Direction direction = new Direction();
                direction.angle = calculateAngle(i - x, j - y);
                direction.distance = Math.sqrt(Math.pow(i - x, 2) + Math.pow(j - y, 2));
                Asteroid asteroid1 = new Asteroid(i, j);
                asteroid1.directionFromMonitoringStation = direction;
                directions.add(asteroid1);
            }
        return directions;
    }

    private Double calculateAngle(double x, double y) {
        return Math.atan2(y, x);
    }

    private boolean isAsteroid(char c) {
        return c == '#';
    }

    public Asteroid getAsteroidVaporizedAs(int no) {
        Map<Double, List<Asteroid>> collect =
                getDirectionsToOtherAsteroidsFor(monitorStationLocation)
                        .stream()
                        .collect(Collectors.groupingBy(asteroid -> asteroid.directionFromMonitoringStation.angle, Collectors.toCollection(ArrayList::new)));

        int noOfVapourizedAsteroids = 0;
        Asteroid lastVapourizedAsteroid = null;
        for (double laserAngle = -Math.PI / 2; noOfVapourizedAsteroids < no; ) {
            lastVapourizedAsteroid = vapourizeClosest(collect.get(laserAngle));
            noOfVapourizedAsteroids++;
            if (collect.get(laserAngle).size() == 0) {
                collect.remove(laserAngle);
            }

            laserAngle = getClosestAngle(laserAngle, collect.keySet());

        }
        return lastVapourizedAsteroid;
    }

    private Asteroid vapourizeClosest(List<Asteroid> asteroidsOnLine) {
        Asteroid asteroid = asteroidsOnLine.stream().min(Comparator.comparingDouble(o -> o.directionFromMonitoringStation.distance)).get();
        asteroidsOnLine.remove(asteroid);
        return asteroid;
    }

    private Double getClosestAngle(double laserAngle, Set<Double> keySet) {
        List<Double> angles = new ArrayList<>(keySet);
        Collections.sort(angles);

        for (Double angle : angles) {
            if (angle > laserAngle) {
                return angle;
            }
        }
        return angles.get(0);
    }
}
