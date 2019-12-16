package com.github.mutare.adventcalendar2019.day11;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PaintingRobot {
    private final IntcodeComputer shipComputer;

    private Map<Panel, Character> gridMap = new HashMap<>();

    private int x = 0;
    private int y = 0;
    private int direction = 0;//0 - north,  1 - east, 2 - south, 3 - west

    public PaintingRobot(long[] program) {
        shipComputer = new IntcodeComputer(program);
    }

    public void paint() {
        IntcodeComputer.Result proccess;
        while ((proccess = shipComputer.proccess(inputByPosition(x, y))).type != IntcodeComputer.Result.Type.END) {
            paintOnGrid(proccess.value);

            proccess = shipComputer.proccess();
            turn(proccess.value);
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

    void paintOnGrid(long paint) {
        gridMap.put(new Panel(x, y), paint == 1 ? '#' : '.');
    }

    private long inputByPosition(int x, int y) {
        return gridMap.containsKey(new Panel(x, y)) ? gridMap.get(new Panel(x, y)) == '.' ? 0 : 1 : 0;
    }

    public int getNumberOfPaintedPanels() {
        return gridMap.keySet().size();
    }

    public char[][] getGrid() {
        Set<Panel> collect = gridMap.entrySet().stream().filter(panelCharacterEntry -> panelCharacterEntry.getValue() == '#').map(Map.Entry::getKey).collect(Collectors.toSet());
        int minX = collect.stream().map(panel -> panel.x).min(Integer::compareTo).orElseThrow();
        int minY = collect.stream().map(panel -> panel.y).min(Integer::compareTo).orElseThrow();
        int maxX = collect.stream().map(panel -> panel.x).max(Integer::compareTo).orElseThrow();
        int maxY = collect.stream().map(panel -> panel.y).max(Integer::compareTo).orElseThrow();

        char[][] grid = new char[maxX - minX + 1][maxY - minY + 1];
        for (int i = 0; i < maxX - minX + 1; i++)
            for (int j = 0; j < maxY - minY + 1; j++) {
                Panel p = new Panel(i + minX, j + minY);
                grid[i][j] = gridMap.getOrDefault(p, '.');
            }
        return grid;
    }
}
