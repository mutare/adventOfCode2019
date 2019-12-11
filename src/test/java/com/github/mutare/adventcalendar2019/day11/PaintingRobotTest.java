package com.github.mutare.adventcalendar2019.day11;

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

        paintPaint(paintingRobot.grid, 100, 100);
    }

    @Test
    public void two() throws IOException, InterruptedException {
        PaintingRobot paintingRobot = new PaintingRobot(new FileProgramInputSource().getProgram(), 100, 30);
        paintingRobot.paintOnGrid(1, paintingRobot.x, paintingRobot.y);
        paintingRobot.paint();
        paintingRobot.join();

        paintPaint(paintingRobot.grid, 30, 100);
    }

    private void paintPaint(char[][] grid, int x, int y) {
        for (int i = 0 ; i < x ; i++) {
            for (int j = 0 ; j < y ; j++) {
                System.out.print(grid[j][i]);
            }
            System.out.println();
        }
    }

}
