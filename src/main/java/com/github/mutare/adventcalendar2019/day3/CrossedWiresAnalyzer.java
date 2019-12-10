package com.github.mutare.adventcalendar2019.day3;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CrossedWiresAnalyzer {

    private final List<List<Point>> paths = new LinkedList<>();
    private final List<Point> intersections;

    public CrossedWiresAnalyzer(Stream<String> line1, Stream<String> line2) {
        paths.add(createCoordsFor(line1.collect(Collectors.toList())));
        paths.add(createCoordsFor(line2.collect(Collectors.toList())));
        intersections = getIntersections();
    }

    private List<Point> createCoordsFor(Collection<String> line) {
        List<Point> coords = new ArrayList<>();
        int x = 0;
        int y = 0;
        coords.add(new Point(x, y));
        for (String next : line) {

            int value = Integer.parseInt(next.substring(1));
            switch (next.charAt(0)) {
                case 'R':
                    for (int i = 0; i < value; i++) {
                        x++;
                        coords.add(new Point(x, y));
                    }
                    break;
                case 'L':
                    for (int i = 0; i < value; i++) {
                        x--;
                        coords.add(new Point(x, y));
                    }
                    break;
                case 'U':
                    for (int i = 0; i < value; i++) {
                        y++;
                        coords.add(new Point(x, y));
                    }
                    break;
                case 'D':
                    for (int i = 0; i < value; i++) {
                        y--;
                        coords.add(new Point(x, y));
                    }

                    break;
            }

        }
        return coords;
    }

    private List<Point> getIntersections() {
        List<Point> coords1 = paths.get(0);
        Set<Point> setOfCoords2 = new HashSet<>(paths.get(1));

        List<Point> intersections = new ArrayList<>();

        for (Point p1 : coords1) {
            if (setOfCoords2.contains(p1)) {
                intersections.add(p1);
            }
        }
        return intersections;
    }

    public int getDistanceToNearestIntersection() {
        return intersections
                .stream()
                .map(point -> Math.abs(point.getLocation().x) + Math.abs(point.getLocation().y))
                .filter(integer -> integer != 0)
                .min(Integer::compare)
                .orElseThrow(RuntimeException::new);
    }

    public int getLowestCombinedStepsToReachIntersection() {
        int combinedSteps = Integer.MAX_VALUE;
        for (int i = 0; i < intersections.size(); i++) {
            Point intersection = intersections.get(i);
            if (i == 0) {
                continue;
            }
            int wire1 = findCombinedStepsFor(intersection, paths.get(0));
            int wire2 = findCombinedStepsFor(intersection, paths.get(1));
            if (wire1 + wire2 < combinedSteps) {
                combinedSteps = wire1 + wire2;
            }
        }
        return combinedSteps;
    }

    private int findCombinedStepsFor(Point intersection, List<Point> wirePath) {
        Map<Point, Integer> middleSteps = new HashMap<>();
        int step = 0;
        for (Point wirePoint : wirePath) {
            middleSteps.put(wirePoint, step);
            if (wirePoint.equals(intersection)) {
                return step;
            }
            step++;
        }
        return -1;
    }
}
