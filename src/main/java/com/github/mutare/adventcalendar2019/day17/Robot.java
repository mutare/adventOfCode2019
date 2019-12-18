package com.github.mutare.adventcalendar2019.day17;

import java.awt.*;

public class Robot {
    int x;
    int y;
    Direction direction;

    static Robot of(int x, int y, Direction direction) {
        Robot robot = new Robot();
        robot.x = x;
        robot.y = y;
        robot.direction = direction;
        return robot;
    }

    Point getPointAhead() {
        switch (direction) {
            case EAST: return new Point(x + 1, y);
            case WEST: return new Point(x - 1, y);
            case NORTH: return new Point(x, y - 1);
            case SOUTH: return new Point(x, y + 1);
        }
        throw new RuntimeException("Where am I ?");
    }

    void move() {
        switch (direction) {
            case EAST: x++; break;
            case WEST: x--; break;
            case NORTH: y--; break;
            case SOUTH: y++; break;
        }
    }

    boolean turn(Direction direction) {
        this.direction = direction;
        return true;
    }

    enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    @Override
    public String toString() {
        return "Robot{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }
}
