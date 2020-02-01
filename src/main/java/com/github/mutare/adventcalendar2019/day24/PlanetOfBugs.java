package com.github.mutare.adventcalendar2019.day24;

public class PlanetOfBugs {
    char[][][] map;

    private final boolean recursive;
    private int width = 5;
    private int height = 5;
    private int init = 1000;
    private int levels = 2000;

    PlanetOfBugs(char[][] map, boolean recursive) {
        this.map = new char[levels][height][width];
        this.recursive = recursive;
        for (int l = 0; l < levels; l++) {
            for (int j = 0; j < height; j++)
                for (int i = 0; i < width; i++)
                    this.map[l][j][i] = (l == init ? map[j][i] : '.');
        }
    }


    PlanetOfBugs(char[][] map) {
        this(map, false);
    }


    public void nextMinute() {
        char[][][] next = new char[levels][height][width];
        for (int l = 0; l < levels; l++) {
            if (!recursive && l != init) continue;
            for (int j = 0; j < height; j++)
                for (int i = 0; i < width; i++) {
                    int n = bugsAdjacentTo(l, j, i);
                    if (map[l][j][i] == '#' && n != 1) {
                        next[l][j][i] = '.';
                    } else if (map[l][j][i] == '.' && (n == 1 || n == 2)) {
                        if (j == 2 && i == 2 && recursive) continue;
                        next[l][j][i] = '#';
                    } else {
                        next[l][j][i] = map[l][j][i];
                    }
                }

        }
        map = next;
    }

    private int bugsAdjacentTo(int l, int j, int i) {
        int n = 0;
        if (!recursive) {
            if (j > 0 && map[l][j - 1][i] == '#') n++;
            if (j < height - 1 && map[l][j + 1][i] == '#') n++;
            if (i > 0 && map[l][j][i - 1] == '#') n++;
            if (i < width - 1 && map[l][j][i + 1] == '#') n++;
        } else {
            //outside
            if (j == 0) n += getBugsFrom(l - 1, new int[]{7});//bottom
            if (j == height - 1) n += getBugsFrom(l - 1, new int[]{17});//up
            if (i == 0) n += getBugsFrom(l - 1, new int[]{11});//right
            if (i == width - 1) n += getBugsFrom(l - 1, new int[]{13});//left

            //inside
            if (i == 2 && j == 3) n += getBugsFrom(l + 1, new int[]{20, 21, 22, 23, 24});//bottom
            if (i == 2 && j == 1) n += getBugsFrom(l + 1, new int[]{0, 1, 2, 3, 4});//up
            if (i == 3 && j == 2) n += getBugsFrom(l + 1, new int[]{4, 9, 14, 19, 24});//right
            if (i == 1 && j == 2) n += getBugsFrom(l + 1, new int[]{0, 5, 10, 15, 20});//left


            if (j > 0 && map[l][j - 1][i] == '#') n++;
            if (j < height - 1 && map[l][j + 1][i] == '#') n++;
            if (i > 0 && map[l][j][i - 1] == '#') n++;
            if (i < width - 1 && map[l][j][i + 1] == '#') n++;
        }
        return n;
    }

    private int getBugsFrom(int l, int[] pos) {
        int n = 0;
        if (l > 0 && l < levels)
            for (int p : pos) {
                if (map[l][p / 5][p % 5] == '#') n++;
            }
        return n;
    }

    public char[][] getMap() {
        return getMap(0);
    }


    public char[][] getMap(int level) {
        return map[init - level];
    }

    public int getBiodiversity() {
        int biodiversity = 0;
        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++)
                biodiversity += (map[init][j][i] == '#' ? Math.pow(2, j * width + i) : 0);
        return biodiversity;
    }

    public int getNoOfBugs() {
        int n = 0;
        for (int l = 0; l < this.map.length; l++)
            for (int j = 0; j < height; j++)
                for (int i = 0; i < width; i++)
                    if (map[l][j][i] == '#') n++;
        return n;
    }

}
