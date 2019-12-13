package com.github.mutare.adventcalendar2019.day13;

import com.github.mutare.adventcalendar2019.day2.ShipComputer;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ArcadeGame extends Thread {

    private boolean finish;

    private int score;

    public void insertCoins(int i) {
        program[0] = i;
    }

    public boolean isFinish() {
        return finish;
    }


    public void printGrid(PrintStream out) {
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[0].length; i++) {
                out.print(grid[j][i]);
            }
            out.println();
        }
        out.println();
    }

    public int getScore() {
        return score;
    }

    public void move(int i) {
        shipComputer.input(i);
    }

    static class Point {
        int x;
        int y;

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + x;
            hash = hash * 31 + y;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Point)) return false;
            return ((Point) obj).x == this.x && ((Point) obj).y == this.y;
        }
    }

    private long[] program;
    private int[][] grid = new int[42][24];

    private Map<Point, Integer> elements = new HashMap<>();

    private ShipComputer shipComputer = new ShipComputer();

    public ArcadeGame(long[] program) {
        this.program = program;
    }

    @Override
    public void run() {
        try {
            shipComputer.proccess(program);
            finish = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void play() throws InterruptedException {
        finish = false;
        start();

        while (!finish) {
            ArcadeGame.Point point = new ArcadeGame.Point();

            while (shipComputer.getOutput().isEmpty()) if (finish) return;
            point.x = shipComputer.getOutput().take().intValue();

            while (shipComputer.getOutput().isEmpty()) if (finish) return;
            point.y = shipComputer.getOutput().take().intValue();

            while (shipComputer.getOutput().isEmpty()) if (finish) return;
            int c = shipComputer.getOutput().take().intValue();
            if (point.x == -1 && point.y == 0) {
                score = shipComputer.getOutput().take().intValue();
            } else {
                elements.put(point, c);
                grid[point.x][point.y] = c;
            }
            //printGrid(System.out);
        }
    }

    public int getNumberOfBlockTiles() {
        int i = 0;
        for (int[] l : grid)
            for(int c : l) {
                if (c == 2) i++;
            }

        //return (int) elements.values().stream().filter(integer -> integer == 2).count();
        return i;
    }

}
