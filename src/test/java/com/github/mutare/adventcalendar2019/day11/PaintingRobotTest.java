package com.github.mutare.adventcalendar2019.day11;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PaintingRobotTest {
    @Test
    public void one() throws IOException, InterruptedException {
        PaintingRobot paintingRobot = new PaintingRobot(new FileProgramInputSource().getProgram());
        paintingRobot.paint();
        paintingRobot.join();
        assertEquals(2336, paintingRobot.getNumberOfPaintedPanels());

        paintPaint(paintingRobot.getGrid());
    }

    @Test
    public void two() throws IOException, InterruptedException {
        PaintingRobot paintingRobot = new PaintingRobot(new FileProgramInputSource().getProgram());
        paintingRobot.paintOnGrid(1);
        paintingRobot.paint();
        paintingRobot.join();

        paintPaint(paintingRobot.getGrid());
    }

    private void paintPaint(char[][] grid) {
        for (int i = 0 ; i < grid[0].length ; i++) {
            for (char[] chars : grid) {
                System.out.print(chars[i]);
            }
            System.out.println();
        }
    }

}
