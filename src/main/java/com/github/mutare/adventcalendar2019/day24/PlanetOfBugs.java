package com.github.mutare.adventcalendar2019.day24;

public class PlanetOfBugs {
    private final boolean recursive;
    private char[][][] map;
    private int width;
    private int height;
    private int levels = 20;
    private int init = levels/2;

    PlanetOfBugs(char[][] map) {
        this.map = new char[levels][][];
        this.map[init] = map;
        this.recursive = false;
        this.width = this.map[init][0].length;
        this.height = this.map[init].length;
    }

    PlanetOfBugs(char[][] map, boolean recursive) {
        this.map[init] = map;
        this.recursive = recursive;
    }


    public void nextMinute() {
        int l = recursive ? init : init;
        char[][] next = new char[height][width];

        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++) {
                int n = bugsAdjacentTo(j, i);
                if (map[l][j][i] == '#' && n != 1) {
                    next[j][i] = '.';
                } else if (map[l][j][i] == '.' && (n == 1 || n == 2)) {
                    next[j][i] = '#';
                } else {
                    next[j][i] = map[l][j][i];
                }
            }
        map[l] = next;
    }

    private int bugsAdjacentTo(int j, int i) {
        int n = 0;
        int l = recursive ? init : init;
        if (j > 0 && map[l][j - 1][i] == '#') n++;
        if (j < height - 1 && map[l][j + 1][i] == '#') n++;
        if (i > 0 && map[l][j][i - 1] == '#') n++;
        if (i < width - 1 && map[l][j][i + 1] == '#') n++;
        return n;
    }

    public char[][] getMap() {
        return map[init];
    }

    public int getBiodiversity() {
        int biodiversity = 0;
        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++)
                biodiversity += (map[init][j][i] == '#' ? Math.pow(2, j * width + i) : 0);
        return biodiversity;
    }
}
