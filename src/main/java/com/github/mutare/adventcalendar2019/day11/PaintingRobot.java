package com.github.mutare.adventcalendar2019.day11;

import com.github.mutare.adventcalendar2019.day2.ShipComputer;

public class PaintingRobot extends Thread {
    private final int width;
    private final int height;

    private final ShipComputer shipComputer = new ShipComputer();

    private long[] program;
    char[][] grid;
    private int[][] wasPainted;
    private int paintings = 0;

    int x;
    int y;
    private int direction = 0;//0 - north,  1 - east, 2 - south, 3 - west
    private boolean finish;

    public PaintingRobot(long[] program) {
        this(program, 100, 100);
    }

    public PaintingRobot(long[] program, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = width / 2;
        this.y = height / 2;
        this.grid = new char[width][height];
        this.wasPainted = new int[width][height];
        this.program = program;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                grid[i][j] = '.';
                wasPainted[i][j] = 0;
            }
    }

    public void paint() throws InterruptedException {
        finish = false;
        this.start();

        while (!finish) {
            shipComputer.input(inputByPosition(x, y));
            while (shipComputer.output().isEmpty()) {
                Thread.sleep(1);
                if (finish) return;
            }
            paintOnGrid(shipComputer.output().take().intValue(), x, y);
            while (shipComputer.output().isEmpty()) {
                Thread.sleep(1);
                if (finish) return;
            }
            turn(shipComputer.output().take());
            move();
        }
    }

    private void move() {
        switch (direction) {
            case 0:
                y--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y++;
                break;
            case 3:
                x--;
                break;
            default:
                throw new RuntimeException(String.format("No such direction (%d)", direction));
        }
    }

    private void turn(long turn) {
        if (turn == 0) direction--;
        if (turn == 1) direction++;
        direction = (direction + 4) % 4;
    }

    void paintOnGrid(int paint, int x, int y) {
        grid[x][y] = paint == 1 ? '#' : '.';
        if (wasPainted[x][y] == 0) wasPainted[x][y] = 1;
    }

    private long inputByPosition(int x, int y) {
        return grid[x][y] == '.' ? 0 : 1;
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

    public int getNumberOfPaintedPanels() {
        int count = 0;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                if (wasPainted[i][j] == 1) count++;
            }
        return count;
    }


}
