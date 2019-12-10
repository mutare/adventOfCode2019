package com.github.mutare.adventcalendar2019.day10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MonitoringStation {
    char[][] map;
    char[][] visibleAsteroidsMap;

    MonitoringStation(char[][] map) {
        this.map = map;
    }

    public Asteroid findBestAsteroid() {
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
        int x = asteroid.x;
        int y = asteroid.y;
        Set<Double> directions = new HashSet<>();
        for (int j = 0; j < map.length; j++)
            for (int i = 0; i < map[0].length; i++) {
                if (!isAsteroid(map[j][i])) continue;
                if (x == i && y == j) continue;
                directions.add(calculateAngle(i - x, j - y));
            }
        return directions.size();
    }

    private Double calculateAngle(double x, double y) {
        return Math.atan2(y, x);
    }

    private boolean isAsteroid(char c) {
        return c == '#';
    }
}
