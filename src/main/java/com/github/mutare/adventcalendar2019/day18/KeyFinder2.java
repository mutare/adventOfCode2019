package com.github.mutare.adventcalendar2019.day18;

import java.util.*;

public class KeyFinder2 {

    private class Key {
        int y;
        int x;
        Set<Character> foundKeys;
        int distance;

        public Key(int y, int x, Set<Character> foundKeys, int distance) {
            this.y = y;
            this.x = x;
            this.foundKeys = foundKeys;
            this.distance = distance;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash *= 31 * y;
            hash *= 31 * x;
            hash *= 31 * Arrays.hashCode(foundKeys.toArray());
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            Key key = (Key) obj;
            return this.y == key.y && this.x == key.x;
        }
    }

    private char[][] map;
    private int height;
    private int width;
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, 1, 0, -1};

    Set<Character> allKeys = new HashSet<>();
    Deque<Key> queue = new LinkedList<>();

    public KeyFinder2(char[][] map) {
        this.map = map;
        this.height = map.length;
        this.width = map[0].length;

        for (byte r = 0; r < height; r++)
            for (byte c = 0; c < width; c++) {
                if (map[r][c] == '@')
                    queue.push(new Key(r, c, new HashSet<>(), (short) 0));
                if ('a' <= map[r][c] && map[r][c] <= 'z')
                    allKeys.add(map[r][c]);
            }
    }

    public int getShortestPathSteps() {
        Set<Key> seen = new LinkedHashSet<>();

        while (!queue.isEmpty()) {
            Key next = queue.pop();
            Key key = new Key(next.y, next.x, next.foundKeys, next.distance);
            if (seen.contains(key)) {
                continue;
            }
            seen.add(key);
            if (seen.size() % 100000 == 0) System.out.println(seen.size());
            if (!(0 <= next.y && next.y < height && 0 <= next.x && next.x < width && map[next.y][next.x] != '#') || ('A' <= map[next.y][next.x] && map[next.y][next.x] <= 'Z' && !next.foundKeys.contains(Character.toLowerCase(map[next.y][next.x]))))
                continue;
            //System.out.println(format("(%d, %d,(%s) ", key.r, key.c, new HashSet(key.keys)));

            Set<Character> newKeys = new HashSet<>(next.foundKeys);
            if ('a' <= map[next.y][next.x] && map[next.y][next.x] <= 'z') {
                newKeys.add(map[next.y][next.x]);
                if (newKeys.equals(allKeys)) {
                    return next.distance;
                }
            }
            for (int i = 0; i < dr.length; i++)
                queue.add(new Key((byte) (next.y + dr[i]), (byte) (next.x + dc[i]), newKeys, (short) (next.distance + 1)));
        }
        throw new RuntimeException("Not found");
    }

}
