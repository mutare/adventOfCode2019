package com.github.mutare.adventcalendar2019.day3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class CrossedWiresAnalyzerTest {

    private FileInputSource fileInputSource = new FileInputSource();

    private CrossedWiresAnalyzer crossedWiresAnalyzer;

    @Test
    public void oneTwo() {
        crossedWiresAnalyzer = new CrossedWiresAnalyzer(fileInputSource.getLine(0), fileInputSource.getLine(1));
        assertEquals(1674, crossedWiresAnalyzer.getDistanceToNearestIntersection());
        assertEquals(14012, crossedWiresAnalyzer.getLowestCombinedStepsToReachIntersection());
    }

    @Test
    public void test() {
        crossedWiresAnalyzer = new CrossedWiresAnalyzer(Stream.of("R8,U5,L5,D3".split(",")), Stream.of("U7,R6,D4,L4".split(",")));
        assertEquals(6, crossedWiresAnalyzer.getDistanceToNearestIntersection());
        assertEquals(30, crossedWiresAnalyzer.getLowestCombinedStepsToReachIntersection());

        crossedWiresAnalyzer = new CrossedWiresAnalyzer(Stream.of("R75,D30,R83,U83,L12,D49,R71,U7,L72".split(",")), Stream.of("U62,R66,U55,R34,D71,R55,D58,R83".split(",")));
        assertEquals(159, crossedWiresAnalyzer.getDistanceToNearestIntersection());
        assertEquals(610, crossedWiresAnalyzer.getLowestCombinedStepsToReachIntersection());

        crossedWiresAnalyzer = new CrossedWiresAnalyzer(Stream.of("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".split(",")), Stream.of("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split(",")));
        assertEquals(135, crossedWiresAnalyzer.getDistanceToNearestIntersection());
        assertEquals(410, crossedWiresAnalyzer.getLowestCombinedStepsToReachIntersection());
    }
}
