package com.github.mutare.adventcalendar2019.day10;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MonitoringStationTest {

    private AsteroidMapReader asteroidMapReader = new AsteroidMapReader();

    @Test
    public void test0() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test0"));
        assertEquals('#', monitoringStation.map[0][1]);
        assertNotNull(monitoringStation.map);
        printMap(monitoringStation.map);

        assertEquals(7, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(1,0)));
        assertEquals(7, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(4,0)));

        assertEquals(6, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(0,2)));
        assertEquals(7, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(1,2)));
        assertEquals(7, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(2,2)));
        assertEquals(7, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(3,2)));
        assertEquals(5, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(4,2)));

        assertEquals(7, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(4,3)));

        assertEquals(8, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(3,4)));
        assertEquals(7, monitoringStation.findNumberOfVisibleAsteroidsFor(new Asteroid(4,4)));

        System.out.println();

        Asteroid bestAsteroid = monitoringStation.findBestAsteroid();

        printMap(monitoringStation.visibleAsteroidsMap);

        assertEquals(8, bestAsteroid.monitoringAsteroids);
        assertEquals(3, bestAsteroid.x);
        assertEquals(4, bestAsteroid.y);

    }

    private void printMap(char[][] map) {
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[0].length; i++) {
                System.out.print(map[j][i]);
            }
            System.out.println();
        }

    }

    @Test
    public void test1() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test1"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.findBestAsteroid();
        assertEquals(33, bestAsteroid.monitoringAsteroids);
        assertEquals(5, bestAsteroid.x);
        assertEquals(8, bestAsteroid.y);
    }

    @Test
    public void test2() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test2"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.findBestAsteroid();
        assertEquals(35, bestAsteroid.monitoringAsteroids);
        assertEquals(1, bestAsteroid.x);
        assertEquals(2, bestAsteroid.y);
    }

    @Test
    public void test3() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test3"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.findBestAsteroid();
        assertEquals(41, bestAsteroid.monitoringAsteroids);
        assertEquals(6, bestAsteroid.x);
        assertEquals(3, bestAsteroid.y);
    }

    @Test
    public void test4() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test4"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.findBestAsteroid();
        assertEquals(210, bestAsteroid.monitoringAsteroids);
        assertEquals(11, bestAsteroid.x);
        assertEquals(13, bestAsteroid.y);
    }

    @Test
    public void one() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/data"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.findBestAsteroid();
        assertEquals(303, bestAsteroid.monitoringAsteroids);
        assertEquals(26, bestAsteroid.x);
        assertEquals(29, bestAsteroid.y);
    }
}
