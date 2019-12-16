package com.github.mutare.adventcalendar2019.day13;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class ArcadeGame extends Thread implements Game {

    LinkedBlockingQueue<Long> input = new LinkedBlockingQueue<>();

    private int count = 0;

    private boolean finish;

    private int score;
    private int delay = 10;

    public void insertCoins(int i) {
        shipComputer.getMemory()[0] = i;
    }

    public boolean isFinish() {
        return finish;
    }


    public void printGrid(PrintStream out) {
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                out.print(grid[j][i]);
            }
            out.println();
        }
        out.println();
    }

    @Override
    public void next() {
        next++;
    }

    @Override
    public void next(int input) {

    }

    @Override
    public int getParameter(int no) {
        return no == 0 ? (int) elements.values().stream().filter(integer -> integer == 2).count() :
                no == 1 ? score : -1;
    }

    @Override
    public void setDelay(int value) {
        this.delay = value;
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

    private int[][] grid = new int[42][24];

    private Map<Point, Integer> elements = new HashMap<>();

    private IntcodeComputer shipComputer;

    Screen screen;
    boolean useScreen;

    public ArcadeGame(long[] program, boolean useScreen) {
        shipComputer = new IntcodeComputer(program);
        this.screen = useScreen ? new Screen(this) : null;
        this.useScreen = useScreen;
    }

    int next = 1008;

    @Override
    public void run() {
        while (!finish) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            next++;
        }
    }

    public void play(long... in) throws InterruptedException {
        start();
        if (in != null)
            for (long l : in)
                input.add(l);

        if (useScreen) screen.setVisible(true);

        IntcodeComputer.Result result = null;
        while ((result = shipComputer.proccess(getInput())).type != IntcodeComputer.Result.Type.END) {
            ArcadeGame.Point point = new ArcadeGame.Point();
            point.x = (result).value.intValue();
            result = shipComputer.proccess();
            point.y = (result).value.intValue();
            result = shipComputer.proccess();
            int c = (result).value.intValue();
            if (point.x == -1 && point.y == 0) {
                score = c;
            } else {
                elements.put(point, c);
                boolean wasPaddleOrBall = grid[point.x][point.y] == 3 || grid[point.x][point.y] == 4;
                grid[point.x][point.y] = c;
                if (useScreen && !wasPaddleOrBall) screen.draw(grid);
            }

            if (useScreen) {
                while (next == 0) Thread.sleep(1);
                next--;
            }
            count++;
        }
        finish = true;
        printGrid(System.out);
    }

    private Long getInput() {
        int b = getObjectX(4);
        int p = getObjectX(3);
        return Long.valueOf(b > p ? 1 : (b < p ? -1 : 0));
    }

    private int getObjectX(int c) {
        for (int j = 0; j < grid.length; j++)
            for (int i = 0; i < grid[0].length; i++)
                if (grid[j][i] == c) return j;
        return -1;
    }

}
