package com.github.mutare.adventcalendar2019.day18;

import java.util.*;

import static java.lang.String.format;

public class KeyFinder3 {

    private class Key {
        public Map<Integer, Integer> others;
        int y;
        int x;
        Set<Character> foundKeys;
        int distance;
        int number;
        LinkedList<String> route;

        public Key(int number, int y, int x, Set<Character> foundKeys, int distance, Map<Integer, Integer> others, List<String> route) {
            this.y = y;
            this.x = x;
            this.foundKeys = foundKeys;
            this.distance = distance;
            this.number = number;
            this.others = others;
            this.route = new LinkedList<>(route);
            String routeStep = format("(%d, %d)", x, y);
            if (this.route.size() == 0 || !this.route.getLast().equals(routeStep)) {
                this.route.add(routeStep);
            }

        }

        public Key(Key robot) {
            this.foundKeys = new HashSet<>(robot.foundKeys);
            this.x = robot.x;
            this.y = robot.y;
            this.distance = robot.distance;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash *= 31 * number;
            //hash *= 31 * y;
            //hash *= 31 * x;
            hash *= 31 * Arrays.hashCode(foundKeys.toArray());

            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            Key key = (Key) obj;
            return this.y == key.y && this.x == key.x && this.number == key.number;
        }
    }

    private char[][] map;
    private int height;
    private int width;
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, 1, 0, -1};

    Set<Character> allKeys = new HashSet<>();
    Deque<Key> queue = new LinkedList<>();
    Map<Character, Key> waits = new HashMap<>();
    Map<Character, Set<Key>> foundKeys = new HashMap<>();
    Map<Integer, Set<Character>> available = new HashMap<>();

    public KeyFinder3(char[][] map) {
        this.map = map;
        this.height = map.length;
        this.width = map[0].length;
        int number = 0;
        for (byte r = 0; r < height; r++)
            for (byte c = 0; c < width; c++) {
                if (map[r][c] == '@') {
                    queue.push(new Key(number++, r, c, new HashSet<>(), (short) 0, new HashMap<>(), new LinkedList<>()));
                }
                if ('a' <= map[r][c] && map[r][c] <= 'z')
                    allKeys.add(map[r][c]);
            }
        getAvailable(queue);
    }

    private void getAvailable(Deque<Key> queue) {
        for (Key robot : queue) {
            available.put(robot.number, getAvailable(robot));
        }
    }

    private Set<Character> getAvailable(Key robot) {
        Set<Character> available = new HashSet<>();
        Set<Key> seen = new LinkedHashSet<>();
        Deque<Key> queue = new LinkedList<>();
        queue.add(new Key(robot));
        while (!queue.isEmpty()) {
            Key pop = queue.pop();
            if (seen.contains(pop)) continue;
            seen.add(pop);
            if (!(0 <= pop.y && pop.y < height && 0 <= pop.x && pop.x < width && map[pop.y][pop.x] != '#')) continue;
            //if ('A' <= map[pop.y][pop.x] && map[pop.y][pop.x] <= 'Z') continue;
            if ('a' <= map[pop.y][pop.x] && map[pop.y][pop.x] <= 'z') available.add(map[pop.y][pop.x]);
            for(int i = 0 ; i < dr.length ; i++)
                queue.add(new Key(pop.number, (byte) (pop.y + dr[i]), (byte) (pop.x + dc[i]), new HashSet<>(pop.foundKeys), (short) (pop.distance + 1), new HashMap<>(), new LinkedList<>()));
        }
        return available;
    }

    public int getShortestPathSteps() {
        Set<Key> seen = new LinkedHashSet<>();

        while (!queue.isEmpty()) {
            Key next = queue.pop();
            Key key = new Key(next.number, next.y, next.x, new HashSet<>(next.foundKeys), next.distance, new HashMap<>(next.others), next.route);
            if (seen.contains(key)) continue;
            seen.add(key);
            if (seen.size() % 100000 == 0) System.out.println(seen.size());
            if (map[next.y][next.x] == '#') continue;

            if ('A' <= map[next.y][next.x] && map[next.y][next.x] <= 'Z') {
                if (!foundKeys.containsKey(Character.toLowerCase(map[next.y][next.x]))) {
                    printSleepInfo(next);
                    waits.put(map[next.y][next.x], next);
                } else {
                    for (Key k : foundKeys.get(Character.toLowerCase(map[next.y][next.x]))) {
                        if (k.number == next.number) continue;
                        Set<Character> foundKeys = new HashSet<>(next.foundKeys);
                        foundKeys.addAll(k.foundKeys);

                        Map<Integer, Integer> others = new HashMap<>(next.others);

                        others.putAll(k.others);
                        Key newNext = new Key(next.number, next.y, next.x, foundKeys, next.distance, others, next.route);
                        go(newNext);
                    }
                }
                continue;
            }

            if ('a' <= map[next.y][next.x] && map[next.y][next.x] <= 'z') {
                if (next.foundKeys.contains(map[next.y][next.x])) continue;
                next.foundKeys.add(map[next.y][next.x]);
                foundKeys.putIfAbsent(map[next.y][next.x], new HashSet<>());
                foundKeys.get(map[next.y][next.x]).add(next);

                Key key1 = waits.get(Character.toUpperCase(map[next.y][next.x]));
                if (key1 != null) {
                    HashSet<Character> characters = new HashSet<>(next.foundKeys);
                    characters.removeIf(character -> !available.get(key1.number).contains(character));
                    if (!key1.foundKeys.containsAll(characters)) continue;

                    printWakeInfo(key1);

                    Set<Character> foundKeys = new HashSet<>(key1.foundKeys);
                    foundKeys.addAll(next.foundKeys);
                    Map<Integer, Integer> others = new HashMap<>(key1.others);

                    others.putAll(next.others);
                    others.put(next.number, next.distance);
                    Key newKey = new Key(key1.number, key1.y, key1.x, foundKeys, key1.distance, others, key1.route);
                    go(newKey);
                }
                if (next.foundKeys.equals(allKeys)) {
                    int i = next.distance + next.others.values().stream().reduce(Integer::sum).orElseThrow();
                    System.out.println("#####################################");
                    System.out.println(i);
                    //return i;
                }
            }
            printRobotInfo(next);
            go(next);
        }
        throw new RuntimeException("Not found");
    }

    private void printSleepInfo(Key robot) {
        if (robot.number == 2)
            System.out.println(format("%d asleep", robot.number));
    }

    private void printWakeInfo(Key robot) {
        if (robot.number == 2)
            System.out.println(format("%d wakes up", robot.number));
    }

    private void printRobotInfo(Key robot) {
        //if (map[robot.y][robot.x] >= 'a' && map[robot.y][robot.x] <= 'z')
        if (robot.number == 2)
            System.out.println(format("Robot %d (%s)  collects %s in %d [%s]", robot.number, robot.foundKeys, map[robot.y][robot.x], robot.distance, robot.route));
    }

    private void go(Key next) {
        for (int i = 0; i < dr.length; i++) {
            queue.add(new Key(next.number, (byte) (next.y + dr[i]), (byte) (next.x + dc[i]), new HashSet<>(next.foundKeys), (short) (next.distance + 1), new HashMap<>(next.others), next.route));
        }
    }

}
