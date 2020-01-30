package com.github.mutare.adventcalendar2019.day24;

public class PlanetOfBugs {
    private char[][] map;

    PlanetOfBugs(char[][] map) {
        this.map = map;
    }


    public void nextMinute() {
        char[][] next = new char[map.length][map[0].length];

        for (int j = 0; j < map.length; j++)
            for (int i = 0; i < map[0].length; i++) {
                int n = bugsAdjacentTo(j, i);
                if (map[j][i] == '#' && n != 1) {
                    next[j][i] = '.';
                } else if (map[j][i] == '.' && (n == 1 || n == 2)) {
                    next[j][i] = '#';
                } else {
                    next[j][i] = map[j][i];
                }
            }
        map = next;
    }

    private int bugsAdjacentTo(int j, int i) {
        int n = 0;
        if (j > 0 && map[j - 1][i] == '#') n++;
        if (j < map.length - 1 && map[j + 1][i] == '#') n++;
        if (i > 0 && map[j][i - 1] == '#') n++;
        if (i < map[0].length - 1 && map[j][i + 1] == '#') n++;
        return n;
    }

    public char[][] getMap() {
        return map;
    }

    public int getBiodiversity() {
        int biodiversity = 0;
        for (int j = 0; j < map.length; j++)
            for (int i = 0; i < map[0].length; i++)
                biodiversity += (map[j][i] == '#' ? Math.pow(2, j * map[0].length + i) : 0);
        return biodiversity;
    }
}
