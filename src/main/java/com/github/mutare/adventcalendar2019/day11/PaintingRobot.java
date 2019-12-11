package com.github.mutare.adventcalendar2019.day11;

import com.github.mutare.adventcalendar2019.day2.ShipComputer;

import java.util.HashMap;
import java.util.Map;

public class PaintingRobot extends Thread {
    private final ShipComputer shipComputer = new ShipComputer();

    private long[] program;
    private Map<Panel, Character> gridMap = new HashMap<>();

    private int x = 0;
    private int y = 0;
    private int direction = 0;//0 - north,  1 - east, 2 - south, 3 - west
    private boolean finish;

    public PaintingRobot(long[] program) {
        this.program = program;
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
            paintOnGrid(shipComputer.output().take().intValue());
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

    void paintOnGrid(int paint) {
        gridMap.put(new Panel(x, y), paint == 1 ? '#' : '.');
    }

    private long inputByPosition(int x, int y) {
        return gridMap.containsKey(new Panel(x, y)) ? gridMap.get(new Panel(x, y)) == '.' ? 0 : 1 : 0;
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
        return gridMap.keySet().size();
    }


    public char[][] getGrid() {
        char[][] grid = new char[100][100];
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++) {
                Panel p = new Panel(i, j);
                grid[i][j] = gridMap.getOrDefault(p, '.');
            }
        return grid;
    }
}
