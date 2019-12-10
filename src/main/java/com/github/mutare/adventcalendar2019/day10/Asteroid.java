package com.github.mutare.adventcalendar2019.day10;

public class Asteroid {
    int x;
    int y;
    int monitoringAsteroids;
    Direction directionFromMonitoringStation;

    Asteroid(int i, int j) {
        this.x = i;
        this.y = j;
    }

    public Direction getDirectionFromMonitoringStation() {
        return directionFromMonitoringStation;
    }
}
