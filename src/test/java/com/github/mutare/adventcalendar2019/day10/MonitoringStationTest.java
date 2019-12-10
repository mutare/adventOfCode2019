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

        Asteroid bestAsteroid = monitoringStation.monitorStationLocation;

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
        Asteroid bestAsteroid = monitoringStation.monitorStationLocation;
        assertEquals(33, bestAsteroid.monitoringAsteroids);
        assertEquals(5, bestAsteroid.x);
        assertEquals(8, bestAsteroid.y);
    }

    @Test
    public void test2() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test2"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.monitorStationLocation;
        assertEquals(35, bestAsteroid.monitoringAsteroids);
        assertEquals(1, bestAsteroid.x);
        assertEquals(2, bestAsteroid.y);
    }

    @Test
    public void test3() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test3"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.monitorStationLocation;
        assertEquals(41, bestAsteroid.monitoringAsteroids);
        assertEquals(6, bestAsteroid.x);
        assertEquals(3, bestAsteroid.y);
    }

    @Test
    public void test4() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test4"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.monitorStationLocation;
        assertEquals(210, bestAsteroid.monitoringAsteroids);
        assertEquals(11, bestAsteroid.x);
        assertEquals(13, bestAsteroid.y);
    }

    @Test
    public void one() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/data"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.monitorStationLocation;
        assertEquals(303, bestAsteroid.monitoringAsteroids);
        assertEquals(26, bestAsteroid.x);
        assertEquals(29, bestAsteroid.y);
    }

    @Test
    public void test5() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test5"));
        assertNotNull(monitoringStation.map);
        Asteroid bestAsteroid = monitoringStation.monitorStationLocation;
        assertEquals(30, bestAsteroid.monitoringAsteroids);
        assertEquals(8, bestAsteroid.x);
        assertEquals(3, bestAsteroid.y);

        Asteroid asteroidVaporizedAsFirst = monitoringStation.getAsteroidVaporizedAs(1);
        assertEquals(8, asteroidVaporizedAsFirst.x);
        assertEquals(1, asteroidVaporizedAsFirst.y);

        Asteroid asteroidVaporizedAsSecond = monitoringStation.getAsteroidVaporizedAs(2);
        assertEquals(9, asteroidVaporizedAsSecond.x);
        assertEquals(0, asteroidVaporizedAsSecond.y);

    }
    @Test
    public void test6() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/test4"));
        Asteroid asteroidVaporizedAsFirst = monitoringStation.getAsteroidVaporizedAs(1);
        assertNotNull(asteroidVaporizedAsFirst);
        assertEquals(11, asteroidVaporizedAsFirst.x);
        assertEquals(12, asteroidVaporizedAsFirst.y);

        Asteroid asteroidVaporizedAs200 = monitoringStation.getAsteroidVaporizedAs(200);
        assertNotNull(asteroidVaporizedAsFirst);
        assertEquals(8, asteroidVaporizedAs200.x);
        assertEquals(2, asteroidVaporizedAs200.y);
    }

    @Test
    public void two() throws IOException {
        MonitoringStation monitoringStation = new MonitoringStation(asteroidMapReader.getMap("/day10/data"));
        Asteroid asteroidVaporizedAs200 = monitoringStation.getAsteroidVaporizedAs(200);
        assertNotNull(asteroidVaporizedAs200);
        assertEquals(4, asteroidVaporizedAs200.x);
        assertEquals(8, asteroidVaporizedAs200.y);
        assertEquals(408, 100 * asteroidVaporizedAs200.x + asteroidVaporizedAs200.y);
    }
}
