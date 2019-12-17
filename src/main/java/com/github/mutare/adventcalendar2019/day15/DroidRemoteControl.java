package com.github.mutare.adventcalendar2019.day15;

import com.github.mutare.adventcalendar2019.day13.Game;
import com.github.mutare.adventcalendar2019.day13.Screen;
import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

import java.awt.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DroidRemoteControl implements Game {
    private static final int SIZE = 50;
    private IntcodeComputer intcodeComputer;
    private boolean useScreen;
    private char[][] map = new char[SIZE][SIZE];
    private int[][] distance = new int[SIZE][SIZE];
    private int x = SIZE / 2;
    private int y = SIZE / 2;
    private Screen screen;
    private int delay = 50;
    private int distanceCounter = 0;
    int oxygenSystemX;
    int oxygenSystemY;
    private int oxygenSystemDistance;
    private int startX = x;
    private int startY = y;


    public DroidRemoteControl(long[] program, boolean useScreen) {
        this.intcodeComputer = new IntcodeComputer(program);
        this.useScreen = useScreen;
        this.screen = useScreen ? new Screen(this) : null;
        if (useScreen) screen.setVisible(true);
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = '-';
            }
        }
        map[x][y] = 'D';
    }

    public int go() throws InterruptedException {
        printMap(map);
        IntcodeComputer.Result proccess;

        long dir = 1;
        while (true) {
            if (useScreen) {
                Thread.sleep(delay);
            }
            proccess = this.intcodeComputer.proccess(dir);
            int xx = 0, yy = 0;
            if (dir == 1) {
                xx = x;
                yy = y - 1;//north
            } else if (dir == 2) {
                xx = x;
                yy = y + 1;//south
            } else if (dir == 3) {
                xx = x - 1;
                yy = y;//west
            } else if (dir == 4) {
                xx = x + 1;
                yy = y;//east
            }
            if (proccess.type == IntcodeComputer.Result.Type.OUTPUT) {
                if (proccess.value == 0) {
                    map[xx][yy] = '#';
                    dir = turn((int) dir, false);
                } else if (proccess.value == 1) {
                    if (x == oxygenSystemX && y == oxygenSystemY) {
                        map[x][y] = '$';
                    } else {
                        map[x][y] = '.';
                    }
                    calculateDistance(x, y);
                    map[x = xx][y = yy] = 'D';
                    dir = turn((int) dir, true);
                } else if (proccess.value == 2) {
                    map[x = xx][y = yy] = '$';
                    if (!useScreen) printMap(map);
                    oxygenSystemX = x;
                    oxygenSystemY = y;
                    oxygenSystemDistance = distanceCounter + 1;
                }
                if (useScreen) screen.draw(convertMap(map));
            }
            if (x == startX && y == startY && oxygenSystemDistance != 0) {
                return oxygenSystemDistance;
            }
        }
    }

    private void calculateDistance(int x, int y) {
        if (distance[x][y] == 0) {
            distanceCounter++;
            distance[x][y] = distanceCounter;
        }
        if (distance[x][y] > 0) {
            distanceCounter = distance[x][y];
        }
    }

    private long turn(int dir, boolean b) {
        //1,4,2,3 
        return b ? new int[]{4, 3, 1, 2}[dir - 1] : new int[]{3, 4, 2, 1}[dir - 1];
    }

    private int[][] convertMap(char[][] map) {
        int[][] intMap = new int[SIZE][SIZE];
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == '#') {
                    intMap[j][i] = 1;
                } else if (map[i][j] == ' ') {
                    intMap[j][i] = 0;
                } else if (map[i][j] == '.') {
                    intMap[j][i] = 2;
                } else if (map[i][j] == 'D') {
                    intMap[j][i] = 4;
                } else if (map[i][j] == 'O') {
                    intMap[j][i] = 3;
                } else if (map[i][j] == '$') {
                    intMap[j][i] = 5;
                }
            }
        }
        return intMap;
    }

    private void printMap(char[][] map) {
        System.out.println("***********");
        for (int j = 0; j < map.length; j++) {
            System.out.print(j % 10);
            for (int i = 0; i < map[0].length; i++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void setDelay(int value) {
        delay = 2 * value;
    }

    @Override
    public void next() {

    }

    @Override
    public void next(int input) {
        switch (input) {
            case 37:
                //directions.add(3L);
                break;//left
            case 39:
                //directions.add(4L);
                break;//right
            case 38:
                //directions.add(1L);
                break;//up
            case 40:
                //directions.add(2L);
                break;//down
        }

    }

    @Override
    public int getParameter(int no) {
        return no == 0 ? minutes : 0;
    }

    int minutes;

    public void turnOnSystem() throws InterruptedException {

        while (noEmptyHall()) {
            if (useScreen) {
                Thread.sleep(delay * 20);
            }
            Set<Point> listPointToFill = new HashSet<>();
            for (int j = 0; j < map.length; j++)
                for (int i = 0; i < map[0].length; i++)
                    if (map[i][j] == 'O' || map[i][j] == '$') {
                        listPointToFill.add(getPoinIfEmptyHall(i + 1, j));
                        listPointToFill.add(getPoinIfEmptyHall(i - 1, j));
                        listPointToFill.add(getPoinIfEmptyHall(i, j + 1));
                        listPointToFill.add(getPoinIfEmptyHall(i, j - 1));
                    }

            listPointToFill.stream().filter(Objects::nonNull).forEach(point -> map[point.x][point.y] = 'O');
            if (useScreen) screen.draw(convertMap(map));
            minutes++;
        }
        printMap(map);
    }

    private Point getPoinIfEmptyHall(int x, int y) {
        return map[x][y] == '.' ? new Point(x, y) : null;
    }

    private boolean noEmptyHall() {
        for (int j = 0; j < map.length; j++)
            for (int i = 0; i < map[0].length; i++)
                if (map[i][j] == '.') return true;

        return false;
    }
}
